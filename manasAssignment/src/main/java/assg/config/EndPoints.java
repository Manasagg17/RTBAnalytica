package assg.config;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import assg.handlers.CustomerSatelliteHandler;
import assg.handlers.LauncherHandler;

public class EndPoints {

	public static Handler configureHandlers() {
		ContextHandlerCollection contexts = new ContextHandlerCollection();

		ContextHandler cxLauncher = new ContextHandler("/launchers/*");
		cxLauncher.setHandler(new LauncherHandler());
		cxLauncher.setAllowNullPathInfo(true);

		ContextHandler cxSatellite = new ContextHandler("/customer-satellites/*");
		cxSatellite.setHandler(new CustomerSatelliteHandler());
		cxSatellite.setAllowNullPathInfo(true);

		contexts.setHandlers(new Handler[] { cxLauncher, cxSatellite });

		return contexts;

	}
}
