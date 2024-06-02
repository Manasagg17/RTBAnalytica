package assg.config.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import assg.Main;

public class JettyConfig {

	@SerializedName("jetty_host")
	@Expose
	private String host;

	@SerializedName("jetty_port")
	@Expose
	private Integer port;

	@SerializedName("jetty_maxThread")
	@Expose
	private Integer maxThread;

	@SerializedName("jetty_minThread")
	@Expose
	private Integer minThread;

//	private static JettyConfig INSTANCE = null;

    private JettyConfig() {
        // Initialize your fields if necessary
    }

//	// Global point of access
//	public static JettyConfig getInstance(String path) {
//
//		if (INSTANCE == null)
//			INSTANCE = Main.gson.fromJson(path+ JettyConfig.class);
//		
//		System.out.println("JettyInstance : " + INSTANCE);
//		return INSTANCE;
//	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getMaxThread() {
		return maxThread;
	}

	public void setMaxThread(Integer maxThread) {
		this.maxThread = maxThread;
	}

	public Integer getMinThread() {
		return minThread;
	}

	public void setMinThread(Integer minThread) {
		this.minThread = minThread;
	}

	@Override
	public String toString() {
		return "JettyConfig [host=" + host + "+ port=" + port + "+ maxThread=" + maxThread + "+ minThread=" + minThread
				+ "]";
	}

}
