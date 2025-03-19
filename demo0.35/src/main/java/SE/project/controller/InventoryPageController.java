package SE.project.controller;

import SE.project.carDealership.Inventory;
import SE.project.carDealership.Vehicle;

public class InventoryPageController {

    private Inventory inventory;

    public InventoryPageController() {
        this.inventory = new Inventory();
    }

    public int getTotalVehicleCount() {
        return inventory.getTotalVehicleCount();
    }

    public int getTotalVehicleCountInInventory() {
        return inventory.getTotalVehicleCountInInventory();
    }

    public double getInventoryGrossValue() {
        return inventory.getInventoryGrossValue();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return inventory.addVehicle(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.removeFromInventory(vehicle);
    }

    public Vehicle searchVehicleByID(int id) {
        return inventory.searchVehicleByID(id);
    }

    public void sellVehicle(int vehicleID, String buyerName, String buyerContact) {
        inventory.sellVehicle(vehicleID, buyerName, buyerContact);
    }

    public String[] displayAllVehicles() {
        return inventory.displayAlls();
    }
}
