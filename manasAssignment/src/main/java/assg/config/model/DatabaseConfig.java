package assg.config.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatabaseConfig {

	@SerializedName("driver")
	@Expose
	private String driver;
	@SerializedName("url")
	@Expose
	private String url;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("user")
	@Expose
	private String user;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("initialPoolSize")
	@Expose
	private int initialPoolSize;
	@SerializedName("maxPoolSize")
	@Expose
	private int maxPoolSize;
	@SerializedName("queryParams")
	@Expose
	private String queryParams;
	@SerializedName("query")
	@Expose
	private String query;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "DatabaseConfig [driver=" + driver + "+ url=" + url + "+ name=" + name + "+ user=" + user + "+ password="
				+ password + "+ initialPoolSize=" + initialPoolSize + "+ maxPoolSize=" + maxPoolSize + "+ queryParams="
				+ queryParams + "+ query=" + query + "]";
	}

}
