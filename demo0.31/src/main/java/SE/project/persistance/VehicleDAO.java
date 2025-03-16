package SE.project.persistance;

import SE.project.carDealership.Car;
import SE.project.carDealership.Motorcycle;
import SE.project.carDealership.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public int countVehicles() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM vehicles";
        ResultSet resultSet = DBManager.getInstance().runQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt("total");
        }
        return 0;
    }

    public double calculateTotalValue() throws SQLException {
        String query = "SELECT SUM(price) AS totalValue FROM vehicles";
        ResultSet resultSet = DBManager.getInstance().runQuery(query);
        if (resultSet.next()) {
            return resultSet.getDouble("totalValue");
        }
        return 0;
    }

    public List<Vehicle> getAllVehicles() throws SQLException {
        String query = "SELECT * FROM vehicles";
        ResultSet resultSet = DBManager.getInstance().runQuery(query);
        List<Vehicle> vehicles = new ArrayList<>();
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            if ("Car".equals(type)) {
                vehicles.add(new Car(
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("color"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getString("type")));
            } else if ("Motorcycle".equals(type)) {
                vehicles.add(new Motorcycle(
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("color"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getString("handlebarType")));
            }
        }
        return vehicles;
    }

    public Vehicle getVehicleById(int id) {
        try {
            String query = "SELECT * FROM vehicles WHERE id = " + id;
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            if (resultSet.next()) {
                String type = resultSet.getString("type");
                if ("Car".equals(type)) {
                    return new Car(
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("price"),
                            resultSet.getString("type"));
                } else if ("Motorcycle".equals(type)) {
                    return new Motorcycle(
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("price"),
                            resultSet.getString("handlebarType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertVehicle(Vehicle vehicle) {
        try {
            String query = "INSERT INTO vehicles (make, model, color, year, price, type, handlebarType) VALUES ('"
                    + vehicle.getMake() + "', '"
                    + vehicle.getModel() + "', '"
                    + vehicle.getColor() + "', "
                    + vehicle.getYear() + ", "
                    + vehicle.getPrice() + ", '"
                    + (vehicle instanceof Car ? "Car" : "Motorcycle") + "', '"
                    + (vehicle instanceof Motorcycle ? ((Motorcycle) vehicle).getHandlebarType() : null) + "');";
            return DBManager.getInstance().runUpdate(query) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteVehicle(int id) {
        try {
            String query = "DELETE FROM vehicles WHERE id = " + id;
            return DBManager.getInstance().runUpdate(query) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
