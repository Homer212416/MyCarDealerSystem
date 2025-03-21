package carDealership;

import java.util.Scanner;

import persistance.DealershipLayer;
import persistance.UserLayer;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class inventoryPage{
	private static final long serialVersionUID = -4235592661347719465L;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private int minyear = 1900;
	private int maxyear = 2025;
	private double minPrice = 0;
	private double maxPrice = 50000;
	private JPanel vehicle;
	private JPanel inventoryBG;
	private JPanel filterBG;
	private GridBagConstraints gbcF;
	private GridBagConstraints gbcI;
	private JScrollPane filterScroll;
	private inventoryPageController controller;
	public static DefaultListSelectionModel pageMenuModel;
	public static JComboBox pageMenuDD;
	
	//page elements
	private static String[] sortMenuElements;
	private static String[] pageElements;
	private static String[] editInventoryElements;
	private static Image carIconImage;
	private static Image newCarImage;
	public Color bgColor = new Color(230, 230, 230);
	
	public inventoryPage(inventoryPageController controller){
		//this.controller = new inventoryPageController();
		carIconImage = (Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		newCarImage = carIconImage.getScaledInstance(80, 70,Image.SCALE_DEFAULT);
		sortMenuElements = new String[]{"Sort By","Price Descending" ,"Price Ascending", "Make","Model", "Year Descending","Year Ascending" };	
		pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		editInventoryElements = new String[]{"", "Add Car", "Add Motorcycle", "Edit Vehicle", "Sell vehicle","Remove Vehicle"};
		this.controller = controller;
		prepareInventoryGUI();
		
	}
	public static void main(String[] args){
		//inventoryPage inventoryPage = new inventoryPage();
		//inventoryPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		//set standard font 
		UIManager.put("Label.font", new Font("HP Simplified Hans", Font.BOLD, 12));
        UIManager.put("Button.font", new Font("HP Simplified Hans", Font.BOLD, 12));
        UIManager.put("TextField.font", new Font("HP Simplified Hans", Font.BOLD, 12));
		//create window called mainFrame
		mainFrame = new JFrame("Inventory");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 665, 665);
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});
		
		///create layout of panels/////////////////////////////////////////////////////////
		controlPanel = new JPanel();
		controlPanel.setBackground(bgColor);
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 665, 665);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JLabel carImage = new JLabel("");
		carImage.setBackground(Color.BLACK);
		carImage.setOpaque(true);
		carImage.setIcon(new ImageIcon(inventoryPage.class.getResource("/images/backgroundd.jpg")));
		carImage.setBounds(0, -50, 650, 200);
		controlPanel.add(carImage);
		
		JPanel searchBarBG = new JPanel();
		searchBarBG.setBackground(Color.GRAY);
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();
		searchBarBG.setBounds(0, 150, 650, 50);
		controlPanel.add(searchBarBG);
		
		JPanel displayBG = new JPanel();
		displayBG.setBackground(bgColor);
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(10, 200, 440, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);
		////////////////////////////////////////////////////////////////////////////////
		
		//////////create page menu drop down///////////////////////////////////////////
			pageMenuDD = new JComboBox();
			for(String element : pageElements){
				pageMenuDD.addItem(element);
			}
			pageMenuModel = new DefaultListSelectionModel();
			EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);
			pageMenuDD.setRenderer(pageMenuEnableRender);
			pageMenuDD.setBounds(450, 20, 200, 25);
			controlPanel.add(pageMenuDD);
		//grey out menuItems the user does not have access to and get access list for pages
		//controller.addPageIntervals();
		//////////////////////////////////////////////////////////////

		//////////inventory information/////////////////////////////////////////////////////
		int size = controller.getTotalVehiclesInInventory();
		JLabel cap = new JLabel("Size: " + size);
		cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		cap.setBounds(30, 600, 200, 25);
		controlPanel.add(cap);
		
		int totalValue = controller.getInventoryGrossValue();
		JLabel totalV = new JLabel("Total Inventory Value: " + totalValue);
		totalV.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		totalV.setBounds(240, 600, 200, 25);
		controlPanel.add(totalV);
		
		////////////////////////////////////////////////////////////////
		
		//////edit inventory drop down menu///////////////////////////////////////////////////////////
		JComboBox editInventoryMenu= new JComboBox();
		for(String element : editInventoryElements){
			editInventoryMenu.addItem(element);
			}
		DefaultListSelectionModel editInventoryModel = new DefaultListSelectionModel();
		EnabledJComboBoxRenderer editInventoryEnableRender = new EnabledJComboBoxRenderer(editInventoryModel);
		editInventoryMenu.setRenderer(editInventoryEnableRender);
		editInventoryMenu.setBounds(450, 20, 200, 25);
		controlPanel.add(editInventoryMenu);
		//set permissions for editInventoryMenu drop down
		//controller.addEditIntervals();
		
		editInventoryMenu.setSelectedIndex(0);
		gbcS.anchor = GridBagConstraints.WEST;  
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		gbcS.weightx = .2;
		searchBarBG.add(editInventoryMenu, gbcS); 
		
		//editlabel
		gbcS.anchor = GridBagConstraints.WEST;
		JLabel editLabel = new JLabel("Edit Inventory: ");
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		gbcS.weightx = 0;
		gbcS.insets = new Insets(0,10,0,0);
		searchBarBG.add(editLabel, gbcS); 
		//////////////////////////////////////////////////////////////////
			
		///////search bar and buttonn///////////////////////////////////////////////////////////////
		//search bar on right hand side
		JTextField searchBar  = new JTextField("Search ID", 15);
		searchBar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		gbcS.insets = new Insets(0,0,0,0);
		gbcS.fill = GridBagConstraints.VERTICAL;	
		searchBarBG.add(searchBar, gbcS);
		
		//searchButton
		JButton MagButton = new JButton("S");
		gbcS.insets = new Insets(0,0,0,10);
		gbcS.gridx = 6;
		gbcS.gridy = 0;
		
		///////////////////////////////////////////////////////////////////
		
		////////display options///////////////////////////////////////////////////////////////
		gbcD.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox dAll = new JCheckBox("Display All");
		dAll.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		dAll.setBackground(bgColor);
		final JCheckBox dMoto = new JCheckBox("Motocycles Only");
		dMoto.setBackground(bgColor);
		dMoto.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		final JCheckBox dCar = new JCheckBox("Cars Only");
		dCar.setBackground(bgColor);
		dCar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		gbcD.gridwidth = 1;
		gbcD.gridx = 0;
		gbcD.gridy = 0;
		displayBG.add(dAll, gbcD);
		gbcD.gridx = 1;
		gbcD.gridy = 0;
		displayBG.add(dMoto, gbcD);
		gbcD.gridx = 2;
		gbcD.gridy = 0;
		gbcD.ipady = 0;
		gbcD.ipadx = 10;
		displayBG.add(dCar, gbcD);
		
		gbcD.anchor = GridBagConstraints.NORTHEAST;
		final JComboBox sortMenu = new JComboBox();
		//System.out.println(sortMenuElements);
		for(String element : sortMenuElements){
			sortMenu.addItem(element);
		}
		sortMenu.setSelectedIndex(0);
		sortMenu.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		gbcD.gridwidth = 1;
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.ipadx = 5;
		gbcD.weightx = .1;
		displayBG.add(sortMenu, gbcD);
		
		/////////////////////////////////////////////////////////////////
	
		//create inventory list
		refreshInventory();
		mainFrame.setContentPane(controlPanel);
		//mainFrame.setVisible(true);
		
	}
	
	private void refreshInventory(){
		//inventory image list
		
		
		//create panels for layout////////////////////////////
		inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 225, 440, 375);
		
		filterBG = new JPanel();
		filterBG.setBackground(bgColor);
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setPreferredSize(new Dimension(200, 365));
		gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);
		//make filter a scrollpane when needed
		filterScroll = new JScrollPane(filterBG, JScrollPane. VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		filterScroll.setBounds(450, 227, 200, 370);
		controlPanel.add(filterScroll);
		
		mainFrame.validate();
		//vehicleInv is where the inventory will show on screen
		JPanel vehicleInv = new JPanel();
		vehicleInv.setBackground(Color.WHITE);
		vehicleInv.setBounds(10, 225, 440, 375);
		GridBagLayout layoutvehicleInv = new GridBagLayout();
		vehicleInv.setLayout(layoutvehicleInv);
		GridBagConstraints gbcvehicleInv = new GridBagConstraints();
		gbcvehicleInv.insets = new Insets(5,5,5,5);
		gbcvehicleInv.anchor = GridBagConstraints.NORTHWEST;
		
		
		myMouseListener mml = new myMouseListener();
		
		int numberToShow = controller.getNumbertoDisplay();
		//create an panel for each vehicle that should be shown and name///////////////////////
		
		
		JPanel[] vehiclePanelNames= new JPanel[numberToShow];
		System.out.println(numberToShow);
		for (int i = 0; i < numberToShow; i++) {
			String num = Integer.toString(i);
			JPanel vehicle = new JPanel();
			//add each panel to the array
			vehiclePanelNames[i] = vehicle;
			//name each panel so that can be called later if needed
			vehicle.setName("car" + i);
		}
		
		String[] vehicles = controller.getAllDisplayInfo();
		for (int i = 0; i < numberToShow ; i++) {
			//set standard layout for each panel/////
			vehiclePanelNames[i].setBackground(Color.WHITE);
			vehiclePanelNames[i].setLayout(new GridBagLayout());
			GridBagLayout layoutC1 = new GridBagLayout();
			vehiclePanelNames[i].setLayout(layoutC1);
			GridBagConstraints gbcC1 = new GridBagConstraints();
			//////////////////////////////////////////
			
			//add image to each panel/////////////////////
			JLabel c1Img = new JLabel("");
			c1Img.setIcon(new ImageIcon(newCarImage));
			gbcC1.gridx = 0;
			gbcC1.gridy = 0;
			gbcC1.anchor = GridBagConstraints.NORTHWEST;
			vehiclePanelNames[i].add(c1Img, gbcC1);
			///////////////////////////////////////////////
			
			//add text description to each panel/////////////
			JTextArea c1Info = new JTextArea(6, 20);
			c1Info.setForeground(Color.BLACK);
			c1Info.setText(vehicles[i]);
			c1Info.setWrapStyleWord(true);
			c1Info.setLineWrap(true);
			c1Info.setOpaque(false);
			c1Info.setEditable(false);
			c1Info.setFocusable(false);
			gbcC1.anchor = GridBagConstraints.NORTH;
			gbcC1.gridx = 0;
			gbcC1.gridy = 1;
			gbcC1.weightx = .2;
			gbcC1.weighty = 1;
			vehiclePanelNames[i].add(c1Info, gbcC1); 
			
			//have 2 vehicles per row
			gbcvehicleInv.gridx = (int)Math.floor(i%2);
			gbcvehicleInv.gridy = (int)Math.floor(i/2);
			//add weight to last entry, you need weight to get anchor to work
			if(i == controller.getNumbertoDisplay()){
				gbcvehicleInv.weightx = .9;
				gbcvehicleInv.weighty = 1;
			}
			//////////////////////////////////////////////
			
			//add vehicles to the scrollpane that holds the inventory
			vehicleInv.add(vehiclePanelNames[i], gbcvehicleInv);
			vehiclePanelNames[i].setFocusable(true);
			vehiclePanelNames[i].addMouseListener(mml);
			///////////////////////////////////
		}
		
		//adds vehicle Inventory list to a scroll pane
		JScrollPane inventoryListScrollPane = new JScrollPane(vehicleInv);   
		inventoryListScrollPane.setPreferredSize(new Dimension(440, 375));		
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcI.ipady = 0;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5,0,0,0);
		inventoryBG.add(inventoryListScrollPane, gbcI);
		//////////////////////////////////////////////////////////////////////////
		
		//filter setup/////////////////////////////////////////////////////////
		//make filter//////////////////////////
		int yBox = 3;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel makeL = new JLabel("Make");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(makeL, gbcF);
		
		//make checkbox for eachMake
		String[] makes = controller.getMakes();
		for(String make : makes){
			yBox++;
			final JCheckBox makeName = new JCheckBox(make);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			makeName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			makeName.setName(make + "Check");
			filterBG.add(makeName, gbcF);
		}
		
		//model filter////////////
		gbcF.anchor = GridBagConstraints.NORTH;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel modelL = new JLabel("Model");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(modelL, gbcF);
		
		String[] models = controller.getModels();
		for(String model : models){
			yBox++;
			final JCheckBox modelName = new JCheckBox(model);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			modelName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			modelName.setName(model + "Check");
			filterBG.add(modelName, gbcF);
		}
		///////////////////
		
		//color filter//////
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel colorL = new JLabel("Color");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(colorL, gbcF);
		
		String[] colors = controller.getColors();
		for(String color : colors){
			yBox++;
			final JCheckBox colorName = new JCheckBox(color);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			colorName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			colorName.setName(color + "Check");
			filterBG.add(colorName, gbcF);
		}
		////////////////////
		
		//year filter//////
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel year = new JLabel("Year");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(year, gbcF);
		
		minyear = controller.getMinYear();
		maxyear = controller.getMaxYear();
		
		//create spinner to set the oldest year
		gbcF.anchor = GridBagConstraints.NORTH;
		SpinnerModel yearMinSpinnerModel = new SpinnerNumberModel(minyear,minyear,maxyear, 1);//min, max,step
		JSpinner minSpinner = new JSpinner(yearMinSpinnerModel);
		JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(minSpinner, "#");
		minSpinner.setEditor(minEditor);	
		minSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.insets = new Insets(0, 5, 0, 0);
		filterBG.add(minSpinner, gbcF);
		
		//create spinner to set the newest year
		gbcF.anchor = GridBagConstraints.NORTHEAST;
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(maxyear,minyear, maxyear,1);
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		maxSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = yBox;
		filterBG.add(maxSpinner, gbcF);
		
		//add dash between two spinners
		gbcF.anchor = GridBagConstraints.CENTER;
		gbcF.fill = GridBagConstraints.BOTH;
		JLabel dashyear = new JLabel(" - ");
		dashyear.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		//gbcF.insets = new Insets(0, -15, 0, 0);
		filterBG.add(dashyear, gbcF);
		////////////////////////////////
		
		//price filter///////////////////
		gbcF.fill = GridBagConstraints.NONE;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel price = new JLabel("Price");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.insets = new Insets(5, 0, 0, 0);
		filterBG.add(price, gbcF);
		//price slider
		
		minPrice = controller.getMinPrice();
		maxPrice = controller.getMaxPrice();
		
		gbcF.anchor = GridBagConstraints.NORTH;
		JSlider priceSlider = new JSlider((int)minPrice, (int)maxPrice, (int)maxPrice);
		priceSlider.setBackground(bgColor);
		gbcF.insets = new Insets(0, 10, 0, 0);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(priceSlider, gbcF);
		
		//label min price
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel min = new JLabel(Integer.toString((int)minPrice));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.ipady = 0;
		//gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(min, gbcF);
		
		//label max price
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel max = new JLabel(Integer.toString((int)maxPrice));
		gbcF.insets = new Insets(0, -15, 0, 0);
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = yBox++;
		gbcF.weighty =.2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);
		//////////////////////////////

		//add inventory and filters onto page	
		controlPanel.add(filterScroll);
		controlPanel.add(inventoryBG);
		inventoryListScrollPane.validate();
		mainFrame.validate();
		mainFrame.repaint();
		mainFrame.setVisible(true);
		}
		
	private void editVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");

			if (idString == null) { // checks if no input
				return;
			}

			int id = Integer.parseInt(idString);
			
			//check if vehicle entered exsist
			controller.getIndexFromId(id); 
			
			Vehicle vehicle = controller.getVehicleFromId(id);

			JTextField makeField = new JTextField();
			JTextField modelField = new JTextField();
			JTextField colorField = new JTextField();
			JTextField yearField = new JTextField();
			JTextField priceField = new JTextField();
			JTextField typeField = new JTextField();
			JTextField handlebarField = new JTextField();

			JPanel editPanel = new JPanel();
			editPanel.setLayout(new GridLayout(0, 2));

			if (vehicle instanceof Car) {
				//all functions in Car.java
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
				//all functions in Motorcycle.java
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

			int option = JOptionPane.showConfirmDialog(null, editPanel, "Edit Vehicle", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { // edit and set
				if (vehicle instanceof Car) {
					Car car = (Car) vehicle;
					car.setMake(makeField.getText());
					car.setModel(modelField.getText());
					car.setColor(colorField.getText());
					car.setYear(Integer.parseInt(yearField.getText()));
					car.setPrice(Double.parseDouble(priceField.getText()));
					car.setType(typeField.getText());
				} else if (vehicle instanceof Motorcycle) {
					Motorcycle motorcycle = (Motorcycle) vehicle;
					motorcycle.setMake(makeField.getText());
					motorcycle.setModel(modelField.getText());
					motorcycle.setColor(colorField.getText());
					motorcycle.setYear(Integer.parseInt(yearField.getText()));
					motorcycle.setPrice(Double.parseDouble(priceField.getText()));
					motorcycle.setHandlebarType(handlebarField.getText());
				}
				JOptionPane.showMessageDialog(null, "Vehicle edited successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterScroll);
				controlPanel.validate();
				controlPanel.repaint();
				mainFrame.validate();
				refreshInventory();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Year and price must be numeric values.");
		}
	}
	
	private void sellVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			int id = Integer.parseInt(idString);
			if (controller.vehicleExsist(id) == false) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return;
			}
			String buyerName = JOptionPane.showInputDialog(null, "Enter the buyer's name:");
			String buyerContact = JOptionPane.showInputDialog(null, "Enter the buyer's contact:");
			Vehicle vehicle = controller.getVehicleFromId(id);

			if (controller.sellVehicle(vehicle, buyerName, buyerContact)) {
				JOptionPane.showMessageDialog(null, "Vehicle sold successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterScroll);
				controlPanel.validate();
				controlPanel.repaint();
				mainFrame.validate();
				refreshInventory();
			} else {
				JOptionPane.showMessageDialog(null, "Couldn't sell vehicle.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}
	
	private void removeVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			int id = Integer.parseInt(idString);

			if (controller.vehicleExsist(id) == false) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
			} else {
				Vehicle vehicle = controller.getVehicleFromId(id);
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this vehicle\nwith id: " + id, "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					if (Main.m_dealership.removeVehicle(vehicle)) {
						JOptionPane.showMessageDialog(null, "Vehicle removed successfully.");
						controlPanel.remove(inventoryBG);
						controlPanel.remove(filterScroll);
						controlPanel.validate();
						controlPanel.repaint();
						mainFrame.validate();
						refreshInventory();
					} else {
						JOptionPane.showMessageDialog(null, "Couldn't remove vehicle.");
					}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}
	public void addCarMenu() {
		JTextField makeField = new JTextField();
		JTextField modelField = new JTextField();
		JTextField colorField = new JTextField();
		JTextField yearField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField carTypeField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.add(new JLabel("Make:"));
		panel.add(makeField);
		panel.add(new JLabel("Model:"));
		panel.add(modelField);
		panel.add(new JLabel("Color:"));
		panel.add(colorField);
		panel.add(new JLabel("Year:"));
		panel.add(yearField);
		panel.add(new JLabel("Price:"));
		panel.add(priceField);
		panel.add(new JLabel("Car Type:"));
		panel.add(carTypeField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Add a Car", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String make = makeField.getText();
			String model = modelField.getText();
			String color = colorField.getText();
			int year;
			double price;

			try {
				year = Integer.parseInt(yearField.getText());
				price = Double.parseDouble(priceField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input. Year and price must be numeric values.");
				return;
			}

			String type = carTypeField.getText();

			if (controller.addVehicle(new Car(make, model, color, year, price, type))){
				JOptionPane.showMessageDialog(null, "Car has been added successfully.");
				//Main.m_dealership.addVehicle(new Car("BMW", "accord", "red", 2012, 8000, "4 door"));
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterScroll);
				controlPanel.validate();
				controlPanel.repaint();
				mainFrame.validate();
				refreshInventory();
				//System.out .println("reload");
			}else{
			JOptionPane.showMessageDialog(null, "Sorry, the car has not been added.");}
		}
		controlPanel.repaint();
	}
	
	public void addMotorcycleMenu() {
		JTextField makeField = new JTextField();
		JTextField modelField = new JTextField();
		JTextField colorField = new JTextField();
		JTextField yearField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField handlebarTypeField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.add(new JLabel("Make:"));
		panel.add(makeField);
		panel.add(new JLabel("Model:"));
		panel.add(modelField);
		panel.add(new JLabel("Color:"));
		panel.add(colorField);
		panel.add(new JLabel("Year:"));
		panel.add(yearField);
		panel.add(new JLabel("Price:"));
		panel.add(priceField);
		panel.add(new JLabel("Handlebar Type:"));
		panel.add(handlebarTypeField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Add a Motorcycle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String make = makeField.getText();
			String model = modelField.getText();
			String color = colorField.getText();
			int year;
			double price;

			try {
				year = Integer.parseInt(yearField.getText());
				price = Double.parseDouble(priceField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input. Year and price must be numeric values.");
				return;
			}

			String handlebarType = handlebarTypeField.getText();

			if (Main.m_dealership.addVehicle(new Motorcycle(make, model, color, year, price, handlebarType))){
				JOptionPane.showMessageDialog(null, "Motorcycle has been added successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterScroll);
				controlPanel.validate();
				controlPanel.repaint();
				mainFrame.validate();
				refreshInventory();
			}
			else{
				JOptionPane.showMessageDialog(null, "Sorry, the motorcycle has not been added.");
			}
		}
	}
	
}

class myMouseListener implements MouseListener {
	private Component previous;
    @Override
    public void mouseClicked(MouseEvent arg0) { 
       //System.out .println("img presses");
	   if(arg0.getSource() instanceof JPanel ){
		   if(previous != null){
			   previous.setBackground(Color.WHITE);
		   }
		   arg0.getComponent().setBackground(Color.RED);
		   previous = arg0.getComponent();
		}
     }

     @Override
     public void mouseEntered(MouseEvent arg0) { }

     @Override
     public void mouseExited(MouseEvent arg0) { }

     @Override
     public void mousePressed(MouseEvent arg0) { }

     @Override
     public void mouseReleased(MouseEvent arg0) { }

}