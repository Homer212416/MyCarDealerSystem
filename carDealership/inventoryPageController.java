package carDealership;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
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
	
	// public String[] getFilterDisplay(){ get intersection of all filters}
	
	public String getDisplayDisplay(String display){return display;}
	
	// public String[] getSearchDisplay(){}
	
	public String filterMakes(String makes){return makes;}
	
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
	
	public String search(String search){
		return search;
		}
	
	
	public int getTotalVehiclesInInventory() {
		//currently in Dealership.java
		//rerout to VehicleDAO.java
		int count = vehicleDAO.getTotalVehiclesInInventory();
		return(count);//add actually query here
	}
		
	public static void addPageIntervals(){
		inventoryPage.pageMenuModel.addSelectionInterval(0, 5);
		List<Integer> available = new ArrayList<Integer>();
		//add page numbers to list if page is available
		available.add(1);
		//editAccess to be used in actionListener for pageMenuDD
		pageAccess = available;
	}
		
	public static int getInventoryGrossValue(){
		//return value of all vehicles in inventory
		//currently in Dealership.java
		//rerout to Vehicle
		return((int)Main.m_dealership.getInventoryGrossValue()); 
		
	}
	
	public static void addEditIntervals(){
		inventoryPage.pageMenuModel.addSelectionInterval(0, 5);
		List<Integer> available = new ArrayList<Integer>();
		//add page numbers to list if page is available
		available.add(1);
		//editAccess to be used in actionListener for pageMenuDD
		editAccess = available;
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
		/*
		//actions for page drop down
		editInventoryMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			if (editInventoryMenu.getSelectedIndex() == 1){
				addCarMenu();
				mainFrame.repaint(5,0, 0, 665, 665);
			}else if(editInventoryMenu.getSelectedIndex() == 2){
				addMotorcycleMenu();
			}else if(editInventoryMenu.getSelectedIndex() == 3){
				editVehicleMenu();
			}else if(editInventoryMenu.getSelectedIndex() == 4){
				sellVehicleMenu();
			}else if(editInventoryMenu.getSelectedIndex() == 5){
				removeVehicleMenu();
			}
			}
		});
		searchBarBG.add(MagButton, gbcS);
		MagButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
			
		}
		});
*/
	
	
	
	//actions for editInventory Pages///////////////////////////////////////////////////////
	public boolean addVehicle(Vehicle vehicle) {
		//currently in dealership.java
		//reroute to VehicleDAO.java
		//change to if nuumber of vehicles in inventory = capacity
		/*
		if (nv == inventory.length) {
			return false;
		}
		*/

		if (vehicle instanceof Car) {
			//add car
		}

		if (vehicle instanceof Motorcycle) {
			//addcar
		}

		return true;

	}
	
	public void getIndexFromId(int id){
		//was in InventoryPage
		//show error message if car does not exsist
		// == false) { // check if exist
		JOptionPane.showMessageDialog(null, "Vehicle not found!");
		
	}
	
	public boolean vehicleExsist(int id){
		//show error message if car does not exsist
		//JOptionPane.showMessageDialog(null, "Vehicle not found!");
		//doesn't exsist can do here or put in VehicleDAO
		return false;
	}
	
	public Vehicle getVehicleFromId(int id){
		//currently in dealership.java
		//rerout to VehicleDAO.java
		Vehicle vehicle = Main.m_dealership.getVehicleFromId(id);
		return vehicle;
	}
	
	public void getVehicleType(Vehicle vehicle, JPanel editPanel){
		//currently in inventoryPage
		//move all nonUI elements here
		/*
		JTextField makeField = new JTextField();
		JTextField modelField = new JTextField();
		JTextField colorField = new JTextField();
		JTextField yearField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField typeField = new JTextField();
		JTextField handlebarField = new JTextField();
			
		if (vehicle instanceof Car) {
				Car car = (Car) vehicle;

				editPanel.add(new JLabel("Make:"));
				makeField.setText(car.getMake());
				editPanel.add(makeField);

				editPanel.add(new JLabel("Model:"));
				modelField.setText(car.getModel());
				editPanel.add(modelField);

				editPanel.add(new JLabel("Color:"));
				colorField.setText(car.getColor());
				editPanel.add(colorField);

				editPanel.add(new JLabel("Year:"));
				yearField.setText(String.valueOf(car.getYear()));
				editPanel.add(yearField);

				editPanel.add(new JLabel("Price:"));
				priceField.setText(String.valueOf(car.getPrice()));
				editPanel.add(priceField);

				editPanel.add(new JLabel("Type:"));
				typeField.setText(car.getType());
				editPanel.add(typeField);

			} else if (vehicle instanceof Motorcycle) {
				Motorcycle motorcycle = (Motorcycle) vehicle;

				editPanel.add(new JLabel("Make:"));
				makeField.setText(motorcycle.getMake());
				editPanel.add(makeField);

				editPanel.add(new JLabel("Model:"));
				modelField.setText(motorcycle.getModel());
				editPanel.add(modelField);

				editPanel.add(new JLabel("Color:"));
				colorField.setText(motorcycle.getColor());
				editPanel.add(colorField);

				editPanel.add(new JLabel("Year:"));
				yearField.setText(String.valueOf(motorcycle.getYear()));
				editPanel.add(yearField);

				editPanel.add(new JLabel("Price:"));
				priceField.setText(String.valueOf(motorcycle.getPrice()));
				editPanel.add(priceField);

				editPanel.add(new JLabel("Handlebar Type:"));
				handlebarField.setText(motorcycle.getHandlebarType());
				editPanel.add(handlebarField);
			}
			*/
	}
	
	public boolean sellVehicle(Vehicle vehicle, String buyerName, String buyerContact){
		//return if able to successfully sellVehicle
		//currently in dealership.java
		//reroute to SaleDAO.java
		Main.m_dealership.sellVehicle(vehicle, buyerName, buyerContact);
		return true;
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
		Main.m_dealership.addVehicle(new Car(make, model, color, year, price, type));
		return true;
	}

	public boolean addMotorcycle(String make, String model, String color, int year, int price, String handlebarType){
		//return true if motorcycle is successfully added
		//currently in dealership.java
		//reroute to VehicleDAO.java
		Main.m_dealership.addVehicle(new Motorcycle(make, model, color, year, price, handlebarType));
		return true;
	}

}