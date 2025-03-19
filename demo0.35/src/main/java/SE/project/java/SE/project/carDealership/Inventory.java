package SE.project.carDealership;

import java.util.ArrayList;
import SE.project.Main;
import SE.project.persistance.DAOInterface;
import SE.project.persistance.VehicleDAO;
import java.sql.SQLException;

public class Inventory {
    private DAOInterface<Vehicle> vehicleDAO;
    private ArrayList<Vehicle> vehicles;

    public Inventory() {
        this.vehicleDAO = new VehicleDAO();
        this.vehicles = vehicleDAO.getAll();
    }

    // the number of all vehicles
    public int getTotalVehicleCount() {
        return vehicles.size();
    }

    // the number of vehicles that inInventory is true
    public int getTotalVehicleCountInInventory() {
        int count = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getInInventory()) {
                count++;
            }
        }
        return count;
    }

    // getInventoryGrossValue()
    public double getInventoryGrossValue() {
        double grossValue = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getInInventory()) {
                grossValue += vehicle.getPrice();
            }
        }
        return grossValue;
    }

    // addVehicle()
    // when add a vehicle, check if the inventory is full, if not, add the vehicle
    public boolean addVehicle(Vehicle vehicle){
		Dealership dealership = new Dealership();
		//dealership.exists();
        int capacity = dealership.getCapacity();
		System.out.println("Capacity: " + capacity);
        if (getTotalVehicleCountInInventory() >= capacity) {
            System.out.println("The inventory is full. Cannot add the vehicle.");
            return false; // Indicate failure to add the vehicle
        }
        if (vehicleDAO.insert(vehicle)) {
            vehicles.add(vehicle);
            return true; // Indicate success
        }
        return false; // Indicate failure to add the vehicle
		
    }

    public Vehicle searchVehicleByID(int ID) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == ID) {
                return vehicle;
            }
        }
        return null;
    }

    // removeFromInventory()
    // update database and in-memory list
    public boolean removeFromInventory(Vehicle vehicle) {
        if (VehicleDAO.removeFromInventory(vehicle.getId())) {
            vehicles.removeIf(v -> v.getId() == vehicle.getId());
            System.out.println("Vehicle removed from inventory.");
            return true;
        }
        System.out.println("Vehicle NOT removed from inventory. Please try again.");
        return false;
    }

    public void sellVehicle(int vehicleID, String buyerName, String buyerContact) {
        Sale sale = new Sale(vehicleID, buyerName, buyerContact);
        if (removeFromInventory(searchVehicleByID(vehicleID))) {
            sale.save();
        }
    }

    public String[] displayAlls() {
        String[] displayStrings = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            displayStrings[i] = vehicles.get(i).toString();
			System.out.println(displayStrings[i]);
        }
        return displayStrings;
    }
	
	public String[] getAllMakes(){
		//return all distint makes
		return(VehicleDAO.getAllMakes());
		
	}
	
	public String[] getAllModels(){
		//return all distint models
		return(VehicleDAO.getAllModels());
		
	}
	public String[] getAllColors(){
		//return all distint colors
		return(VehicleDAO.getAllColors());
		
	}
	
	public int getMinYear(){
		//return old vehicle year
		return(VehicleDAO.getMinYear());
		
	}
	
	public int getMaxYear(){
		//return old vehicle year
		return(VehicleDAO.getMaxYear());
		
	}
	
	public int getMinPrice(){
		//return old vehicle Price
		return(VehicleDAO.getMinPrice());
		
	}
	
	public int getMaxPrice(){
		//return old vehicle Price
		return(VehicleDAO.getMaxPrice());
		
	}
}
