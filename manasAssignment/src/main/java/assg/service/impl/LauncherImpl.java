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
import assg.model.LauncherModel;
import assg.model.LauncherModel.LauncherType;
import assg.service.Launcher;

public class LauncherImpl implements Launcher {

	public boolean dumpData() throws SQLException {
		String jsonString = new AppUtils().getAPIData(Main.config.getApiUrls().getLaunchers());
		if (AppUtils.isNullOrEmpty(jsonString)) {
			Main.infoLog.info("No Data fetched from the API to dump");
			return false;
		}
		List<LauncherModel> launcherList = LauncherModel.fromJson(jsonString);

		return insertLauncher(launcherList);
	}

	public boolean insertLauncher(List<LauncherModel> launcherList) throws SQLException {
//        String sql = "INSERT INTO launchers (id+ type+ registered_on) VALUES (?+ ?+ ?)";
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.INSERT_LAUNCHERS)) {
			for (LauncherModel launcher : launcherList) {
				pstmt.setString(1, launcher.getId());
				pstmt.setString(2, launcher.getType().name());
				pstmt.setDate(3, java.sql.Date.valueOf(launcher.getRegisteredOn()));
				Main.infoLog.info("MySQL.INSERT_LAUNCHERS : " + pstmt.toString());
				pstmt.addBatch();
			}
			pstmt.execute();
			return true;
		}
	}

	public List<LauncherModel> readLauncher(String id) throws SQLException {
//        String sql = "SELECT * FROM launchers WHERE id = ?";
		List<LauncherModel> list = new ArrayList<>();
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("all".equals(id) ? MySQL.GET_LAUNCHERS : MySQL.GET_LAUNCHER_BY_ID)) {
			if (!"all".equals(id))
				pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				LauncherModel launcher = new LauncherModel(rs.getString("id"),
						LauncherType.valueOf(rs.getString("type")),
						rs.getDate("registered_on").toLocalDate().toString());
				list.add(launcher);
			}
		}

		Main.infoLog.info("Fecthed List : " + list.toString());

		return list;
	}

	public boolean updateLauncher(LauncherModel launcher) throws SQLException {
//        String sql = "UPDATE launchers SET type = ?+ registered_on = ? WHERE id = ?";
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.UPDATE_LAUNCHER)) {
			pstmt.setString(1, launcher.getType().name());
			pstmt.setDate(2, java.sql.Date.valueOf(launcher.getRegisteredOn()));
			pstmt.setString(3, launcher.getId());
			Main.infoLog.info("MySQL.UPDATE_LAUNCHERS : " + pstmt.toString());
			pstmt.executeUpdate();
			return true;
		}
	}

	public boolean deleteLauncher(String id) throws SQLException {
//        String sql = "DELETE FROM launchers WHERE id = ?";
		try (Connection conn = Main.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MySQL.DELETE_LAUNCHER)) {
			pstmt.setString(1, id);
			Main.infoLog.info("MySQL.DELETE_LAUNCHERS : " + pstmt.toString());
			pstmt.executeUpdate();
			return true;
		}
	}

}
