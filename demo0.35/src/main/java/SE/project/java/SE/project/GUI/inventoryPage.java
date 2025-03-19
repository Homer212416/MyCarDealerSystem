package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import SE.project.carDealership.Vehicle;
import SE.project.carDealership.Car;
import SE.project.carDealership.Motorcycle;
import SE.project.controller.InventoryPageController;
import SE.project.Main;
import SE.project.persistance.VehicleDAO;
import java.sql.SQLException;

public class inventoryPage {
	private JFrame mainFrame;
	private InventoryPageController controller;
	private static final long serialVersionUID = -4235592661347719465L;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private int minyear = 1900;
	private int maxyear = 2025;
	private double minPrice = 0;
	private double maxPrice = 50000;
	private JPanel car1;
	private JPanel inventoryBG;
	private JPanel filterBG;
	private GridBagConstraints gbcF;
	private GridBagConstraints gbcI;
	private JScrollPane filterScroll;

	public inventoryPage(){
		this.controller = new InventoryPageController();
		prepareInventoryGUI();
		// Removed misplaced catch block
	}

	private void prepareInventoryGUI(){
		controller.addVehicle(new Car("Honda", "accord", "red", 2012, 4000, "4 door"));
		System.out.println("addVehicle on inventoryPage");
		controller.addVehicle(new Car("Volkswagon", "beetle", "yellow", 2020, 7000, "bug"));
		controller.addVehicle(new Car("Honda", "accord", "red", 2000, 2500, "4 door"));
		controller.addVehicle(new Car("Volkswagon", "beetle", "yellow", 2024, 5000, "bug"));
		//mainFrame is the whole window
		//controlPanel is the writable area within the mainframe
		/*
			ControlPanel
		 ____________________________
		|		Car Image			|
		|___________________________|
		|___searchBarBG_____________|
		|displayBG 	   		|		|
		|___________________|filterBG|
		|InventoryBG	   |		|
		|				   |		|
		|				   |		|
		|___________________________|
		|cap		totalV
		----------------------------|
		
		Car Image
		 ____________________________
		|				pageMenuDD	|		DD is dropdownMenu
		|___________________________|
		
		|___searchBarBG________________________________________|
		|editLabel editInventoryMenu		searchBar MagButton|
		_______________________________________________________
		
		Display BG______________________________________________|
		|														|
		|dAll dMoto dCar						sortMenu		|
		|_______________________________________________________|
		
		
		InventoryBG______________________________________________
		|	vehicleInv												| 
		|vehicleInv is a scrollpane that has a JPanel for every car |
		|								
		|
		--------------------------------------------------------
		
		
		Filter_____________________________
		\		make						|
		\	makeName						|
		\									|
		\		model						|
		\	modelName						|
		\									|
		\		color						|
		\	makeName						|
		\									|
		\		year						|
		\	minSpinner dashyear maxSpinner	|
		|									|
		|		price						|
		|	priceSlider						|
		|	min		max						|
		_____________________________________
		
		
		*/
		// frame and background setup
		// Create the main frame
		mainFrame = new JFrame("Inventory");
		mainFrame.setBounds(0, 0, 665, 665);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the control panel
		controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setLayout(null);

		// Create the car image
		JLabel carImage = new JLabel(new ImageIcon(inventoryPage.class.getResource("/images/backgroundd.jpg")));
		carImage.setBounds(0, -50, 650, 200);
		controlPanel.add(carImage);

		// Create the search bar background
		JPanel searchBarBG = new JPanel();
		searchBarBG.setBackground(Color.GRAY);
		searchBarBG.setLayout(new GridBagLayout());
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();
		searchBarBG.setBounds(0, 150, 650, 50);
		controlPanel.add(searchBarBG);

		

		// Create the display background
		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(10, 200, 440, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);

		
		// Create the page menu drop down for switching pages
		JComboBox<String> pageMenuDD = new JComboBox<>();
		pageMenuDD.addItem("Inventory");
		pageMenuDD.addItem("Dealership Info");
		pageMenuDD.addItem("Sales History");
		pageMenuDD.addItem("Manage User Accounts");
		pageMenuDD.addItem("Sign Out");

		// set the default pages for selection
		DefaultListSelectionModel pageMenuModel = new DefaultListSelectionModel();
		pageMenuModel.addSelectionInterval(0, 5);

		// set available pages
		ArrayList<Integer> available = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(4);
		available.add(5);

		// render the drop down
		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);
		pageMenuDD.setRenderer(pageMenuEnableRender);
		pageMenuDD.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDD);

		// Add action listener to page menu drop down
		pageMenuDD.addActionListener(new ActionListener() {
			// Switch to the selected page
			@Override
			public void actionPerformed(ActionEvent e) {
				if (available.contains(pageMenuDD.getSelectedIndex())) {
					if (pageMenuDD.getSelectedIndex() == 2) {
						new dealerShipInfoPage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 3) {
						new pastSalesPage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 4) {
						new accountManagePage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 5) {
						new LoginPage();
						mainFrame.dispose();
					}
				}
			}
		});

		// Set the default page: size and total value
		int size = controller.getTotalVehicleCountInInventory();
		JLabel cap = new JLabel("Inventory Size: " + size);
		cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		cap.setBounds(30, 600, 200, 25);
		controlPanel.add(cap);

		double totalValue = controller.getInventoryGrossValue();
		JLabel totalV = new JLabel("Total Inventory Value: " + totalValue);
		totalV.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		totalV.setBounds(240, 600, 200, 25);
		controlPanel.add(totalV);

		// Set the edit inventory drop down
		JComboBox<String> editInventoryMenu = new JComboBox<>();
		editInventoryMenu.addItem("");
		editInventoryMenu.addItem("Add Car");
		editInventoryMenu.addItem("Add Motorcycle");
		editInventoryMenu.addItem("Edit Vehicle");
		editInventoryMenu.addItem("Sell vehicle");
		editInventoryMenu.addItem("Remove Vehicle");

		// set the default selection
		DefaultListSelectionModel editInventoryModel = new DefaultListSelectionModel();
		editInventoryModel.addSelectionInterval(0, 5);

		// render the drop down
		EnabledJComboBoxRenderer editInventoryEnableRender = new EnabledJComboBoxRenderer(editInventoryModel);
		editInventoryMenu.setRenderer(editInventoryEnableRender);
		editInventoryMenu.setBounds(450, 20, 200, 25);
		controlPanel.add(editInventoryMenu);

		// Add action listener to edit inventory drop down
		editInventoryMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editInventoryMenu.getSelectedIndex() == 1) {
					//addCarMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 2) {
					//addMotorcycleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 3) {
					//editVehicleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 4) {
					//sellVehicleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 5) {
					//removeVehicleMenu();
				}
			}
		});

		editInventoryMenu.setSelectedIndex(0);
		gbcS.anchor = GridBagConstraints.WEST;
		JScrollPane addVehiclePane = new JScrollPane(editInventoryMenu);
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		gbcS.weightx = .2;
		searchBarBG.add(addVehiclePane, gbcS);

		// set Edit Inventory
		gbcS.anchor = GridBagConstraints.WEST;
		JLabel editLabel = new JLabel("Edit Inventory: ");
		editLabel.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		gbcS.weightx = 0;
		gbcS.insets = new Insets(0, 10, 0, 0);
		searchBarBG.add(editLabel, gbcS);

		// set search bar
		JTextField searchBar = new JTextField("Search ID", 15);
		searchBar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		gbcS.insets = new Insets(0, 0, 0, 0);
		gbcS.fill = GridBagConstraints.VERTICAL;
		searchBarBG.add(searchBar, gbcS);

		// set search button
		JButton MagButton = new JButton("S");
		gbcS.insets = new Insets(0, 0, 0, 10);
		gbcS.gridx = 6;
		gbcS.gridy = 0;

		searchBarBG.add(MagButton, gbcS);

		gbcD.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox dAll = new JCheckBox("Display All");
		dAll.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		dAll.setBackground(new Color(230, 230, 230));
		final JCheckBox dMoto = new JCheckBox("Motocycles Only");
		dMoto.setBackground(new Color(230, 230, 230));
		dMoto.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		final JCheckBox dCar = new JCheckBox("Cars Only");
		dCar.setBackground(new Color(230, 230, 230));
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
		final DefaultComboBoxModel<String> sortMenu = new DefaultComboBoxModel<>();
		sortMenu.addElement("Sort By");
		sortMenu.addElement("Price Descending");
		sortMenu.addElement("Price Ascending");
		sortMenu.addElement("Make");
		sortMenu.addElement("Model");
		sortMenu.addElement("Year Descending");
		sortMenu.addElement("Year Ascending");

		final JComboBox<String> sortMenuBox = new JComboBox<>(sortMenu);
		sortMenuBox.setSelectedIndex(0);
		sortMenuBox.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		JScrollPane sortMenuPane = new JScrollPane(sortMenuBox);
		gbcD.gridwidth = 1;
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.ipadx = 5;
		gbcD.weightx = .1;
		displayBG.add(sortMenuPane, gbcD);

		refreshInventory();
		
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
		

	}
	private void refreshInventory(){
		//inventory image list
		int totalVehicles = controller.getTotalVehicleCountInInventory();
		inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 225, 440, 375);

		filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setPreferredSize(new Dimension(200, 365));
		gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);
		filterScroll = new JScrollPane(filterBG, JScrollPane. VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		filterScroll.setBounds(450, 227, 200, 370);
		controlPanel.add(filterScroll);
		
		mainFrame.validate();
		
		JPanel vehicleInv = new JPanel();
		vehicleInv.setBackground(Color.WHITE);
		vehicleInv.setBounds(10, 225, 440, 375);
		vehicleInv.setLayout(new GridBagLayout());
		GridBagLayout layoutvehicleInv = new GridBagLayout();
		vehicleInv.setLayout(layoutvehicleInv);
		GridBagConstraints gbcvehicleInv = new GridBagConstraints();
		gbcvehicleInv.insets = new Insets(5,5,5,5);
		gbcvehicleInv.anchor = GridBagConstraints.NORTHWEST;
		
		System.out.println("total vehicles: " + controller.getTotalVehicleCountInInventory());
		
		myMouseListener mml = new myMouseListener();
		Image carIconImage = (Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		Image newCarImage = carIconImage.getScaledInstance(80, 70,Image.SCALE_DEFAULT);
		String[] vehicles = controller.displayAllVehicles();
		JPanel[] vehiclePanelNames= new JPanel[totalVehicles];
		//create a panel with a distinct name for each vehicle in inventory
		for (int i = 0; i < totalVehicles ; i++) {
			String num = Integer.toString(i);
			JPanel vehicle1 = new JPanel();
			vehiclePanelNames[i] = vehicle1;
			vehicle1.setName("vehicle" + i);
			System.out.println("i = " + i);
		}
		//add image and information to each panel
		for (int i = 0; i < totalVehicles ; i++) {
			vehiclePanelNames[i].setBackground(Color.WHITE);
			vehiclePanelNames[i].setLayout(new GridBagLayout());
			GridBagLayout layoutC1 = new GridBagLayout();
			vehiclePanelNames[i].setLayout(layoutC1);
			GridBagConstraints gbcC1 = new GridBagConstraints();
			JLabel c1Img = new JLabel("");
			c1Img.setIcon(new ImageIcon(newCarImage));
			gbcC1.gridx = 0;
			gbcC1.gridy = 0;
			gbcC1.anchor = GridBagConstraints.NORTHWEST;
			vehiclePanelNames[i].add(c1Img, gbcC1);
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
			gbcvehicleInv.gridx = (int)Math.floor(i%2);
			gbcvehicleInv.gridy = (int)Math.floor(i/2);
			//assign weight to last panel so that they format properly within the scrollpane
			if(i == totalVehicles){
				gbcvehicleInv.weightx = .9;
				gbcvehicleInv.weighty = 1;
			}
			vehicleInv.add(vehiclePanelNames[i], gbcvehicleInv);
			vehiclePanelNames[i].setFocusable(true);
			vehiclePanelNames[i].addMouseListener(mml); 
		} 
		
		//scrollpane that hold all the vehicle panels
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
		
		
		
		//filter portion right hand side
		//make filter
		int yBox = 3;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel makeL = new JLabel("Make");
		makeL.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(makeL, gbcF);

		//create a checkbox for each distinct make
		String[] makes = controller.getAllMakes();
		//System.out.println(makes[0]);
		for(String make : makes){
			yBox++;
			final JCheckBox makeName = new JCheckBox(make);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			makeName.setBackground(new Color(230, 230, 230));
			makeName.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			makeName.setName(make + "Check");
			filterBG.add(makeName, gbcF);
		}
		
		//model filter
		gbcF.anchor = GridBagConstraints.NORTH;
		
		JLabel modelL = new JLabel("Model");
		modelL.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(modelL, gbcF);
		
		String[] models = controller.getAllModels();
		for(String model: models){
			yBox++;
			if(model != null){
				final JCheckBox modelName = new JCheckBox(model);
				gbcF.anchor = GridBagConstraints.NORTHWEST;
				modelName.setBackground(new Color(230, 230, 230));
				modelName.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
				gbcF.gridx = 0;
				gbcF.gridy = yBox++;
				modelName.setName(model + "Check");
				filterBG.add(modelName, gbcF);
			}
		}
	
		
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel colorL = new JLabel("Color");
		colorL.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(colorL, gbcF);
		
		String[] colors = controller.getAllColors();

		for(String color: colors){
			yBox++;
			final JCheckBox colorName = new JCheckBox(color);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			colorName.setBackground(new Color(230, 230, 230));
			colorName.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			colorName.setName(color + "Check");
			filterBG.add(colorName, gbcF);
		}
		
		//year filter
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel year = new JLabel("Year");
		year.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		
		filterBG.add(year, gbcF);
		
		minyear = controller.getMinYear();
		maxyear = controller.getMaxYear();
		
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

		gbcF.anchor = GridBagConstraints.NORTHEAST;
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(maxyear,minyear, maxyear,1);
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		maxSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = yBox;
		//gbcF.insets = new Insets(0, 0, 0, 20);
		filterBG.add(maxSpinner, gbcF);

		gbcF.anchor = GridBagConstraints.CENTER;
		gbcF.fill = GridBagConstraints.BOTH;
		JLabel dashyear = new JLabel(" - ");
		dashyear.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		//gbcF.insets = new Insets(0, -15, 0, 0);
		filterBG.add(dashyear, gbcF);
		
		gbcF.fill = GridBagConstraints.NONE;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel price = new JLabel("Price");
		price.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
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
		priceSlider.setBackground(new Color(230, 230, 230));
		gbcF.insets = new Insets(0, 10, 0, 0);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(priceSlider, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel min = new JLabel(Integer.toString((int)minPrice));
		min.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.ipady = 0;
		//gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(min, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel max = new JLabel(Integer.toString((int)maxPrice));
		max.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.insets = new Insets(0, -15, 0, 0);
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = yBox++;
		gbcF.weighty =.2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);
		
		//filterScroll.validate();
		controlPanel.add(filterScroll);
		controlPanel.add(inventoryBG);
		inventoryListScrollPane.validate();
		mainFrame.validate();
		mainFrame.repaint();
	
	}
	

	private void editVehicleMenu() throws SQLException {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");

			if (idString == null) { // checks if no input
				return;
			}

			int id = Integer.parseInt(idString);

			Vehicle vehicle = controller.searchVehicleByID(id);

			if (vehicle == null) { // check if exists
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return;
			}

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
				typeField.setText(car.getCarType());
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
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}

	// int option = JOptionPane.showConfirmDialog(null, editPanel, "Edit Vehicle",
	// JOptionPane.OK_CANCEL_OPTION);
	// if (option == JOptionPane.OK_OPTION) { // edit and set
	// if (vehicle instanceof Car) {
	// Car car = (Car) vehicle;
	// car.setMake(makeField.getText());
	// car.setModel(modelField.getText());
	// car.setColor(colorField.getText());
	// car.setYear(Integer.parseInt(yearField.getText()));
	// car.setPrice(Double.parseDouble(priceField.getText()));
	// car.setType(typeField.getText());
	// } else if (vehicle instanceof Motorcycle) {
	// Motorcycle motorcycle = (Motorcycle) vehicle;
	// motorcycle.setMake(makeField.getText());
	// motorcycle.setModel(modelField.getText());
	// motorcycle.setColor(colorField.getText());
	// motorcycle.setYear(Integer.parseInt(yearField.getText()));
	// motorcycle.setPrice(Double.parseDouble(priceField.getText()));
	// motorcycle.setHandlebarType(handlebarField.getText());
	// }
	// JOptionPane.showMessageDialog(null, "Vehicle edited successfully.");
	// }
	// } catch (NumberFormatException e) {
	// JOptionPane.showMessageDialog(null, "Invalid input. Year and price must be
	// numeric values.");
	// }

	private void sellVehicleMenu() throws SQLException {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			int id = Integer.parseInt(idString);

			Vehicle vehicle = controller.searchVehicleByID(id);

			if (vehicle == null) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return;
			}

			String buyerName = JOptionPane.showInputDialog(null, "Enter the buyer's name:");
			String buyerContact = JOptionPane.showInputDialog(null, "Enter the buyer's contact:");

			controller.sellVehicle(id, buyerName, buyerContact);
			JOptionPane.showMessageDialog(null, "Vehicle sold successfully.");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}

	private void removeVehicleMenu() throws SQLException {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			int id = Integer.parseInt(idString);

			Vehicle vehicle = controller.searchVehicleByID(id);

			if (vehicle == null) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
			} else {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this vehicle\nwith id: " + id, "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					controller.removeVehicle(vehicle);
					JOptionPane.showMessageDialog(null, "Vehicle removed successfully.");
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}

	public void addCarMenu() throws SQLException {
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

			if (controller.addVehicle(new Car(make, model, color, year, price, type))) {
				JOptionPane.showMessageDialog(null, "Car has been added successfully.");

			} else {
				JOptionPane.showMessageDialog(null, "Sorry, the car has not been added.");
			}
		}
		mainFrame.invalidate();
		mainFrame.validate();
		mainFrame.repaint();
	}

	public void addMotorcycleMenu() throws SQLException{
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

			if (controller.addVehicle(new Motorcycle(make, model, color, year, price, handlebarType)))
				JOptionPane.showMessageDialog(null, "Motorcycle has been added successfully.");
			else
				JOptionPane.showMessageDialog(null, "Sorry, the motorcycle has not been added.");

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