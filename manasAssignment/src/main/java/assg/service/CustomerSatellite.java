package assg.service;

import java.sql.SQLException;
import java.util.List;

import assg.model.CustomerSatelliteModel;

public interface CustomerSatellite {

	boolean dumpData() throws SQLException;

	boolean insertCustomerSatellite(List<CustomerSatelliteModel> customerSatelliteList) throws SQLException;

	List<CustomerSatelliteModel> readCustomerSatellite(String id) throws SQLException;

	boolean updateCustomerSatellite(CustomerSatelliteModel customerSatellite) throws SQLException;

	boolean deleteCustomerSatellite(String id) throws SQLException;
}
