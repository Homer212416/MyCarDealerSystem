package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import carDealership.Dealership;

public class DealershipDAO implements DAOInterface<Dealership> {

	private static String m_name;
	private static String m_location;
	private static int m_capacity;
	
    public DealershipDAO() {
    }

	public DealershipDAO(String name, String location, int capacity) throws SQLException {
		m_name = name;
		m_location = location;
		m_capacity = capacity;

		DBManager.getInstance().runInsert("INSERT INTO dealerships " + "(name, location, capacity) " + "VALUES ('"
				+ name + "', '" + location + "', " + Integer.toString(capacity) + ");");
	}
	
    // Create the table if it does not exist
    private void createTable() {
        String dealershipSQL = "CREATE TABLE IF NOT EXISTS dealerships ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "location TEXT NOT NULL, "
                + "capacity INTEGER NOT NULL);";
        try {
            DBManager.getInstance().runInsert(dealershipSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Dealership dealership) {
			
	   try {
            String query = "INSERT INTO dealerships (name, location, capacity) VALUES ('"
                    + dealership.getName() + "', '"
                    + dealership.getLocation() + "', "
                    + dealership.getCapacity() + ");";
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Dealership> getAll() {
        try {
            String query = "SELECT * FROM dealerships";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Dealership> dealerships = new ArrayList<>();
			System.out.println(resultSet.getString("name"));
			if(resultSet.next()) {
                dealerships.add(new Dealership(
                        resultSet.getString("name"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity")));
            }
            return dealerships;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // two additional methods
    // for delete dealership button
    public static boolean deleteAll() {
        try {
            // Drop the dealerships table
            String dropDealershipsTable = "DROP TABLE IF EXISTS dealerships";
            DBManager.getInstance().runInsert(dropDealershipsTable);

            // Drop other tables if they exist (e.g., users, roles, vehicles)
            String dropUsersTable = "DROP TABLE IF EXISTS users";
            DBManager.getInstance().runInsert(dropUsersTable);

            String dropRolesTable = "DROP TABLE IF EXISTS roles";
            DBManager.getInstance().runInsert(dropRolesTable);

            String dropVehiclesTable = "DROP TABLE IF EXISTS vehicles";
            DBManager.getInstance().runInsert(dropVehiclesTable);

            System.out.println("All tables have been deleted.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // for Main.java to check if the dealership exists
    public static boolean exists() throws SQLException{
        try {
            ResultSet resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}