package SE.project.controller;

import SE.project.carDealership.Inventory;
import SE.project.carDealership.Vehicle;
import java.sql.SQLException;

public class InventoryPageController {

    private Inventory inventory;

    public InventoryPageController() {
        this.inventory = new Inventory();
		System.out.println("new InventoryPageController");
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

    public boolean addVehicle(Vehicle vehicle){
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
	
	public String[] getAllMakes(){
		//return distinct makes of vehicles
		return inventory.getAllMakes();
	}
	public String[] getAllModels(){
		//return distinct makes of vehicles
		return inventory.getAllModels();
	}
	public String[] getAllColors(){
		//return distinct makes of vehicles
		return inventory.getAllColors();
	}
	
	public int getMinYear(){
		//return old vehicle year
		return(inventory.getMinYear());
		
	}
	
	public int getMaxYear(){
		//return old vehicle year
		return(inventory.getMaxYear());
		
	}
	
	public int getMinPrice(){
		//return old vehicle Price
		return(inventory.getMinPrice());
		
	}
	
	public int getMaxPrice(){
		//return old vehicle Price
		return(inventory.getMaxPrice());
		
	}
	
}
