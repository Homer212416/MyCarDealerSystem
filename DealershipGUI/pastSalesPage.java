package DealershipGUI;

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

public class pastSalesPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public pastSalesPage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      pastSalesPage pastSalesPage = new pastSalesPage();
      //pastSalesPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Past Sales");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 650, 650);
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 650, 650);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JLabel carImage = new JLabel("");
		carImage.setBackground(Color.BLACK);
		carImage.setOpaque(true);
		carImage.setIcon(new ImageIcon(pastSalesPage.class.getResource("/images/backgroundd.jpg")));
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
		filterBG.setBounds(450, 225, 200, 450);
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
		inventoryBG.setBounds(0, 225, 450, 430);
		controlPanel.add(inventoryBG);
		
		//page menu in upper right hand corner
		final DefaultComboBoxModel pageMenuDD = new DefaultComboBoxModel();
		pageMenuDD.addElement("");
		pageMenuDD.addElement("Inventory");
		pageMenuDD.addElement("Dealership Info");
		pageMenuDD.addElement("Sales History");
		pageMenuDD.addElement("Sign Out");
		
		final JComboBox pageMenuDDB = new JComboBox(pageMenuDD);    
		pageMenuDDB.setSelectedIndex(0);
		JScrollPane pageMenuDDP = new JScrollPane(pageMenuDDB);  
		pageMenuDDP.setBounds(500, 20, 150, 25);
		controlPanel.add(pageMenuDDP);
		
		//edit inventory button on search bar left hand side
		final DefaultComboBoxModel addVehicle = new DefaultComboBoxModel();
		
		addVehicle.addElement("");
		addVehicle.addElement("Add Car");
		addVehicle.addElement("Add Motorcycle");
		addVehicle.addElement("Edit Vehicle");
		addVehicle.addElement("Sell Vehicle");
		addVehicle.addElement("Remove Vehicle");

		final JComboBox addVehicleBox = new JComboBox(addVehicle);    
		addVehicleBox.setSelectedIndex(0);
		JScrollPane addVehiclePane = new JScrollPane(addVehicleBox);    
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		searchBarBG.add(addVehiclePane, gbcS); 
		
		JLabel editLabel = new JLabel("Edit Inventory: ");
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		searchBarBG.add(editLabel, gbcS); 
		
		//search bar on right hand side
		JTextField searchBar  = new JTextField("Search", 15);
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		gbcS.insets = new Insets(0,200,0,0);
		searchBarBG.add(searchBar, gbcS);
		
		//searchButton
		JButton MagButton = new JButton("S");
		gbcS.insets = new Insets(0,0,0,0);
		gbcS.gridx = 6;
		gbcS.gridy = 0;
		searchBarBG.add(MagButton, gbcS);
		
		//filter portion right hand side
		//make filter
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel make = new JLabel("Make");
		gbcF.gridwidth = 3;
		gbcF.weighty =.2;
		gbcF.gridx = 0;
		gbcF.gridy = 3;
		gbcF.ipady = 5;
		filterBG.add(make, gbcF);
		gbcF.anchor = GridBagConstraints.WEST;
		final JCheckBox Honda = new JCheckBox("Honda");
		Honda.setBackground(new Color(230, 230, 230));
		final JCheckBox BMW = new JCheckBox("BMW");
		BMW.setBackground(new Color(230, 230, 230));
		final JCheckBox Volkswagon = new JCheckBox("Volkswagon");
		Volkswagon.setBackground(new Color(230, 230, 230));
		gbcF.gridx = 0;
		gbcF.gridy = 4;
		filterBG.add(Honda, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 5;
		filterBG.add(BMW, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 6;
		gbcF.ipady = 15;
		filterBG.add(Volkswagon, gbcF);
		
		//model filter
		gbcF.anchor = GridBagConstraints.CENTER;
		
		JLabel model = new JLabel("Model");
		gbcF.gridwidth = 3;
		gbcF.weighty =.2;
		gbcF.gridx = 0;
		gbcF.gridy = 7;
		gbcF.ipady = 0;
		filterBG.add(model, gbcF);
		gbcF.anchor = GridBagConstraints.WEST;
		final JCheckBox accord = new JCheckBox("Accord");
		accord.setBackground(new Color(230, 230, 230));
		final JCheckBox oneSeries = new JCheckBox("1 Series");
		oneSeries.setBackground(new Color(230, 230, 230));
		final JCheckBox bug = new JCheckBox("Beetle");
		bug.setBackground(new Color(230, 230, 230));
		gbcF.gridx = 0;
		gbcF.gridy = 8;
		filterBG.add(accord, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 9;
		filterBG.add(oneSeries, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 10;
		gbcF.ipady = 15;
		filterBG.add(bug, gbcF);
		
		//year filter
		gbcF.anchor = GridBagConstraints.CENTER;
		JLabel year = new JLabel("Year");
		gbcF.insets = new Insets(-50,0,0,0);
		gbcF.gridwidth = 3;
		gbcF.weighty =.8;
		gbcF.gridx = 0;
		gbcF.gridy = 11;
		gbcF.ipady = 0;
		filterBG.add(year, gbcF);
		
		gbcF.anchor = GridBagConstraints.WEST;
		SpinnerModel yearMinSpinnerModel = new SpinnerNumberModel(1900,1900,2025, 1);//min, max,step
		JSpinner minSpinner = new JSpinner(yearMinSpinnerModel);
		JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(minSpinner, "#");
		minSpinner.setEditor(minEditor);			
		gbcF.gridwidth = 1;
		
		gbcF.gridx = 0;
		gbcF.gridy = 12;
		//gbcF.insets = new Insets(-70,0,0,0);
		gbcF.ipady = 5;
		filterBG.add(minSpinner, gbcF);
		
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(2025,1900, 2025,1);
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = 12;
		gbcF.ipady = 5;

		filterBG.add(maxSpinner, gbcF);
		
		gbcF.anchor = GridBagConstraints.CENTER;
		JLabel dashyear = new JLabel("       - ");
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = 12;
		gbcF.ipadx = 5;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(dashyear, gbcF);
		
		gbcF.fill = GridBagConstraints.NONE;
		gbcF.anchor = GridBagConstraints.CENTER;
		JLabel price = new JLabel("Price");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 13;
		filterBG.add(price, gbcF);
		
		//price slider
		JLabel min = new JLabel("0");
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 15;
		gbcF.weightx = .05;
		gbcF.insets = new Insets(-70,0,0,0);
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(min, gbcF);
		
		gbcF.anchor = GridBagConstraints.WEST;
		JLabel max = new JLabel("5000");
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = 15;
		gbcF.weightx = .5;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);
		
		gbcF.anchor = GridBagConstraints.CENTER;
		JSlider priceSlider = new JSlider(0, 5000, 5000);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 14;
		gbcF.weightx = .25;
		gbcF.insets = new Insets(-50,0,0,0);
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(priceSlider, gbcF);
		
		//display options
		gbcD.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox dAll = new JCheckBox("Display All");
		dAll.setBackground(new Color(230, 230, 230));
		final JCheckBox dMoto = new JCheckBox("Motocycles Only");
		dMoto.setBackground(new Color(230, 230, 230));
		final JCheckBox dCar = new JCheckBox("Cars Only");
		dCar.setBackground(new Color(230, 230, 230));
		//gbcD.insets = new Insets(0,-50,0,0);
		gbcD.weightx = .5;
		gbcD.gridwidth = 1;
		gbcD.gridx = 0;
		gbcD.gridy = 0;
		displayBG.add(dAll, gbcD);
		gbcD.insets = new Insets(0,-70,0,0);
		gbcD.weightx = .9;
		gbcD.gridx = 1;
		gbcD.gridy = 0;
		displayBG.add(dMoto, gbcD);
		gbcD.insets = new Insets(0,-115,0,0);
		gbcD.weightx= .9;
		gbcD.gridx = 2;
		gbcD.gridy = 0;
		gbcD.ipady = 0;
		gbcD.ipadx = 10;
		displayBG.add(dCar, gbcD);
		
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
		JScrollPane sortMenuPane = new JScrollPane(sortMenuBox); 
		gbcD.fill = GridBagConstraints.HORIZONTAL;		
		gbcD.gridwidth = 1;
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.ipadx = 5;
		gbcD.insets = new Insets(0,-140,0,0);
		gbcD.weightx = .1;
		displayBG.add(sortMenuPane, gbcD);
	
	
		//inventory list
		final DefaultListModel inventoryModel = new DefaultListModel();

		inventoryModel.addElement("Blue Honda Accord 2013");
		inventoryModel.addElement("Red BMW Series One 2020");
		inventoryModel.addElement("Yellow Volkswagon Beetle 1960");



		final JList inventoryList = new JList(inventoryModel);
		inventoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		inventoryList.setSelectedIndex(0);
		inventoryList.setVisibleRowCount(19);
		JScrollPane inventoryListScrollPane = new JScrollPane(inventoryList);       
		JButton showButton2 = new JButton("Show");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcF.ipady = 0;
		//gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5,0,0,0);
		inventoryBG.add(inventoryListScrollPane, gbcI);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}
}