package SE.project.carDealership;

import SE.project.persistance.DBManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Dealership {
	private String name;
	private String location;
	private int nv;
	private int ns;
	private Vehicle[] inventory;
	private Sale[] sales;
	private int nextId;

	public Dealership() {
	}

	public Dealership(String name, String location, int maxInventory) throws SQLException {
		this.name = name;
		this.location = location;
		inventory = new Vehicle[maxInventory];
		sales = new Sale[maxInventory * 2];
		nv = 0;
		ns = 0;
		nextId = 0;

		DBManager.getInstance().runInsert("INSERT INTO dealerships " + "(name, location, capacity) " + "VALUES ('"
				+ name + "', '" + location + "', " + Integer.toString(maxInventory) + ");");
	}

	public boolean existsAndSet() throws SQLException {
		var resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
		boolean dealershipFound = false;

		while (resultSet.next()) {
			dealershipFound = true;
			name = resultSet.getString("name");
			location = resultSet.getString("location");
			inventory = new Vehicle[resultSet.getInt("capacity")];
		}

		return dealershipFound;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getCapacity() {
		return inventory.length;
	}

	// Dealership End

}
