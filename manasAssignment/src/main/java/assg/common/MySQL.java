package assg.common;

public class MySQL {
	
	public static final String INSERT_LAUNCHERS = "INSERT INTO launchers (id, type, registered_on) VALUES (?, ?, ?)";
	public static final String GET_LAUNCHERS = "SELECT * FROM launchers";
	public static final String GET_LAUNCHER_BY_ID = "SELECT * FROM launchers WHERE id = ?";
	public static final String UPDATE_LAUNCHER = "UPDATE launchers SET type = ?, registered_on = ? WHERE id = ?";
	public static final String DELETE_LAUNCHER = "DELETE FROM launchers WHERE id = ?";

	public static final String INSERT_CUSTOMER_SATELLITES = "INSERT INTO customer_satellites (id, launch_date, country, launcher_id, mass) VALUES (?, ?, ?, ?, ?)";
	public static final String GET_CUSTOMER_SATELLITES = "SELECT * FROM customer_satellites";
	public static final String GET_CUSTOMER_SATELLITE_BY_ID = "SELECT * FROM customer_satellites WHERE id = ?";
	public static final String UPDATE_CUSTOMER_SATELLITE = "UPDATE customer_satellites SET launch_date = ?, country = ?, launcher_id = ?, mass = ? WHERE id = ?";
	public static final String DELETE_CUSTOMER_SATELLITE = "DELETE FROM customer_satellites WHERE id = ?";


	
}
