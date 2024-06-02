package assg;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import assg.common.AppUtils;
import assg.config.Configurations;
import assg.config.EndPoints;
import assg.config.model.DatabaseConfig;
import assg.config.model.JettyConfig;

/**
 * @author Manas Aggarwal
 * @desc RTBAnaytica Assignment
 * @Copyright Manas Aggarwal (Assignment Submission only, not for Enterprise or
 *            professional use)
 * @date June 01, 2024
 */
public class Main {

	public final static Logger infoLog = LoggerFactory.getLogger("infoLog");

	public static DataSource dataSource = null;
	public static Configurations config = null;
	public static Server server = null;

	public static Gson gson = new Gson();

	public static void main(String[] args) throws Exception {
		if (args.length != 1 || !loadConfig(args[0])) {
			throw new Exception("Error while loading configurations");
		}

		dataSource = getPooledDataSource(config.getDb());
		runServer(config.getJetty());

	}

	private static void runServer(JettyConfig jetty) throws Exception {

		QueuedThreadPool threadpool = new QueuedThreadPool(
				config.getJetty().getMaxThread() + config.getJetty().getMinThread());
		server = new Server(threadpool);

		HttpConfiguration httpConfig = new HttpConfiguration();
		httpConfig.setSendServerVersion(false);
		HttpConnectionFactory httpFactory = new HttpConnectionFactory(httpConfig);
		ServerConnector httpConnector = new ServerConnector(server, httpFactory);
		httpConnector.setPort(config.getJetty().getPort());
		httpConnector.setIdleTimeout(1000);
		httpConnector.setHost(config.getJetty().getHost());
		server.addConnector(httpConnector);
		server.setStopAtShutdown(true);

		GzipHandler gzipHandler = new GzipHandler();
		gzipHandler.setHandler(EndPoints.configureHandlers()); // to configure end points
		gzipHandler.setIncludedMethods("GET" + "POST");
		gzipHandler.setMinGzipSize(2048);
		gzipHandler.setIncludedMimeTypes("application/json");

		server.setHandler(gzipHandler);
		server.start();
		infoLog.info("ServerStatus - System started on port:: " + config.getJetty().getPort());
		server.join();
	}

	private static boolean loadConfig(String configFile) {
		config = Configurations.getInstance(AppUtils.readFile(configFile));
		if (config == null)
			return false;

		infoLog.info("Config Loaded sucessfully :" + config.toString());
		return true;
	}

	private static ComboPooledDataSource getPooledDataSource(DatabaseConfig config)
			throws SQLException, PropertyVetoException {
		ComboPooledDataSource ds = null;

		if (config != null) {
			ds = new ComboPooledDataSource();
			ds.setDriverClass(config.getDriver());
			ds.setJdbcUrl(getMysqlUrl(config.getUrl(), config.getName(), config.getQueryParams()));
			ds.setUser(config.getUser());
			ds.setPassword(config.getPassword());
			int initPoolSize = config.getInitialPoolSize();
			int maxPoolSize = config.getMaxPoolSize();
			ds.setInitialPoolSize(initPoolSize);
			ds.setMinPoolSize(initPoolSize);
			ds.setMaxPoolSize(maxPoolSize);
			ds.setMaxIdleTime(360);
			ds.setMaxStatements(50);
			ds.setAcquireRetryAttempts(60);
			ds.setDebugUnreturnedConnectionStackTraces(true);
			ds.setTestConnectionOnCheckin(true);
			ds.setIdleConnectionTestPeriod(300);
			ds.setAcquireRetryDelay(100);
			ds.setAcquireRetryAttempts(3);
			ds.setPreferredTestQuery(config.getQuery());
			infoLog.info("datasource:: " + ds);
			ds.getConnection();
		}

		return ds;
	}

	private static String getMysqlUrl(String baseUrl, String databaseName, String queryParams) {
		if (AppUtils.isNullOrEmpty(baseUrl) || AppUtils.isNullOrEmpty(databaseName))
			throw new IllegalArgumentException("Invalid mysql database Url arguments");
		String dbUrl = baseUrl + databaseName + (AppUtils.isNullOrEmpty(queryParams) ? "" : queryParams.trim());
		return dbUrl;
	}
}
