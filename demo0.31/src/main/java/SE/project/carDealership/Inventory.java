package SE.project.carDealership;

import SE.project.persistance.VehicleDAO;
import java.sql.SQLException;
import java.util.List;

public class Inventory {
    private VehicleDAO vehicleDAO;
    private List<Vehicle> vehicles;
    private int totalVehicles;
    private double inventoryGrossValue;

    public Inventory() {
        try {
            this.vehicleDAO = new VehicleDAO();
            this.vehicles = vehicleDAO.getAllVehicles();
            this.totalVehicles = vehicleDAO.countVehicles();
            this.inventoryGrossValue = vehicleDAO.calculateTotalValue();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception, e.g., log it or rethrow it as a runtime exception
        }
    }

    public int getTotalVehicles() {
        return totalVehicles;
    }

    public double getInventoryGrossValue() {
        return inventoryGrossValue;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Vehicle getVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            return vehicles.get(index);
        }
        return null;
    }

    public Vehicle getVehicleFromId(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    public int getIndexFromId(int id) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicleDAO.insertVehicle(vehicle)) {
            vehicles.add(vehicle);
            totalVehicles++;
            inventoryGrossValue += vehicle.getPrice();
            return true;
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        if (vehicleDAO.deleteVehicle(vehicle.getId())) {
            vehicles.removeIf(v -> v.getId() == vehicle.getId());
            totalVehicles--;
            inventoryGrossValue -= vehicle.getPrice();
            return true;
        }
        return false;
    }

    public boolean sellVehicle(Vehicle vehicle, String buyerName, String buyerContact) {
        Sale sale = new Sale(vehicle, buyerName, buyerContact, java.time.LocalDate.now());
        sale.save();
        return removeVehicle(vehicle);
    }

    public String[] displayAlls() {
        String[] displayStrings = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            displayStrings[i] = vehicles.get(i).toString();
        }
        return displayStrings;
    }
}
