package SE.project.carDealership;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.core.DB;

import SE.project.persistance.DBManager;

public class Dealership {
	private String name;
	private String location;
	private int capacity;
	// private int nv;
	// private int ns;
	// commented out because dealership does not provide the number of vehicles and
	// sales
	// private Vehicle[] inventory;
	// private Sale[] sales;
	// private int nextId;
	// commented out because we dealership does not provide these data

	// default constructor
	public Dealership() {
		if (name == null) { // if there is not info in fields, buffer them for later use
			try {
				bufferAllInfo();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// the constructor is only for first launch
	public Dealership(String name, String location, int capacity) throws SQLException {
		this.name = name;
		this.location = location;
		this.capacity = capacity;

		// INSERT INTO Dealerships (name, location, capacity)
		// VALUES ('<name>', '<location>', <capacity>);
		DBManager.getInstance().runInsert("INSERT INTO dealerships "
				+ "(name, location, capacity) "
				+ "VALUES ('" + name + "', '" + location + "', " + capacity + ");");
	}

	public boolean exists() throws SQLException {
		// ResultSet is a interface that represents the result set of a database query
		// in Java
		// Imagine it as a table
		// ResultSet provides methods:
		// next() - moves the cursor to the next row
		// getDatatype(Datatype columnLabel or columnID) - gets the value of a column in
		// the current row

		// runQuery() is run on the instance of DBManager
		ResultSet resultSet = DBManager.getInstance().runQuery(
				// count the number of tables named dealerships
				"SELECT * FROM dealerships");
		// The resultSet.next() moves the cursor to the next row and returns a boolean
		// value
		if (resultSet.next()) {
			return true;
		}
		return false;
	}

	// public boolean existsAndSet() throws SQLException {
	// var resultSet = DBManager.getInstance().runQuery("SELECT * FROM
	// dealerships");
	// boolean dealershipFound = false;

	// while (resultSet.next()) {
	// dealershipFound = true;
	// name = resultSet.getString("name");
	// location = resultSet.getString("location");
	// }

	// return dealershipFound;
	// }

	// bufferAllInfo() is used to store all the information of the dealership in the
	public void bufferAllInfo() throws SQLException {
		ResultSet resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
		if (resultSet.next()) {
			name = resultSet.getString("name");
			location = resultSet.getString("location");
			capacity = resultSet.getInt("capacity");
		}
	}

	public String[] getInfoGUI() {
		String[] info = new String[8];
		info[0] = name;
		info[1] = location;
		info[2] = Integer.toString(capacity);
		// info[3] = Integer.toString(nv);
		// info[4] = Integer.toString(ns);
		// commented out because dealership does not provide the number of vehicles and
		// sales
		// info[5] = Integer.toString(inventory.length);
		// info[6] = Integer.toString(sales.length);
		// info[7] = Integer.toString(nextId);
		// commented out because dealership does not provide these data
		return info;
	}

	public void delete() throws SQLException {
		DBManager.getInstance().runInsert("DELETE FROM dealerships"); // delete all the rows in the table
	}
}
