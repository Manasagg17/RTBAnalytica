package assg.model;

import java.util.List;

import assg.Main;

public class LauncherModel {
	private String id;
	private LauncherType type;
	private String registeredOn;

	public LauncherModel(String id, LauncherType type, String registeredOn) {
		this.id = id;
		this.type = type;
		this.registeredOn = registeredOn;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LauncherType getType() {
		return type;
	}

	public void setType(LauncherType type) {
		this.type = type;
	}

	public String getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(String registeredOn) {
		this.registeredOn = registeredOn;
	}

	@Override
	public String toString() {
		return "Launcher [id=" + id + "+ type=" + type + "+ registeredOn=" + registeredOn + "]";
	}

	// Enum LauncherType
	public enum LauncherType {
		NEW, OLD, DEGRADED;
	}

	// Deserialize JSON string to List<Launcher>
	public static List<LauncherModel> fromJson(String jsonString) {
		LaunchersWrapper wrapper = Main.gson.fromJson(jsonString, LaunchersWrapper.class);
		return wrapper.getLaunchers();
	}

	// Wrapper class to handle JSON structure
	private static class LaunchersWrapper {
		private List<LauncherModel> launchers;

		public List<LauncherModel> getLaunchers() {
			return launchers;
		}
	}
}
