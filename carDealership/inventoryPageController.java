package carDealership;

import java.util.Arrays;
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
import persistance.SaleDAO;

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
	private SaleDAO saleDAO;
	private int[] security;
	private int[] editSecurity;
	private int userID;

	//persistant string variables for filter components.
	private String makeFilter = "";
	private String modelFilter = "";
	private String colorFilter = "";
	private String yearFilter = "";
	private String priceFilter = "";
	private String sorted = "";
	private String searchFilter = "";
	private String displayFilter = "";
	private String qry;
	private String[][] displayInfo;
	private String[] vehicles;
	
public inventoryPageController(int ID, int width, int height){ 
			
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
				System.out.println(Arrays.toString(editSecurity));
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			this.vehicleDAO = new VehicleDAO();
			this.saleDAO = new SaleDAO();
			inventory = new inventoryPage(this, width, height);
		}
	
	public void inventoryRefresh(){
		//will refresh the displayed inventory anytime a filter or display option is clicked
	}

	public String[] getAllDisplayInfo(){
		//System.out.println("GEETALL CALLED: " + qry);
		if(qry != null){
			displayInfo = vehicleDAO.getAllDisplayInfoQ(qry);
			vehicles = new String[vehicleDAO.getNumberToShow(qry)];
			//System.out.println("number to show: " + vehicleDAO.getNumberToShow(qry));
		}else{
			displayInfo = vehicleDAO.getAllDisplayInfo();
			vehicles = new String[getTotalVehiclesInInventory()];
			}
		
		int x = 0;
		for(String[] vehicle: displayInfo){
			if(vehicle[6] != null){
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
		this.numberToShow = displayInfo.length;
		return vehicles;
		
	}

public int getNumbertoDisplay(){
		//return number that will be shown after filters apply;
		if(qry != null){
			this.numberToShow = vehicleDAO.getNumberToShow(qry);
		}else{
			this.numberToShow = getTotalVehiclesInInventory();
		}
		return numberToShow;
	}

public String getFilterDisplay(){ 
		     // Base query
			 
    qry = "SELECT * FROM vehicles";
    
	//if (searchFilter.isEmpty()){

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
			
			if (!displayFilter.isEmpty()) {
				if (whereClause.length() > 0) whereClause.append(" AND ");
				whereClause.append(displayFilter);  
				//System.out.println(qry);
			}
			
			// Add the WHERE clause if it's not empty
			if (whereClause.length() > 0) {
				whereClause.append(" AND inInventory = 'true'");
				qry += " WHERE " + whereClause.toString().trim();
				//System.out.println(qry);
			}

			// Add the ORDER BY clause if sorted is not empty
			if (!sorted.isEmpty()) {
				qry += " ORDER BY " + sorted;  // Append ORDER BY condition
			}
	//}else{
		//qry += " WHERE " + searchFilter; 
	//}
	System.out.println("qry: " + qry);
	filterFilter();
	
    return qry;
	
}
	public void filterFilter(){ 
			if(!searchFilter.isEmpty()){inventory.searchRemove();}
			if (makeFilter.isEmpty()) {
				inventory.refreshMakes();
			}
			
			if (modelFilter.isEmpty()) {
				inventory.refreshModels(); 
			}
			
			if (colorFilter.isEmpty()) {
				inventory.refreshColors(); 
			}
			if (yearFilter.isEmpty()) {
				inventory.refreshYears();
			}
			if(priceFilter.isEmpty()){
				inventory.refreshPrices();
			}
			if (!displayFilter.isEmpty()) {
			}
		inventory.ignorestateChanger();	
	}
	
	public void filterMakes(String makes){
 		if (makes == null || makes.isEmpty()) {
        makeFilter = "";
		}else{
			makeFilter = " make IN(" + makes + ") ";
		}
	}
	
	public void getDisplayDisplay(String display){
		//System.out.println("display" + display);
		switch(display){
			case "car":
				displayFilter = " carType is not null ";
				break;
			case "motorcycle":
				displayFilter = " handlebarType is not null ";
				break;
			case "all":
				displayFilter = "";
				break;
					
		}
				
	}
	
	public String[] getFilteredMakes(){
		String[] filteredMakes = vehicleDAO.getFilteredMakes(qry);
		return filteredMakes;
	}
	
	public String[] getFilteredModels(){
		String[] filteredModels = vehicleDAO.getFilteredModels(qry);
		return filteredModels;
	}
	
	public String[] getFilteredColors(){
		String[] filteredColors = vehicleDAO.getFilteredColors(qry);
		return filteredColors;
	}
	
	public int[] getFilteredYears(){
		int[] filteredYears = vehicleDAO.getFilteredYears(qry);
		return filteredYears;
	}
	
	public int[] getFilteredPrice(){
		int[] filteredPrice = vehicleDAO.getFilteredPrice(qry);
		return filteredPrice;
	}
	
	public void search(String search){
		  // Check if the search string contains only digits
		  if (search.matches("\\d+")) {  // \\d+ matches one or more digits
			inventory.checkDemark();
			searchFilter = " id = " + search + " ";  
			qry = "SELECT * FROM vehicles WHERE" + searchFilter + "AND inInventory = 'true'";
			
		}
		else {
			// Show an error message if input is invalid
			getFilterDisplay();
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a vehicle ID number.");
		}
	}
	
	public void searchExit(){
		searchFilter = "";
		getFilterDisplay();
		
	}
	
	public void filterModels(String models){
		if (models == null || models.isEmpty()) {
			modelFilter = "";
			}else{
				modelFilter = " model IN(" + models + ") ";
			}
	}
	
	public void filterColors(String colors){
		if (colors == null || colors.isEmpty()) {
			colorFilter = "";
			}else{
				colorFilter = " color IN(" + colors +") ";
			}
	}
	
	public void filterYears(int minyear, int maxyear){
		if (minyear == 0 || maxyear == 0) {
			yearFilter = "";
			}else{
				yearFilter = " year >= " + minyear + " AND year <= " + maxyear + " ";
			}
	}
	
	public void filterPrice(int minPrice, int maxPrice){
		if (minPrice == 0 || maxPrice == 0) {
			priceFilter = "";
			}else{
				priceFilter = " price >= " + minPrice + " AND price <= " + maxPrice + " ";
		}
	}

	public void sortMenuSelect(int sortSel){
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
		getFilterDisplay();
	}

	public int getTotalVehiclesInInventory() {
		//currently in Dealership.java
		//rerout to VehicleDAO.java
		int count = vehicleDAO.getTotalVehiclesInInventory();
		return(count);//add actually query here
	}
		
	public String getInventoryGrossValue(){
		//return value of all vehicles in inventory
		//currently in Dealership.java
		//rerout to Vehicle
		//return((int)Main.m_dealership.getInventoryGrossValue()); 
		String value = vehicleDAO.getGrossValue();
		return(value);
		
	}
	
	
	public String[] getMakes(){
		//return distinct makes
		//reroute to Vehicle or keep here

		String[] makes = vehicleDAO.getAllMakes();
		return makes;
	}
	
	public String[] getModels(){
		//return distinct models
		//reroute to Vehicle or keep here

		String[] models = vehicleDAO.getAllModels();
		return models;
		
	}
	
	public String[] getColors(){
		//return distinct models
		//reroute to Vehicle or keep here
		String[] colors = vehicleDAO.getAllColors();
		return colors;
		
	}
	
	public int getMinYear(){
		//get year of oldest car
		//reroute to Vehicle or keep here
		int minYear = vehicleDAO.getMinYear();
		return minYear;
	}
	
	public int getMaxYear(){
		//get year of oldest car
		//reroute to Vehicle or keep here
		int maxYear = vehicleDAO.getMaxYear();
		return maxYear;
	}
	
	public int getMinPrice(){
		//get Price of oldest car
		//reroute to Vehicle or keep here
		int minPrice = vehicleDAO.getMinPrice();
		return minPrice;
	}
	
	public int getMaxPrice(){
		//get Price of oldest car
		//reroute to Vehicle or keep here
		int maxPrice = vehicleDAO.getMaxPrice();
		return maxPrice;
	}
	
	public void setDisabledEdits(DefaultListSelectionModel ddb){
		if(editSecurity.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(4, 4);
			
		}
	}
	
	public void setDisabledPages(DefaultListSelectionModel ddb){
		if(security.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(0, 3);
			ddb.addSelectionInterval(5, 5);
		}
	}
	
	public void pageMenuSelect(int sel, JFrame mainFrame, int w, int h){
		boolean contains = false;
		
		for(int page: security){
			if(sel == page)
				contains = true;
		}
		
		if(contains){
			if (sel== 1){
				inventoryPageController inv = new inventoryPageController(userID,w,h);
				mainFrame.dispose();
			}else if (sel== 2){
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 3){
				new pastSalesPageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 4){
				new accountManagePageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 5){
				new loginPageController();
				mainFrame.dispose();
			}
		}
	}
	public void editMenuSelect(int sel){
		boolean contains = false;
		
		for(int edit: editSecurity){
			//System.out.println(edit);
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
		//System.out.println("controller" + exsist);
		if (exsist == false) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return false;
		}else{
			return true;
		}
	}
	
	public String[] getVehicleFromId(int id){
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
		boolean sold = saleDAO.insert(vehicle,buyerName, buyerContact);
		boolean notin = false;
		if(sold){notin = vehicleDAO.sellVehicle(vehicle);}
		return notin;
		
	}
	
	public boolean removeVehicle(Vehicle vehicle){
		//return if able to successfully removeVehicle
		Main.m_dealership.removeVehicle(vehicle);
		return true;
	}
	
	public boolean addCar(String make, String model, String color, int year, int price, String type){
		//return true if successfully added
		boolean added = vehicleDAO.addCar(make, model, color, year, price, type);
		return added;
	}

	public boolean addMotorcycle(String make, String model, String color, int year, int price, String handlebarType){
		//return true if motorcycle is successfully added
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