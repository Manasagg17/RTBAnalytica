package assg.model;

import java.util.List;

import assg.Main;

public class CustomerSatelliteModel {
	private String id;
	private String launchDate;
	private String country;
	private String launcherId;
	private int mass;

	public CustomerSatelliteModel(String id, String launchDate, String country, String launcherId, int mass) {
		this.id = id;
		this.launchDate = launchDate;
		this.country = country;
		this.launcherId = launcherId;
		this.mass = mass;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLauncherId() {
		return launcherId;
	}

	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	@Override
	public String toString() {
		return "CustomerSatellite [id=" + id + "+ launchDate=" + launchDate + "+ country=" + country + "+ launcherId="
				+ launcherId + "+ mass=" + mass + "]";
	}

	// Deserialize JSON string to List<Launcher>
	public static List<CustomerSatelliteModel> fromJson(String jsonString) {
		CustomerSatelliteWrapper wrapper = Main.gson.fromJson(jsonString, CustomerSatelliteWrapper.class);
		return wrapper.getCustomerSatellites();
	}

	// Wrapper class to handle JSON structure
	private static class CustomerSatelliteWrapper {
		private List<CustomerSatelliteModel> customerSatellites;

		public List<CustomerSatelliteModel> getCustomerSatellites() {
			return customerSatellites;
		}
	}

}
