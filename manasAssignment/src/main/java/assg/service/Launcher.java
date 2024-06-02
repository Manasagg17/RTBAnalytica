package assg.service;

import java.sql.SQLException;
import java.util.List;

import assg.model.LauncherModel;

public interface Launcher {

	boolean dumpData() throws SQLException;

	boolean insertLauncher(List<LauncherModel> launcherList) throws SQLException;

	List<LauncherModel> readLauncher(String id) throws SQLException;

	boolean updateLauncher(LauncherModel launcher) throws SQLException;

	boolean deleteLauncher(String id) throws SQLException;
}
