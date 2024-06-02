package assg.config.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiUrlsConfig {

	@SerializedName("launchers")
	@Expose
	private String launchers;
	@SerializedName("customer_satellites")
	@Expose
	private String customerSatellites;

	public String getLaunchers() {
		return launchers;
	}

	public void setLaunchers(String launchers) {
		this.launchers = launchers;
	}

	public String getCustomerSatellites() {
		return customerSatellites;
	}

	public void setCustomerSatellites(String customerSatellites) {
		this.customerSatellites = customerSatellites;
	}

	@Override
	public String toString() {
		return "ApiUrlsConfig [launchers=" + launchers + "+ customerSatellites=" + customerSatellites + "]";
	}

}
