package assg.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assg.Main;
import assg.common.AppUtils;
import assg.common.MySQL;
import assg.model.CustomerSatelliteModel;
import assg.service.CustomerSatellite;

public class CustomerSatelliteImpl implements CustomerSatellite {

	@Override
	public boolean dumpData() throws SQLException {
		String jsonString = new AppUtils().getAPIData(Main.config.getApiUrls().getCustomerSatellites());
		if (AppUtils.isNullOrEmpty(jsonString)) {
			Main.infoLog.info("No Data fetched from the API to dump");
			return false;
		}
		List<CustomerSatelliteModel> customerSatelliteList = CustomerSatelliteModel.fromJson(jsonString);

		return insertCustomerSatellite(customerSatelliteList);
	}

	@Override
	public boolean insertCustomerSatellite(List<CustomerSatelliteModel> customerSatelliteList) throws SQLException {
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.INSERT_CUSTOMER_SATELLITES)) {
			for (CustomerSatelliteModel customerSatellite : customerSatelliteList) {
				pstmt.setString(1, customerSatellite.getId());
				pstmt.setDate(2, java.sql.Date.valueOf(customerSatellite.getLaunchDate()));
				pstmt.setString(3, customerSatellite.getCountry());
				pstmt.setString(4, customerSatellite.getLauncherId());
				pstmt.setInt(5, customerSatellite.getMass());
				Main.infoLog.info("MySQL.INSERT_CUSTOMER_SATELLITES : " + pstmt.toString());
				pstmt.addBatch();
			}
			pstmt.execute();
			return true;
		}
	}

	@Override
	public List<CustomerSatelliteModel> readCustomerSatellite(String id) throws SQLException {
//      String sql = "SELECT * FROM customerSatellites WHERE id = ?";
		List<CustomerSatelliteModel> list = new ArrayList<>();
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("all".equals(id) ? MySQL.GET_CUSTOMER_SATELLITES : MySQL.GET_CUSTOMER_SATELLITE_BY_ID)) {
			if (!"all".equals(id))
				pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomerSatelliteModel customerSatellite = new CustomerSatelliteModel(rs.getString("id"),
						rs.getDate("launch_date").toLocalDate().toString(), rs.getString("country"),
						rs.getString("customerSatellite_id"), rs.getInt("mass"));
				list.add(customerSatellite);
			}
		}

		Main.infoLog.info("Fecthed List : " + list.toString());

		return list;
	}

	@Override
	public boolean updateCustomerSatellite(CustomerSatelliteModel customerSatellite) throws SQLException {
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.UPDATE_CUSTOMER_SATELLITE)) {
            pstmt.setDate(1, java.sql.Date.valueOf(customerSatellite.getLaunchDate()));
            pstmt.setString(2, customerSatellite.getCountry());
            pstmt.setString(3, customerSatellite.getLauncherId());
            pstmt.setInt(4, customerSatellite.getMass());
            pstmt.setString(5, customerSatellite.getId());
			Main.infoLog.info("MySQL.UPDATE_CUSTOMER_SATELLITES : " + pstmt.toString());
			pstmt.executeUpdate();
			return true;
		}
	}

	@Override
	public boolean deleteCustomerSatellite(String id) throws SQLException {
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.DELETE_CUSTOMER_SATELLITE)) {
			pstmt.setString(1, id);
			Main.infoLog.info("MySQL.DELETE_CUSTOMER_SATELLITES : " + pstmt.toString());
			pstmt.executeUpdate();
			return true;
		}
	}

}
