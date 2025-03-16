package SE.project.GUI;

import SE.project.carDealership.Car;
import SE.project.carDealership.Motorcycle;
import SE.project.carDealership.Vehicle;
import SE.project.carDealership.Inventory;

import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class inventoryPage {
	private static final long serialVersionUID = -4235592661347719465L;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private int minyear = 1900;
	private int maxyear = 2025;
	private double minPrice = 0;
	private double maxPrice = 50000;
	private Inventory inventory;

	public inventoryPage() {
		this.inventory = new Inventory();
		prepareInventoryGUI();
	}

	public static void main(String[] args) {
		new inventoryPage();
	}

	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI() {
		mainFrame = new JFrame("Inventory");
		mainFrame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 665, 665);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
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
		searchBarBG.setLayout(new GridBagLayout());
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();
		searchBarBG.setBounds(0, 150, 650, 50);
		controlPanel.add(searchBarBG);

		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setBounds(450, 225, 200, 350);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);

		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(10, 200, 440, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);

		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 225, 440, 375);
		controlPanel.add(inventoryBG);

		// page menu in upper right hand corner
		/*
		 * final DefaultComboBoxModel pageMenuDD = new DefaultComboBoxModel();
		 * pageMenuDD.addElement("");
		 * pageMenuDD.addElement("Inventory");
		 * pageMenuDD.addElement("Dealership Info");
		 * pageMenuDD.addElement("Sales History");
		 * pageMenuDD.addElement("Manage User Accounts");
		 * pageMenuDD.addElement("Sign Out");
		 * 
		 * 
		 * 
		 * final JComboBox pageMenuDDB = new JComboBox(pageMenuDD);
		 * pageMenuDDB.setSelectedIndex(0);
		 * JScrollPane pageMenuDDP = new JScrollPane(pageMenuDDB);
		 * pageMenuDDP.setBounds(450, 20, 200, 25);
		 * controlPanel.add(pageMenuDDP);
		 * //actions for page drop down
		 */
		JComboBox pageMenuDD = new JComboBox();

		pageMenuDD.addItem("");
		pageMenuDD.addItem("Inventory");
		pageMenuDD.addItem("Dealership Info");
		pageMenuDD.addItem("Sales History");
		pageMenuDD.addItem("Manage User Accounts");
		pageMenuDD.addItem("Sign Out");

		DefaultListSelectionModel pageMenuModel = new DefaultListSelectionModel();

		pageMenuModel.addSelectionInterval(0, 5);

		// pageMenuModel.addSelectionInterval(4, 5);

		List<Integer> available = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(4);
		available.add(5);

		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);

		pageMenuDD.setRenderer(pageMenuEnableRender);
		pageMenuDD.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDD);
		// actions for page drop down */
		System.out.println(pageMenuModel.toString());
		pageMenuDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (available.contains(pageMenuDD.getSelectedIndex())) {
					if (pageMenuDD.getSelectedIndex() == 2) {
						dealerShipInfoPage dealer = new dealerShipInfoPage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 3) {
						pastSalesPage sales = new pastSalesPage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 4) {
						accountManagePage accounts = new accountManagePage();
						mainFrame.dispose();
					} else if (pageMenuDD.getSelectedIndex() == 5) {
						LoginPage login = new LoginPage();
						mainFrame.dispose();
					}
				}
			}
		});

		int size = inventory.getTotalVehicles();
		JLabel cap = new JLabel("Size: " + size);
		cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		cap.setBounds(30, 600, 200, 25);
		controlPanel.add(cap);

		double totalValue = inventory.getInventoryGrossValue();
		JLabel totalV = new JLabel("Total Inventory Value: " + totalValue);
		totalV.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		totalV.setBounds(240, 600, 200, 25);
		controlPanel.add(totalV);

		// edit inventory button on search bar left hand side
		JComboBox editInventoryMenu = new JComboBox();

		editInventoryMenu.addItem("");
		editInventoryMenu.addItem("Add Car");
		editInventoryMenu.addItem("Add Motorcycle");
		editInventoryMenu.addItem("Edit Vehicle");
		editInventoryMenu.addItem("Sell vehicle");
		editInventoryMenu.addItem("Remove Vehicle");

		DefaultListSelectionModel editInventoryModel = new DefaultListSelectionModel();

		editInventoryModel.addSelectionInterval(0, 5);

		// editInventoryModel.addSelectionInterval(5, 5);

		EnabledJComboBoxRenderer editInventoryEnableRender = new EnabledJComboBoxRenderer(editInventoryModel);

		editInventoryMenu.setRenderer(editInventoryEnableRender);
		editInventoryMenu.setBounds(450, 20, 200, 25);
		controlPanel.add(editInventoryMenu);
		// actions for page drop down */

		editInventoryMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editInventoryMenu.getSelectedIndex() == 1) {
					addCarMenu();

				} else if (editInventoryMenu.getSelectedIndex() == 2) {
					addMotorcycleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 3) {
					editVehicleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 4) {
					sellVehicleMenu();
				} else if (editInventoryMenu.getSelectedIndex() == 5) {
					removeVehicleMenu();
				}
			}
		});

		editInventoryMenu.setSelectedIndex(0);
		// addVehicleBox.setForeground(Color.GRAY);
		gbcS.anchor = GridBagConstraints.WEST;
		JScrollPane addVehiclePane = new JScrollPane(editInventoryMenu);
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		gbcS.weightx = .2;
		searchBarBG.add(addVehiclePane, gbcS);

		gbcS.anchor = GridBagConstraints.WEST;
		JLabel editLabel = new JLabel("Edit Inventory: ");
		editLabel.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		gbcS.weightx = 0;
		gbcS.insets = new Insets(0, 10, 0, 0);
		searchBarBG.add(editLabel, gbcS);

		// search bar on right hand side
		JTextField searchBar = new JTextField("Search ID", 15);
		searchBar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		// gbcS.anchor = GridBagConstraints.NORTHEAST;
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		gbcS.insets = new Insets(0, 0, 0, 0);
		gbcS.fill = GridBagConstraints.VERTICAL;
		searchBarBG.add(searchBar, gbcS);

		// searchButton
		JButton MagButton = new JButton("S");
		gbcS.insets = new Insets(0, 0, 0, 10);
		gbcS.gridx = 6;
		gbcS.gridy = 0;

		searchBarBG.add(MagButton, gbcS);

		// filter portion right hand side
		// make filter
		int yBox = 0;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel make = new JLabel("Make");
		make.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(make, gbcF);

		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			yBox++;
			final JCheckBox makeName = new JCheckBox(inventory.getVehicle(i).getMake());
			makeName.setBackground(new Color(230, 230, 230));
			makeName.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			filterBG.add(makeName, gbcF);
		}

		// model filter
		gbcF.anchor = GridBagConstraints.NORTH;

		JLabel model = new JLabel("Model");
		model.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(model, gbcF);

		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			yBox++;
			final JCheckBox modelName = new JCheckBox(inventory.getVehicle(i).getModel());
			modelName.setBackground(new Color(230, 230, 230));
			modelName.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			filterBG.add(modelName, gbcF);
		}

		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel color = new JLabel("Color");
		color.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(color, gbcF);

		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			yBox++;
			final JCheckBox vehColor = new JCheckBox(inventory.getVehicle(i).getColor());
			vehColor.setBackground(new Color(230, 230, 230));
			vehColor.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			filterBG.add(vehColor, gbcF);
		}

		// year filter
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel year = new JLabel("Year");
		year.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;

		filterBG.add(year, gbcF);

		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			if (inventory.getTotalVehicles() > 1) {
				if (i + 1 < inventory.getTotalVehicles()) {

					if (inventory.getVehicle(i).getYear() > inventory.getVehicle(i + 1).getYear()) {
						minyear = inventory.getVehicle(i + 1).getYear();
						maxyear = inventory.getVehicle(i).getYear();

					} else {
						minyear = inventory.getVehicle(i).getYear();
						maxyear = inventory.getVehicle(i + 1).getYear();
					}
				}
			} else {
				minyear = inventory.getVehicle(i).getYear();
				maxyear = inventory.getVehicle(i).getYear();
			}

		}
		gbcF.anchor = GridBagConstraints.NORTH;
		SpinnerModel yearMinSpinnerModel = new SpinnerNumberModel(minyear, minyear, maxyear, 1);// min, max,step
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
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(maxyear, minyear, maxyear, 1);
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		maxSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = yBox;
		// gbcF.insets = new Insets(0, 0, 0, 20);
		filterBG.add(maxSpinner, gbcF);

		gbcF.anchor = GridBagConstraints.NORTH;
		gbcF.fill = GridBagConstraints.BOTH;
		JLabel dashyear = new JLabel(" - ");
		dashyear.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		gbcF.insets = new Insets(0, -15, 0, 0);
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
		// price slider
		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			if (inventory.getTotalVehicles() > 1) {
				if (i + 1 < inventory.getTotalVehicles()) {

					if (inventory.getVehicle(i).getPrice() > inventory.getVehicle(i + 1).getPrice()) {
						minPrice = inventory.getVehicle(i + 1).getPrice();
						maxPrice = inventory.getVehicle(i).getPrice();

					} else {
						minPrice = inventory.getVehicle(i).getPrice();
						maxPrice = inventory.getVehicle(i + 1).getPrice();
					}
				}
			} else {
				minPrice = inventory.getVehicle(i).getPrice();
				maxPrice = inventory.getVehicle(i).getPrice();
			}

		}
		gbcF.anchor = GridBagConstraints.NORTH;
		JSlider priceSlider = new JSlider((int) minPrice, (int) maxPrice, (int) maxPrice);
		priceSlider.setBackground(new Color(230, 230, 230));
		gbcF.insets = new Insets(0, 10, 0, 0);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(priceSlider, gbcF);

		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel min = new JLabel(Integer.toString((int) minPrice));
		min.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.ipady = 0;
		gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(min, gbcF);

		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel max = new JLabel(Integer.toString((int) maxPrice));
		max.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.insets = new Insets(0, -15, 0, 0);
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = yBox++;
		gbcF.weighty = .2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);

		// display options
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
		// gbcD.insets = new Insets(0,-50,0,0);
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
		final DefaultComboBoxModel sortMenu = new DefaultComboBoxModel();
		sortMenu.addElement("Sort By");
		sortMenu.addElement("Price Descending");
		sortMenu.addElement("Price Ascending");
		sortMenu.addElement("Make");
		sortMenu.addElement("Model");
		sortMenu.addElement("Year Descending");
		sortMenu.addElement("Year Ascending");

		final JComboBox sortMenuBox = new JComboBox(sortMenu);
		sortMenuBox.setSelectedIndex(0);
		sortMenuBox.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		JScrollPane sortMenuPane = new JScrollPane(sortMenuBox);
		gbcD.gridwidth = 1;
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.ipadx = 5;
		gbcD.weightx = .1;
		displayBG.add(sortMenuPane, gbcD);

		// inventory list
		/*
		 * final DefaultListModel inventoryModel = new DefaultListModel();
		 * 
		 * inventoryModel.addElement("Blue Honda Accord 2013");
		 * inventoryModel.addElement("Red BMW Series One 2020");
		 * inventoryModel.addElement("Yellow Volkswagon Beetle 1960");
		 * 
		 * 
		 * 
		 * 
		 */

		// inventory image list
		JPanel carInv = new JPanel();
		carInv.setBackground(Color.WHITE);
		carInv.setBounds(10, 225, 440, 375);
		carInv.setLayout(new GridBagLayout());
		GridBagLayout layoutCarInv = new GridBagLayout();
		carInv.setLayout(layoutCarInv);
		GridBagConstraints gbcCarInv = new GridBagConstraints();
		gbcCarInv.insets = new Insets(5, 5, 5, 5);
		gbcCarInv.anchor = GridBagConstraints.NORTHWEST;

		Image carIconImage = (Toolkit.getDefaultToolkit()
				.getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		Image newCarImage = carIconImage.getScaledInstance(80, 70, Image.SCALE_DEFAULT);
		String[] vehicles = inventory.displayAlls();
		for (int i = 0; i < inventory.getTotalVehicles(); i++) {
			JPanel car1 = new JPanel();
			car1.setBackground(Color.WHITE);
			car1.setLayout(new GridBagLayout());
			GridBagLayout layoutC1 = new GridBagLayout();
			car1.setLayout(layoutC1);
			GridBagConstraints gbcC1 = new GridBagConstraints();
			JLabel c1Img = new JLabel("");
			c1Img.setIcon(new ImageIcon(newCarImage));
			gbcC1.gridx = 0;
			gbcC1.gridy = 0;
			gbcC1.anchor = GridBagConstraints.NORTHWEST;
			car1.add(c1Img, gbcC1);
			JTextArea c1Info = new JTextArea(6, 20);
			c1Info.setForeground(Color.BLACK);
			c1Info.setText(inventory.displayAlls()[i]);
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
			car1.add(c1Info, gbcC1);
			gbcCarInv.insets = new Insets(5, 5, 5, 5);
			gbcCarInv.anchor = GridBagConstraints.NORTHWEST;
			gbcCarInv.gridx = (int) Math.floor(i % 3);
			gbcCarInv.gridy = (int) Math.floor(i / 3);
			gbcCarInv.weightx = .9;
			gbcCarInv.weighty = 1;
			carInv.add(car1, gbcCarInv);
		}

		/*
		 * final JList inventoryList = new JList(inventoryModel);
		 * inventoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
		 * );
		 * inventoryList.setSelectedIndex(0);
		 * inventoryList.setVisibleRowCount(19);
		 */
		JScrollPane inventoryListScrollPane = new JScrollPane(carInv);
		inventoryListScrollPane.setPreferredSize(new Dimension(440, 375));
		// inventoryList.setFont(new Font("HP Simplified Hans", Font.PLAIN, 14));
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcF.ipady = 0;
		// gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5, 0, 0, 0);
		inventoryBG.add(inventoryListScrollPane, gbcI);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);

	}

	private void editVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");

			if (idString == null) { // checks if no input
				return;
			}

			int id = Integer.parseInt(idString);

			System.out.println(inventory.getIndexFromId(id));

			if (inventory.getIndexFromId(id) == -1) { // check if exist
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return;
			}
			Vehicle vehicle = inventory.getVehicleFromId(id);

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
			if (inventory.getIndexFromId(id) == -1) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
				return;
			}
			String buyerName = JOptionPane.showInputDialog(null, "Enter the buyer's name:");
			String buyerContact = JOptionPane.showInputDialog(null, "Enter the buyer's contact:");
			Vehicle vehicle = inventory.getVehicleFromId(id);

			if (inventory.sellVehicle(vehicle, buyerName, buyerContact)) {
				JOptionPane.showMessageDialog(null, "Vehicle sold successfully.");
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

			if (inventory.getIndexFromId(id) == -1) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
			} else {
				Vehicle vehicle = inventory.getVehicleFromId(id);
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this vehicle\nwith id: " + id, "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					if (inventory.removeVehicle(vehicle)) {
						JOptionPane.showMessageDialog(null, "Vehicle removed successfully.");
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

			if (inventory.addVehicle(new Car(make, model, color, year, price, type))) {
				JOptionPane.showMessageDialog(null, "Car has been added successfully.");

			} else {
				JOptionPane.showMessageDialog(null, "Sorry, the car has not been added.");
			}
		}
		mainFrame.invalidate();
		mainFrame.validate();
		mainFrame.repaint();
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

			if (inventory.addVehicle(new Motorcycle(make, model, color, year, price, handlebarType)))
				JOptionPane.showMessageDialog(null, "Motorcycle has been added successfully.");
			else
				JOptionPane.showMessageDialog(null, "Sorry, the motorcycle has not been added.");

		}
	}
}