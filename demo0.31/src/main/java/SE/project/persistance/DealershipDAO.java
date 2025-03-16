package SE.project.persistance;

import SE.project.carDealership.Dealership;
import java.sql.ResultSet;
import java.sql.SQLException;

// This class is responsible for handling the database operations related to the Dealership table
// It is used by the Dealership class to interact with the database
public class DealershipDAO {

    public boolean exists() {
        try {
            ResultSet resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertDealership(Dealership dealership) {
        try {
            String query = "INSERT INTO dealerships (name, location, capacity) VALUES ('"
                    + dealership.getName() + "', '"
                    + dealership.getLocation() + "', "
                    + dealership.getCapacity() + ");";
            DBManager.getInstance().runInsert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDealership() {
        try {
            DBManager.getInstance().runInsert("DELETE FROM dealerships");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dealership getDealership() {
        try {
            ResultSet resultSet = DBManager.getInstance().runQuery("SELECT * FROM dealerships");
            if (resultSet.next()) {
                return new Dealership(
                        resultSet.getString("name"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
