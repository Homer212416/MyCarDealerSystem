package carDealership;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Date;

import carDealership.Car;
import carDealership.Main;
import carDealership.Motorcycle;
import carDealership.User;
import carDealership.accountManagePageController;
import carDealership.dealerShipInfoPageController;
import carDealership.loginPageController;
import carDealership.pastSalesPageController;

import java.sql.SQLException;
import java.sql.*;
import persistance.VehicleDAO;

public class inventoryPageController{
	private static List<Integer> pageAccess;
	private static List<Integer> editAccess;
	private int numberToShow;
	private inventoryPage inventory;
	private User user;
	private VehicleDAO vehicleDAO;
	private int[] security;
	private int editSecurity;
	private int userID;

	//persistant string variables for filter components.
	private String makeFilter = "";
	private String modelFilter = "";
	private String colorFilter = "";
	private String yearFilter = "";
	private String priceFilter = "";
	private String sortOrder = "";
	
	
public inventoryPageController(int ID){ 
			
			JComboBox pageMenuDD = inventoryPage.pageMenuDD;
			this.numberToShow=0;
			user = new User();
			//set up user ID retrieval or pass between each controller from LoginContext
			//but for testing am just using 1
			//ID = user.getUserID();
			this.userID = ID;
			//getPage Security and editSecurity Info
			try{
				security = user.getPageSecurity(userID);
				editSecurity = user.getEditSecurity(userID);
				//for(int each : security){System.out.print(each +", ");}
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			this.vehicleDAO = new VehicleDAO();
			inventory = new inventoryPage(this);
		}
	
	public void inventoryRefresh(){
		//will refresh the displayed inventory anytime a filter or display option is clicked
	}

	public String[] getAllDisplayInfo(){
		String[][] displayInfo = vehicleDAO.getAllDisplayInfo();
		String[] vehicles = new String[displayInfo.length];
		int x = 0;
		for(String[] vehicle: displayInfo){
			if(vehicle[7] != null){
				String vehicleInfo = ("ID: " + vehicle[0] + "\n" 
					+ "Make: " + vehicle[1] + "\n"
					+ "Model: " + vehicle[2] + "\n"
					+ "Color: " + vehicle[3] + "\n" 
					+ "Year: " + vehicle[4] + "\n" 
					+ "Price: " + vehicle[5] + "\n"
					+ "Type: " + vehicle[6] + "\n"); 
				vehicles[x] = vehicleInfo;
				x++;
			}else{
				String vehicleInfo = ("ID: " + vehicle[0] + "\n" 
					+ "Make: " + vehicle[1] + "\n"
					+ "Model: " + vehicle[2] + "\n"
					+ "Color: " + vehicle[3] + "\n" 
					+ "Year: " + vehicle[4] + "\n" 
					+ "Price: " + vehicle[5] + "\n"
					+ "Handlebar Type: " + vehicle[7] + "\n"); 
				vehicles[x] = vehicleInfo;
				x++;
				}
			}
		
	
		//currently in Dealership.java
		//rerout to VehicleDAO
		/*
		get all car that should show in inventory based on filters
		if search diplay is null
		get intersection of getFilterDisplay and getDiplayDisplay
		*/
		
		this.numberToShow = displayInfo.length;
		return vehicles;
		
	}

public int getNumbertoDisplay(){
		//return number that will be shown after filters apply;
		if(this.numberToShow == 0){
			this.numberToShow = getAllDisplayInfo().length;
		}
		return numberToShow;
	}

	public String getFilterDisplay(){ 
		     // Base query
			 System.out.println ("It worked");
    String qry = "SELECT * FROM Inventory";
    
    // Initialize the WHERE clause
    StringBuilder whereClause = new StringBuilder();
    
    // Add conditions if filters are not empty
    if (!makeFilter.isEmpty()) {
        if (whereClause.length() > 0) whereClause.append(" AND ");
        whereClause.append(makeFilter);  
    }
    
    if (!modelFilter.isEmpty()) {
        if (whereClause.length() > 0) whereClause.append(" AND ");
        whereClause.append(modelFilter);  
    }
    
    if (!colorFilter.isEmpty()) {
        if (whereClause.length() > 0) whereClause.append(" AND ");
        whereClause.append(colorFilter);  
    }
    
    if (!yearFilter.isEmpty()) {
        if (whereClause.length() > 0) whereClause.append(" AND ");
        whereClause.append(yearFilter);  
    }
    
    if (!priceFilter.isEmpty()) {
        if (whereClause.length() > 0) whereClause.append(" AND ");
        whereClause.append(priceFilter);  
    }
    
    // Add the WHERE clause if it's not empty
    if (whereClause.length() > 0) {
        qry += " WHERE " + whereClause.toString().trim();
    }

    // Add the ORDER BY clause if sortOrder is not empty
    if (!sortOrder.isEmpty()) {
        qry += " ORDER BY " + sortOrder;  // Append ORDER BY condition
    }

    return qry;
}

	public void filterMakes(String makes){
 		if (makes == null || makes.isEmpty()) {
        makeFilter = "";
		}else{
			makeFilter = " make IN(makes) ";
		}
	}
	
	public String getDisplayDisplay(String display){return display;}
	
	public String search(String search){
		return search;
	}
	
	
	public String filterModels(String models){return models;}
	
	public String filterColors(String colors){return colors;}
	
	public int filterYears(int minyear, int maxyear){return minyear;}
	
	public int filterPrice(int minPrice, int maxPrice){return minPrice;}
	
	public String sortMenuSelect(int sortSel){
		String sorted = "";
		switch(sortSel){
			case 1:
				sorted = "price DESC";
				break;
			case 2:
				sorted = "price ASC";
				break;
			case 3:
				sorted = "make";
				break;
			case 4:
				sorted = "model";
				break;	
			case 5:
				sorted = "year DESC";
				break;
			case 6:
				sorted = "year ASC";
				break;	
		}
		return sorted;
	}

	public int getTotalVehiclesInInventory() {
		//currently in Dealership.java
		//rerout to VehicleDAO.java
		int count = vehicleDAO.getTotalVehiclesInInventory();
		return(count);//add actually query here
	}
		
	public static int getInventoryGrossValue(){
		//return value of all vehicles in inventory
		//currently in Dealership.java
		//rerout to Vehicle
		return((int)Main.m_dealership.getInventoryGrossValue()); 
		
	}
	
	
	public static String[] getMakes(){
		//return distinct makes
		//reroute to Vehicle or keep here
		String[] makes = {"1", "2"};
		return makes;
		
	}
	
	public static String[] getModels(){
		//return distinct models
		//reroute to Vehicle or keep here
		String[] models = {"a", "b"};
		return models;
		
	}
	
	public static String[] getColors(){
		//return distinct models
		//reroute to Vehicle or keep here
		String[] colors = {"r", "y"};
		return colors;
		
	}
	
	public static int getMinYear(){
		//get year of oldest car
		//reroute to Vehicle or keep here
		int minYear = 1960;
		return minYear;
	}
	
	public static int getMaxYear(){
		//get year of oldest car
		//reroute to Vehicle or keep here
		int maxYear = 2005;
		return maxYear;
	}
	
	public static int getMinPrice(){
		//get Price of oldest car
		//reroute to Vehicle or keep here
		int minPrice = 1960;
		return minPrice;
	}
	
	public static int getMaxPrice(){
		//get Price of oldest car
		//reroute to Vehicle or keep here
		int maxPrice = 2005;
		return maxPrice;
	}
	
	public void setDisabledPages(DefaultListSelectionModel ddb){
		if(editSecurity == 1){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(0, 3);
			ddb.addSelectionInterval(5, 5);
		}
	}
	
	public void setDisabledEdits(DefaultListSelectionModel ddb){
		
		if(security.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(4, 4);
		}
	}
	
	public void pageMenuSelect(int sel, JFrame mainFrame){
		boolean contains = false;
		
		for(int page: security){
			if(sel == page)
				contains = true;
		}
		
		if(contains){
			if (sel== 1){
				inventoryPageController inv = new inventoryPageController(userID);
				mainFrame.dispose();
			}else if (sel== 2){
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID);
				mainFrame.dispose();
			}else if(sel== 3){
				new pastSalesPageController(userID);
				mainFrame.dispose();
			}else if(sel== 4){
				new accountManagePageController(userID);
				mainFrame.dispose();
			}else if(sel== 5){
				new loginPageController();
				mainFrame.dispose();
			}
		}
	}
	public void editMenuSelect(int sel){
		boolean contains = false;
		
		for(int edit: security){
			if(sel == edit)
				contains = true;
		}
		if(contains){
			if (sel== 1){
				inventory.addCarMenu();
			}else if (sel== 2){
				inventory.addMotorcycleMenu();
			}else if(sel== 3){
				inventory.editVehicleMenu();
			}else if(sel== 4){
				inventory.sellVehicleMenu();
			}else if(sel== 5){
				inventory.removeVehicleMenu();
			}
		}
	}
	
	
	
	
	public void getIndexFromId(int id){
		//was in InventoryPage
		//show error message if car does not exsist
		// == false) { // check if exist
	}
	
