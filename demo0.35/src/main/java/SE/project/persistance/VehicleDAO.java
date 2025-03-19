package SE.project.persistance;

import SE.project.carDealership.Car;
import SE.project.carDealership.Motorcycle;
import SE.project.carDealership.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAO implements DAOInterface<Vehicle> {

    public VehicleDAO() {
        createTable();
    }

    // Create the table if it does not exist
    private void createTable() {
        String vehiclesSQL = "CREATE TABLE IF NOT EXISTS vehicles ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "make TEXT NOT NULL, "
                + "model TEXT NOT NULL, "
                + "color TEXT NOT NULL, "
                + "year INTEGER NOT NULL, "
                + "price REAL NOT NULL, "
                + "carType TEXT, "
                + "handlebarType TEXT,"
                + "inInventory BOOLEAN DEFAULT TRUE);";
        // carType and handlebarType can be null because they are only used for cars and
        // motorcycles respectively
        try {
            DBManager.getInstance().runInsert(vehiclesSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Vehicle vehicle) {
        try {
            String query = "INSERT INTO vehicles (make, model, color, year, price, carType, handlebarType, inInventory) VALUES ('"
                    + vehicle.getMake() + "', '"
                    + vehicle.getModel() + "', '"
                    + vehicle.getColor() + "', "
                    + vehicle.getYear() + ", "
                    + vehicle.getPrice() + ", '"
                    + (vehicle instanceof Car ? ((Car) vehicle).getCarType() : null) + "', '"
                    + (vehicle instanceof Motorcycle ? ((Motorcycle) vehicle).getHandlebarType() : null) + "', '"
                    + vehicle.getInInventory()
                    + "');";
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Vehicle> getAll() {
        try {
            String query = "SELECT * FROM vehicles";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                String carType = resultSet.getString("carType");
                String handlebarType = resultSet.getString("handlebarType");
                if (carType != null) {
                    vehicles.add(new Car(
                            resultSet.getInt("id"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("price"),
                            resultSet.getString("carType"),
                            resultSet.getBoolean("inInventory")));
                } else if (handlebarType != null) {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // extra method for changing the inInventory status of a vehicle to false
    // when it is sold
    public static boolean removeFromInventory(int id) {
        try {
            String query = "UPDATE vehicles SET inInventory = FALSE WHERE id = " + id;
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
