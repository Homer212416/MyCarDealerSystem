package persistance;

import java.sql.SQLException;
import java.util.HashMap;

public class DealershipLayer {
	private String m_name;
	private String m_location;
	private int m_capacity;

	public DealershipLayer() {
	}

	public DealershipLayer(String name, String location, int capacity) throws SQLException {
		m_name = name;
		m_location = location;
		m_capacity = capacity;
		DBManager.getInstance().runInsert("INSERT INTO dealerships " + "(name, location, capacity) " + "VALUES ('"
				+ name + "', '" + location + "', " + Integer.toString(capacity) + ");");
	}

	public boolean existsAndSet() throws SQLException {
		System.out.println("exsistsAndSet Method Ran");
		var resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
		boolean dealershipFound = false;

		while (resultSet.next()) {
			dealershipFound = true;
			m_name = resultSet.getString("name");
			m_location = resultSet.getString("location");
			m_capacity = resultSet.getInt("capacity");
		}

		return dealershipFound;
	}

	// provides a map of all the dealership information
	public HashMap<String, String> getAllDealershipInfo() {
		try {
			HashMap<String, String> dealershipInfoMap = new HashMap<>();

			int carInInventoryNum = getCarInInventoryNum();
			int motorInInventoryNum = getMotorInInventoryNum();
			int salesNum = getSalesNum();
			int vehiclesSoldNum = getVehiclesSoldNum();

			var dealershipResultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
			if (dealershipResultSet.next()) {
				dealershipInfoMap.put("name", dealershipResultSet.getString("name"));
				dealershipInfoMap.put("location", dealershipResultSet.getString("location"));
				dealershipInfoMap.put("capacity", Integer.toString(dealershipResultSet.getInt("capacity")));
				dealershipInfoMap.put("available space", Integer
						.toString(dealershipResultSet.getInt("capacity") - (carInInventoryNum + motorInInventoryNum)));
				dealershipInfoMap.put("total cars", Integer.toString(carInInventoryNum));
				dealershipInfoMap.put("total motorcycles", Integer.toString(motorInInventoryNum));
				dealershipInfoMap.put("total sales", Integer.toString(salesNum));
				dealershipInfoMap.put("total vehicles sold", Integer.toString(vehiclesSoldNum));
			}

			return dealershipInfoMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // indicate error
		}
	}

	// public void deleteDealership() {
	// // drop all tables in the database
	// try {
	// DBManager.getInstance().runInsert("DROP TABLE dealerships;");
	// DBManager.getInstance().runInsert("DROP TABLE vehicles;");
	// DBManager.getInstance().runInsert("DROP TABLE users;");
	// DBManager.getInstance().runInsert("DROP TABLE usersInfo;");
	// DBManager.getInstance().runInsert("DROP TABLE roles;");
	// DBManager.getInstance().runInsert("DROP TABLE sales;");
	// } catch (SQLException e) {
	// System.out.println("layer: Error deleting dealership: " + e.getMessage());
	// e.printStackTrace();
	// }
	// }

	// truncates all tables in the database
	public void deleteDealership() {
		try {
			DBManager.getInstance().runInsert("DELETE FROM vehicles;");
			DBManager.getInstance().runInsert("DELETE FROM users;");
			DBManager.getInstance().runInsert("DELETE FROM usersInfo;");
			DBManager.getInstance().runInsert("DELETE FROM roles;");
			DBManager.getInstance().runInsert("DELETE FROM sales;");
		} catch (SQLException e) {
			System.out.println("layer: Error deleting dealership: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String getNname() {
		return m_name;
	}

	public String getLocation() {
		return m_location;
	}

	public int getCapacity() {
		return m_capacity;
	}

	// private helper method to get info

	private int getCarInInventoryNum() {
		try {
			var resultSet = DBManager.getInstance()
					.runQuery("SELECT COUNT(*) FROM vehicles WHERE inInventory = 1 AND carType IS NOT NULL;");
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // indicate error
		}
	}

	private int getMotorInInventoryNum() {
		try {
			var resultSet = DBManager.getInstance()
					.runQuery("SELECT COUNT(*) FROM vehicles WHERE inInventory = 1 AND handleBarType IS NOT NULL;");
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // indicate error
		}
	}

	private int getSalesNum() {
		try {
			var resultSet = DBManager.getInstance()
					.runQuery("SELECT SUM(price) FROM sales JOIN vehicles ON sales.vehicleID = vehicles.ID ;");
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // indicate error
		}
	}

	private int getVehiclesSoldNum() {
		try {
			var resultSet = DBManager.getInstance()
					.runQuery("SELECT COUNT(*) FROM sales;");
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // indicate error
		}
	}

}