	public boolean vehicleExsist(int id){
		boolean exsist = vehicleDAO.exsist(id);
		System.out.println("controller" + exsist);
		if (exsist == false) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return false;
		}else{
			return true;
		}
	}
	
	public String[] getVehicleFromId(int id){
		//currently in dealership.java
		//rerout to VehicleDAO.java
		boolean exsist = vehicleDAO.exsist(id);
		if(!exsist){
			JOptionPane.showMessageDialog(null, "Vehicle not found!");
			String [] noVehicle = {};
			return noVehicle;
			}
		else{
			String [] vehicleDetails = vehicleDAO.getIndexFromId(id);
			return vehicleDetails;
		}
	}
	
	public void getVehicleType(Vehicle vehicle, JPanel editPanel){
		
	}
	
	public boolean sellVehicle(int vehicle, String buyerName, String buyerContact){
		boolean sold = vehicleDAO.sellVehicle(vehicle);
		return sold;
		
	}
	
	public boolean removeVehicle(Vehicle vehicle){
		//return if able to successfully removeVehicle
		//currently in dealership.java
		//reroute to VehicleDAO.java
		Main.m_dealership.removeVehicle(vehicle);
		return true;
	}
	
	public boolean addCar(String make, String model, String color, int year, int price, String type){
		//return true if successfully added
		//currently in dealership.java
		//reroute to VehicleDAO.java
		boolean added = vehicleDAO.addCar(make, model, color, year, price, type);
		return added;
	}

	public boolean addMotorcycle(String make, String model, String color, int year, int price, String handlebarType){
		//return true if motorcycle is successfully added
		//currently in dealership.java
		//reroute to VehicleDAO.java
		boolean added = vehicleDAO.addMotorcycle(make, model, color, year, (int) price, handlebarType);
		return added;
	}
	
	public boolean editCar(String[] car){
		boolean editS = vehicleDAO.editCar(car);
		return editS;
	}
	
	public boolean editMotorcycle(String[] Motorcycle){
		boolean editS = vehicleDAO.editMotorcycle(Motorcycle);
		return editS;
	}

	public boolean removeVehicle(int id){
		boolean removed = vehicleDAO.removeVehicle(id);
		return removed;
	}
}