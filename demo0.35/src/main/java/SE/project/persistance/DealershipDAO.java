package SE.project.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SE.project.carDealership.Dealership;

public class DealershipDAO implements DAOInterface<Dealership> {

    public DealershipDAO() {
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
            System.out.println("will run query to get all dealerships");
            String query = "SELECT * FROM dealerships";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Dealership> dealerships = new ArrayList<>();
            while (resultSet.next()) {
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
    public static boolean exists() {
        try {
            ResultSet resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
