package assg.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import assg.Main;
import assg.config.model.ApiUrlsConfig;
import assg.config.model.DatabaseConfig;
import assg.config.model.JettyConfig;

public class Configurations {

	@SerializedName("jetty_config")
	@Expose
	private JettyConfig jetty;

	@SerializedName("database")
	@Expose
	private DatabaseConfig db;

	@SerializedName("api-urls")
	@Expose
	private ApiUrlsConfig apiUrls;

	// Private constructor to prevent instantiation
	private Configurations() {
		// Initialize your fields if necessary
	}

	private static Configurations INSTANCE = null;

	// Global point of access
	public static Configurations getInstance(String jsonString) {
		if (INSTANCE == null) {
			INSTANCE = Main.gson.fromJson(jsonString, Configurations.class);
		}
		return INSTANCE;
	}

	public JettyConfig getJetty() {
		return jetty;
	}

	public void setJetty(JettyConfig jetty) {
		this.jetty = jetty;
	}

	public DatabaseConfig getDb() {
		return db;
	}

	public void setDb(DatabaseConfig db) {
		this.db = db;
	}

	public ApiUrlsConfig getApiUrls() {
		return apiUrls;
	}

	public void setApiUrls(ApiUrlsConfig apiUrls) {
		this.apiUrls = apiUrls;
	}

	@Override
	public String toString() {
		return "Configurations [jetty=" + jetty + "+ db=" + db + "+ apiUrls=" + apiUrls + "]";
	}
}
