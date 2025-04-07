package carDealership;

import java.util.Scanner;

import persistance.DealershipLayer;
import persistance.UserLayer;

import java.util.Arrays;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.JTree;


public class inventoryPage{
	private static final long serialVersionUID = -4235592661347719465L;
	private JFrame invMainFrame;
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
	private GridBagConstraints gbcC;
	private GridBagConstraints gbcI;
	private JScrollPane filterScroll;
	private inventoryPageController controller;
	public static DefaultListSelectionModel pageMenuModel;
	public static JComboBox pageMenuDD;
	private Component previousDisplay;
	private JLabel totalV;
	private JLabel cap;
	
	//page elements
	private static String[] sortMenuElements;
	private static String[] pageElements;
	private static String[] editInventoryElements;
	private static Image carIconImage;
	private static Image newCarImage;
	private static Image carHeaderImage;
	private static Image motoImage;
	private static Image newCarHeaderImage;
	private static JCheckBox[] makesCheck;
	private static JCheckBox[] colorsCheck;
	private static JCheckBox[] modelsCheck;
	private JSpinner maxSpinner;
	private static SpinnerNumberModel yearMaxSpinnerModel;
	private static SpinnerNumberModel yearMinSpinnerModel;
	private static JSlider priceSlider;
	public Color bgColor = new Color(230, 230, 230);
	private static JLabel min;
	private static JLabel max;
	private static boolean ignoreStateChange = false;
	private static boolean ignoreStateChangeSearch = false;
	private static JLabel at;
	private static JButton searchExit;
	private static JTextField searchBar ;
	
	public inventoryPage(inventoryPageController controller, int width, int height){
		//this.controller = new inventoryPageController();
		Image UmotoIconImage = (Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/moto.jpg")));
		motoImage = UmotoIconImage.getScaledInstance(80, 70,Image.SCALE_DEFAULT);
		carIconImage = (Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		newCarImage = carIconImage.getScaledInstance(80, 70,Image.SCALE_DEFAULT);
		carHeaderImage = (Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/backgroundd.jpg")));
		newCarHeaderImage = carHeaderImage.getScaledInstance(300, 150,Image.SCALE_DEFAULT);
		sortMenuElements = new String[]{"Sort By","Price Descending" ,"Price Ascending", "Make","Model", "Year Descending","Year Ascending" };	
		pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		editInventoryElements = new String[]{"", "Add Car", "Add Motorcycle", "Edit Vehicle", "Sell vehicle","Remove Vehicle"};
		this.controller = controller;
		prepareInventoryGUI(width, height);
		
	}
	public static void main(String[] args){
		//inventoryPage inventoryPage = new inventoryPage();
		//inventoryPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(int width, int height){
		//set standard font 
		UIManager.put("Label.font", new Font("HP Simplified Hans", Font.BOLD, 12));
        UIManager.put("Button.font", new Font("HP Simplified Hans", Font.BOLD, 12));
        UIManager.put("TextField.font", new Font("HP Simplified Hans", Font.BOLD, 12));
		//create window called invMainFrame
		invMainFrame = new JFrame("Inventory");
		invMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(inventoryPage.class.getResource("/images/icon.jpg")));
		invMainFrame.setBounds(0, 0, width, height);
		invMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});
		///create layout of panels DON'T CHANGE/////////////////////////////////////////////////////////
		controlPanel = new JPanel();
		controlPanel.setBackground(bgColor);
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setPreferredSize(new Dimension(655,655));
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);	
		gbcC = new GridBagConstraints();
		
		
		JLabel carImage = new JLabel("");
		carImage.setBackground(Color.BLACK);
		carImage.setOpaque(true);
		carImage.setIcon(new ImageIcon(newCarHeaderImage));
		carImage.setPreferredSize(new Dimension(645,150));
		carImage.setMaximumSize(new Dimension(650,150));
		
		gbcC.fill = GridBagConstraints.BOTH;
		JPanel searchBarBG = new JPanel();
		searchBarBG.setBackground(Color.GRAY);
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();
		//searchBarBG.setBounds(0, 150, 650, 50);
		searchBarBG.setPreferredSize(new Dimension(650,50));
		//controlPanel.add(searchBarBG);
		gbcC.anchor = GridBagConstraints.NORTH;  
		gbcC.gridx = 0;
		gbcC.gridwidth = 2;
		gbcC.gridy = 1;
		gbcC.weightx = .01;
		controlPanel.add(searchBarBG,gbcC);
		
		gbcC.anchor = GridBagConstraints.NORTH;
		JPanel displayBG = new JPanel();
		displayBG.setBackground(bgColor);
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(10, 200, 440, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		gbcC.anchor = GridBagConstraints.NORTH;  
		gbcC.gridx = 0;
		gbcC.gridwidth = 1;
		gbcC.gridy = 2;
		gbcC.weightx = .1;
		controlPanel.add(displayBG,gbcC);
		
		//filter panel
		filterBG = new JPanel();
		filterBG.setBackground(bgColor);
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		//filterBG.setPreferredSize(new Dimension(200, 365));
		filterBG.setMinimumSize(new Dimension(200, 365));
		//filterBG.setMaximumSize(new Dimension(200, 365));
		gbcF = new GridBagConstraints();
		
		//make filter a scrollpane when needed
		filterScroll = new JScrollPane(filterBG, JScrollPane. VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		filterScroll.setPreferredSize(new Dimension(200, 375));
		filterScroll.setMinimumSize(new Dimension(200, 375));
		filterScroll.setMaximumSize(new Dimension(200, 375));
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.anchor = GridBagConstraints.NORTHEAST;  
		gbcC.gridx = 1;
		gbcC.gridwidth = 1;
		gbcC.gridheight = 3;
		gbcC.gridy = 3;
		gbcC.weightx = .3;
		controlPanel.add(filterScroll,gbcC);
		////////////////////////////////////////////////////////////////////////////////
		
		//////////create page menu drop down///////////////////////////////////////////
		pageMenuDD = new JComboBox();
		for(String element : pageElements){
			pageMenuDD.addItem(element);
		}
		pageMenuModel = new DefaultListSelectionModel();
		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);
		pageMenuDD.setRenderer(pageMenuEnableRender);
		pageMenuDD.setPreferredSize(new Dimension(200, 30));
		gbcC.fill = GridBagConstraints.NONE;
		gbcC.anchor = GridBagConstraints.NORTHEAST;  
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		gbcC.gridwidth = 2;
		gbcC.gridheight = 1;
		gbcC.weightx = 0;
		
		controlPanel.add(pageMenuDD,gbcC);
		controller.setDisabledPages(pageMenuModel);//grey out menuItems the user does not have access to and get access list for pages
		pageMenuDD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Rectangle r = invMainFrame.getBounds();
				int h = r.height;
				int w = r.width;
				controller.pageMenuSelect(pageMenuDD.getSelectedIndex(), invMainFrame, w, h);
			}
		});
		gbcC.fill = GridBagConstraints.HORIZONTAL;
		gbcC.anchor = GridBagConstraints.NORTH;  
		gbcC.gridx = 0;
		gbcC.gridwidth = 2;
		gbcC.gridy = 0;
		controlPanel.add(carImage,gbcC);
		//////////////////////////////////////////////////////////////

		//////////inventory information/////////////////////////////////////////////////////
		cap = new JLabel("Size: " + controller.getTotalVehiclesInInventory());
		cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		//cap.setBounds(30, 600, 200, 25);
		gbcC.anchor = GridBagConstraints.SOUTHWEST;  
		gbcC.gridx = 0;
		gbcC.gridwidth = 1;
		gbcC.gridy = 6;
		gbcC.weighty = .1;
		controlPanel.add(cap,gbcC);
		
		totalV = new JLabel("Total Inventory Value: " + controller.getInventoryGrossValue());
		totalV.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		//totalV.setBounds(240, 600, 200, 25);
		gbcC.insets = new Insets(0, 50,0,0);
		gbcC.gridx = 0;
		gbcC.gridwidth = 1;
		gbcC.gridy = 6;
		gbcC.weighty = .1;
		gbcC.weightx = .1;
		gbcC.anchor = GridBagConstraints.SOUTHEAST;  
		controlPanel.add(totalV,gbcC);
		
		////////////////////////////////////////////////////////////////
		
		//////edit inventory drop down menu///////////////////////////////////////////////////////////
		JComboBox editInventoryMenu= new JComboBox();
		for(String element : editInventoryElements){
			editInventoryMenu.addItem(element);
			}
		DefaultListSelectionModel editInventoryModel = new DefaultListSelectionModel();
		EnabledJComboBoxRenderer editInventoryEnableRender = new EnabledJComboBoxRenderer(editInventoryModel);
		controller.setDisabledEdits(editInventoryModel); //disable menu items based on job security
		editInventoryMenu.setRenderer(editInventoryEnableRender);
		editInventoryMenu.setBounds(450, 20, 200, 25);

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
		
		editInventoryMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.editMenuSelect(editInventoryMenu.getSelectedIndex());
			}
		});
		
		
		//////////////////////////////////////////////////////////////////
			
		///////search bar and buttonn///////////////////////////////////////////////////////////////
		//search bar on right hand side
		
		gbcS.anchor = GridBagConstraints.EAST;
		searchExit  = new JButton("X");
		searchExit.setFont(new Font("HP Simplified Hans", Font.PLAIN, 5));
		gbcS.gridx = 2;
		gbcS.gridy = 1;
		gbcS.insets = new Insets(-28,8,0,20);
		searchExit.setMargin(new java.awt.Insets(0,1,0,1));
		//gbcS.fill = GridBagConstraints.VERTICAL;	
		searchBarBG.add(searchExit, gbcS);
		searchExit.setEnabled(false);
		searchExit.setVisible(false);
		
		gbcS.anchor = GridBagConstraints.CENTER;
		gbcS.fill = GridBagConstraints.NONE;
		searchBar  = new JTextField("Search ID", 15);
		searchBar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		gbcS.insets = new Insets(0,0,0,0);
		gbcS.fill = GridBagConstraints.VERTICAL;	
		searchBarBG.add(searchBar, gbcS);
		
		searchExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!searchBar.getText().matches("Search ID")){
					//System.out.println("searchExit");
					searchBar.setText("Search ID");
					controller.searchExit();
					controlPanel.remove(inventoryBG);
					searchExit.setEnabled(false);
					searchExit.setVisible(false);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}
				ignoreStateChangeSearch = false;
			}
		});
		//searchButton
		JButton MagButton = new JButton("S");
		//gbcS.insets = new Insets(0,0,0,0);
		gbcS.gridx = 6;
		gbcS.gridy = 0;
		searchBarBG.add(MagButton, gbcS);
		
		ActionListener searchListen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!searchBar.getText().matches("Search ID")){
					//System.out.println("searchBar");
					String searchString = searchBar.getText();
					
					controller.search(searchString);
					controlPanel.remove(inventoryBG);
					searchExit.setEnabled(true);
					searchExit.setVisible(true);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}
				ignoreStateChangeSearch = false;
			}
		};
		MagButton.addActionListener(searchListen);
		searchBar.addActionListener(searchListen);
		//clear out placeholder text 
		searchBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = searchBar.getText(); 
				if(text.matches("Search ID")){	
					searchBar.setText("");
				}
				
			}
			});
		///////////////////////////////////////////////////////////////////
		
		////////display options///////////////////////////////////////////////////////////////
		gbcD.anchor = GridBagConstraints.NORTHWEST;
		ButtonGroup G = new ButtonGroup();
		JRadioButton dAll = new JRadioButton("Display All"); 
		JRadioButton dMoto = new JRadioButton("Motocycles Only");
		JRadioButton dCar = new JRadioButton("Cars Only");
		G.add(dAll);
		G.add(dMoto);
		G.add(dCar);
		dAll.setName("dAll");
		dAll.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		dAll.setBackground(bgColor);
		dMoto.setName("dMoto");
		dMoto.setBackground(bgColor);
		dMoto.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		dCar.setName("dCar");
		dCar.setBackground(bgColor);
		dCar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		dAll.setSelected(true);
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
		
		ActionListener displayListen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String displayString = "";
				if(dCar.isSelected()){
					displayString = "car";
				}if(dMoto.isSelected()){
					displayString = "motorcycle";
				}if(dAll.isSelected()){
					displayString = "all";
				}
				controller.getDisplayDisplay(displayString);
				controller.getFilterDisplay();
				controlPanel.remove(inventoryBG);
				controlPanel.validate();
				controlPanel.repaint();
				invMainFrame.validate();
				refreshInventory();
			}
		};
		
		dCar.addActionListener(displayListen);
		dMoto.addActionListener(displayListen);
		dAll.addActionListener(displayListen);
		gbcD.anchor = GridBagConstraints.NORTHEAST;
		final JComboBox sortMenu = new JComboBox();
		
		
		////sort menu//////////////////////////////////////////
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
		
		sortMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.sortMenuSelect(sortMenu.getSelectedIndex());
				}
			});
		/////////////////////////////////////////////////////////////////
		
		
		//filter setup/////////////////////////////////////////////////////////
		//make filter//////////////////////////
		int yBox = 0;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel makeL = new JLabel("Make");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		filterBG.add(makeL, gbcF);
		
		//make checkbox for eachMake
		String[] makes = controller.getMakes();
		makesCheck = new JCheckBox[makes.length];
		int m = 0;
		for(String make : makes){
			yBox++;
			final JCheckBox makeName = new JCheckBox(make);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			makeName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			makeName.setName(make + "Check");
			filterBG.add(makeName, gbcF);
			makesCheck[m] = (makeName);
			m++;
		}
		
		ItemListener makesListener = new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					String makeSel = "";
					for(JCheckBox makeC : makesCheck){
						if(makeC.isSelected()){
							makeSel += (" '" + makeC.getText() + "', ");
						}
						
					}
					makeSel = makeSel.replaceAll(", $", "");

					controller.filterMakes(makeSel);
					controller.getFilterDisplay();
					controlPanel.remove(inventoryBG);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}
			};
			
		for(JCheckBox makeC : makesCheck){
			makeC.addItemListener(makesListener);
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
		modelsCheck = new JCheckBox[models.length];
		int mo = 0;
		for(String model : models){
			yBox++;
			final JCheckBox modelName = new JCheckBox(model);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			modelName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			modelName.setName(model + "Check");
			filterBG.add(modelName, gbcF);
			modelsCheck[mo] = modelName;
			mo++;
		}
		
		ItemListener modelsListener = new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					String modelSel = "";
					for(JCheckBox modelC : modelsCheck){
						if(modelC.isSelected()){
							modelSel += (" '" + modelC.getText() + "', ");
						}
						
					}
					modelSel = modelSel.replaceAll(", $", "");
					
					controller.filterModels(modelSel);
					controller.getFilterDisplay();
					controlPanel.remove(inventoryBG);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}
			};
			
		for(JCheckBox modelC : modelsCheck){
			modelC.addItemListener(modelsListener);
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
		colorsCheck = new JCheckBox[colors.length];
		int c = 0;
		for(String color : colors){
			yBox++;
			final JCheckBox colorName = new JCheckBox(color);
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			colorName.setBackground(new Color(230, 230, 230));
			gbcF.gridx = 0;
			gbcF.gridy = yBox++;
			colorName.setName(color + "Check");
			filterBG.add(colorName, gbcF);
			colorsCheck[c] = colorName;
			c++;
		}
		
		ItemListener colorsListener = new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					String colorSel = "";
					for(JCheckBox colorC : colorsCheck){
						if(colorC.isSelected()){
							colorSel += (" '" + colorC.getText() + "', ");
						}
						
					}
					colorSel = colorSel.replaceAll(", $", "");
					
					controller.filterColors(colorSel);
					controller.getFilterDisplay();
					controlPanel.remove(inventoryBG);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}
			};
			
		for(JCheckBox colorC : colorsCheck){
			colorC.addItemListener(colorsListener);
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
		yearMinSpinnerModel = new SpinnerNumberModel(controller.getMinYear(),controller.getMinYear(),controller.getMaxYear(), 1);//min, max,step
		JSpinner minSpinner = new JSpinner(yearMinSpinnerModel);
		JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(minSpinner, "#");
		minSpinner.setEditor(minEditor);	
		minSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.insets = new Insets(0, 10, 0, 0);
		filterBG.add(minSpinner, gbcF);
		
		//create spinner to set the newest year
		gbcF.anchor = GridBagConstraints.NORTHEAST;
		yearMaxSpinnerModel = new SpinnerNumberModel(controller.getMaxYear(),controller.getMinYear(), controller.getMaxYear(),1);
		maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		maxSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox;
		//gbcF.insets = new Insets(0, 15, 0, 0);
		filterBG.add(maxSpinner, gbcF);
		
		//add dash between two spinners
		gbcF.anchor = GridBagConstraints.CENTER;
		gbcF.fill = GridBagConstraints.BOTH;
		JLabel dashyear = new JLabel(" - ");
		dashyear.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		gbcF.insets = new Insets(0, 15, 0, 0);
		filterBG.add(dashyear, gbcF);
		
		ChangeListener yearListener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				//System.out.println("state" + ignoreStateChange);
				if(!ignoreStateChange){
					int minYear = (int) minSpinner.getValue();
					int maxYear = (int)maxSpinner.getValue();
					controller.filterYears(minYear, maxYear);
					controller.getFilterDisplay();
					controlPanel.remove(inventoryBG);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
				}		
			}
		};
		
		minSpinner.addChangeListener(yearListener);
		maxSpinner.addChangeListener(yearListener);
		
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
		priceSlider = new JSlider((int)controller.getMinPrice(), (int)controller.getMaxPrice(), (int)controller.getMaxPrice());
		priceSlider.setBackground(bgColor);
		gbcF.insets = new Insets(0, -15, 0, 0);
		gbcF.gridwidth = 2;
		gbcF.gridx = 0;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(priceSlider, gbcF);
		
		//label min price
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		min = new JLabel(Integer.toString((int)minPrice));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = yBox;
		gbcF.ipady = 0;
		gbcF.insets = new Insets(0,0, 0, 0);
		//gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(min, gbcF);
		
		//label max price
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		max = new JLabel(Integer.toString((int)maxPrice));
		gbcF.insets = new Insets(0, -30, 0, 0);
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = yBox;
		gbcF.weighty =.2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);
		
		//location of slider
		gbcF.anchor = GridBagConstraints.CENTER;
		at = new JLabel(Integer.toString(priceSlider.getValue()));
		at.setOpaque(true);
		//at.setForeground(Color.WHITE);
		at.setBackground(Color.WHITE);
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.insets = new Insets(0,-70, 0, 0);
		gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(at, gbcF);
		
		gbcF.anchor = GridBagConstraints.CENTER;
		JButton removeChanges = new JButton("Remove Filters");
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = yBox++;
		gbcF.ipady = 0;
		gbcF.insets = new Insets(30,-70, 0, 0);
		gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(removeChanges, gbcF);
		
		removeChanges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkDemark();
			}
		});
		ChangeListener priceListener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				if(!ignoreStateChange){
				if (!priceSlider.getValueIsAdjusting()) {
					int newMaxPrice = (int) priceSlider.getValue();
					controller.filterPrice((int)minPrice, newMaxPrice);
					controller.getFilterDisplay();
					controlPanel.remove(inventoryBG);
					controlPanel.validate();
					controlPanel.repaint();
					invMainFrame.validate();
					refreshInventory();
					}
				}
				at.setText(Integer.toString(priceSlider.getValue()));
				ignoreStateChange = false;
			}
		};
		
		priceSlider.addChangeListener(priceListener);
		//////////////////////////////
		
		//create inventory list
		refreshInventory();
		invMainFrame.setContentPane(controlPanel);
		//invMainFrame.setVisible(true);
		
	}
	
	
	public void refreshMakes(){
		String[] filteredMakes = controller.getFilteredMakes();
		this.ignoreStateChange = true;
		for(JCheckBox check : makesCheck){
			String nameCheck = check.getName();
			String name = nameCheck.replaceAll("Check", "");
			if(Arrays.asList(filteredMakes).contains(name)){
				check.setEnabled(true);
			}else{check.setEnabled(false);}
		}	
	}
	
	public void refreshModels(){
		String[] filteredModels = controller.getFilteredModels();
		this.ignoreStateChange = true;
		for(JCheckBox check : modelsCheck){
			String nameCheck = check.getName();
			String name = nameCheck.replaceAll("Check", "");
			if(Arrays.asList(filteredModels).contains(name)){
				check.setEnabled(true);
			}else{check.setEnabled(false);}
		}	
	}
	
	public void refreshColors(){
		String[] filteredColors = controller.getFilteredColors();
		this.ignoreStateChange = true;
		for(JCheckBox check : colorsCheck){
			String nameCheck = check.getName();
			String name = nameCheck.replaceAll("Check", "");
			if(Arrays.asList(filteredColors).contains(name)){
				check.setEnabled(true);
			}else{check.setEnabled(false);}
		}	
	}
	
	public void refreshYears(){
		int[] newYears = controller.getFilteredYears();
		this.ignoreStateChange = true;
		if((int) yearMinSpinnerModel.getNumber() < newYears[1]){
			yearMinSpinnerModel.setValue(newYears[1]);
		}
		if((int)yearMaxSpinnerModel.getNumber() > newYears[0]){
			yearMaxSpinnerModel.setValue(newYears[0]);
		}
		
	}
	
	public void refreshPrices(){
		int[] newPrice = controller.getFilteredPrice();
		this.ignoreStateChange = true;
		if(newPrice[1] <  priceSlider.getValue()){
			priceSlider.setValue(newPrice[1]);
			min.setText(Integer.toString((int)newPrice[0]));
			//max.setText(Integer.toString((int)newPrice[1]));
		}
		min.setText(Integer.toString((int)newPrice[0]));
		//max.setText(Integer.toString((int)newPrice[1]));
	}
		
	public void checkDemark(){
		JCheckBox[][] allCheck = {makesCheck, modelsCheck, colorsCheck};
		for(JCheckBox[] type: allCheck){
			for(JCheckBox check: type){
				check.setSelected(false);
			}
		}
		priceSlider.setValue((int)maxPrice);
		yearMaxSpinnerModel.setValue(controller.getMaxYear());
		yearMinSpinnerModel.setValue(controller.getMinYear());
	}
	
	public void searchRemove(){
		if(searchBar.getText() != "Search ID"){
			this.ignoreStateChangeSearch = true;
			searchBar.setText("Search ID");
			controller.searchExit();
			searchExit.setEnabled(false);
			searchExit.setVisible(false);
		}
		}
	private void refreshInventory(){
		//inventory image list
		//////////inventory information/////////////////////////////////////////////////////
		cap.setText("Size: " + controller.getTotalVehiclesInInventory());
		totalV.setText("Total Inventory Value: " + controller.getInventoryGrossValue());		
		////////////////////////////////////////////////////////////////
		
		//create panels for layout////////////////////////////
		inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		gbcI = new GridBagConstraints();
		
		
		
		invMainFrame.validate();
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
		
		//int numberToShow = controller.getNumbertoDisplay();
		//create an panel for each vehicle that should be shown and name///////////////////////
		int newVehicles = controller.getNumbertoDisplay() + 1;
		JPanel[] vehiclePanelNames = new JPanel[0];
		vehiclePanelNames= new JPanel[controller.getNumbertoDisplay() + 1];
		for (int i = 0; i < newVehicles; i++) {
			String num = Integer.toString(i);
			JPanel vehicle = new JPanel();
			//add each panel to the array
			vehiclePanelNames[i] = vehicle;
			//name each panel so that can be called later if needed
			vehicle.setName("car" + i);
		}

		String[] vehicles = controller.getAllDisplayInfo();
		int newInfoLength = controller.getNumbertoDisplay();
		for (int i = 0; i < newInfoLength; i++) {
			//set standard layout for each panel/////
			vehiclePanelNames[i].setBackground(Color.WHITE);
			vehiclePanelNames[i].setLayout(new GridBagLayout());
			GridBagLayout layoutC1 = new GridBagLayout();
			vehiclePanelNames[i].setLayout(layoutC1);
			GridBagConstraints gbcC1 = new GridBagConstraints();
			//////////////////////////////////////////
			//add image to each panel/////////////////////
			JLabel c1Img = new JLabel("");
			int index = vehicles[i].indexOf("Handlebar");
			if(index != -1){c1Img.setIcon(new ImageIcon(motoImage));}
			else{c1Img.setIcon(new ImageIcon(newCarImage));}
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
			gbcC1.anchor = GridBagConstraints.NORTHWEST;
			gbcC1.fill = GridBagConstraints.BOTH;
			gbcC1.gridx = 0;
			gbcC1.gridy = 1;
			gbcC1.weightx = .2;
			gbcC1.weighty = 1;
			vehiclePanelNames[i].add(c1Info, gbcC1); 
			
			//have 2 vehicles per row
			gbcvehicleInv.gridx = (int)Math.floor(i%2);
			gbcvehicleInv.gridy = (int)Math.floor(i/2);
			gbcvehicleInv.anchor = GridBagConstraints.NORTH;
			gbcvehicleInv.fill = GridBagConstraints.BOTH;
			//add weight to last entry, you need weight to get anchor to work
			if(i == newInfoLength-1 || i == newInfoLength-2 ){
				gbcvehicleInv.weightx = .5;
				gbcvehicleInv.weighty = .5;
			}
			//////////////////////////////////////////////
			
			//add vehicles to the scrollpane that holds the inventory
			vehicleInv.add(vehiclePanelNames[i], gbcvehicleInv);
			vehiclePanelNames[i].setFocusable(true);
			vehiclePanelNames[i].addMouseListener(mml);
			///////////////////////////////////
		}
		
		//adds vehicle Inventory list to a scroll pane
		JScrollPane inventoryListScrollPane = new JScrollPane(vehicleInv,20 ,30);   
		inventoryListScrollPane.setPreferredSize(new Dimension(440, 375));		
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcI.ipady = 0;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(-10,0,0,0);
		inventoryBG.add(inventoryListScrollPane, gbcI);
		//////////////////////////////////////////////////////////////////////////
		//inventoryBG.setPreferredSize(new Dimension(440, 375));
		gbcC.anchor = GridBagConstraints.WEST;  
		gbcC.fill = GridBagConstraints.BOTH;  
		gbcC.insets = new Insets(0,0,0,0);
		gbcC.gridx = 0;
		gbcC.gridwidth = 1;
		gbcC.gridheight = 3;
		gbcC.gridy = 3;
		gbcC.weighty = .9;
		gbcC.weightx = 1;
		controlPanel.add(inventoryBG,gbcC);
		inventoryListScrollPane.validate();
		inventoryListScrollPane.setWheelScrollingEnabled(true);
		invMainFrame.validate();
		invMainFrame.repaint();
		invMainFrame.setVisible(true);
		}

	public void editVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");

			if (idString == null) { // checks if no input
				return;
			}

			int id = Integer.parseInt(idString);
			
			//check if vehicle entered exsist
			//controller.getIndexFromId(id); 
			
			String[] vehicle = controller.getVehicleFromId(id);

			JTextField makeField = new JTextField();
			JTextField modelField = new JTextField();
			JTextField colorField = new JTextField();
			JTextField yearField = new JTextField();
			JTextField priceField = new JTextField();
			JTextField typeField = new JTextField();
			JTextField handlebarField = new JTextField();

			JPanel editPanel = new JPanel();
			editPanel.setLayout(new GridLayout(0, 2));
			if(vehicle.length != 0){
				if (vehicle[6] == "car") {
					//all functions in Car.java
					editPanel.add(new JLabel("Make:"));
					makeField.setText(vehicle[0]);
					editPanel.add(makeField);

					editPanel.add(new JLabel("Model:"));
					modelField.setText(vehicle[1]);
					editPanel.add(modelField);

					editPanel.add(new JLabel("Color:"));
					colorField.setText(vehicle[2]);
					editPanel.add(colorField);

					editPanel.add(new JLabel("Year:"));
					yearField.setText(vehicle[3]);
					editPanel.add(yearField);

					editPanel.add(new JLabel("Price:"));
					priceField.setText(vehicle[4]);
					editPanel.add(priceField);

					editPanel.add(new JLabel("Type:"));
					typeField.setText(vehicle[5]);
					editPanel.add(typeField);

				} else if (vehicle[6] == "motorcycle") {
					//all functions in Motorcycle.java
					editPanel.add(new JLabel("Make:"));
					makeField.setText(vehicle[0]);
					editPanel.add(makeField);

					editPanel.add(new JLabel("Model:"));
					modelField.setText(vehicle[1]);
					editPanel.add(modelField);

					editPanel.add(new JLabel("Color:"));
					colorField.setText(vehicle[2]);
					editPanel.add(colorField);

					editPanel.add(new JLabel("Year:"));
					yearField.setText(vehicle[3]);
					editPanel.add(yearField);

					editPanel.add(new JLabel("Price:"));
					priceField.setText(vehicle[4]);
					editPanel.add(priceField);

					editPanel.add(new JLabel("Handlebar Type:"));
					handlebarField.setText(vehicle[5]);
					editPanel.add(handlebarField);
				}

				int option = JOptionPane.showConfirmDialog(null, editPanel, "Edit Vehicle", JOptionPane.OK_CANCEL_OPTION);
				boolean editS = false;
				if (option == JOptionPane.OK_OPTION) { // edit and set
					if (vehicle[6] == "car") {
						String setMake = (makeField.getText());
						String setModel = (modelField.getText());
						String setColor = (colorField.getText());
						String setYear = (yearField.getText());
						String setPrice = (priceField.getText());
						String setType = (typeField.getText());
						String[] editVehicle = {Integer.toString(id), setMake, setModel, setColor, setYear, setPrice, setType};
						editS = controller.editCar(editVehicle);
					} else if (vehicle[6] == "motorcycle") {
						String setMake = (makeField.getText());
						String setModel = (modelField.getText());
						String setColor = (colorField.getText());
						String setYear = (yearField.getText());
						String setPrice = (priceField.getText());
						String setType = (handlebarField.getText());
						String[] editVehicle = {Integer.toString(id), setMake, setModel, setColor, setYear, setPrice, setType};
						editS = controller.editMotorcycle(editVehicle);
					}
					if(editS){
						JOptionPane.showMessageDialog(null, "Vehicle edited successfully.");
						controlPanel.remove(inventoryBG);
						controlPanel.remove(filterBG);
						controlPanel.validate();
						controlPanel.repaint();
						invMainFrame.validate();
						
						refreshInventory();
					}else{System.out.println("edit error");}
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Year and price must be numeric values.");
		}
	}
	
	public void sellVehicleMenu() {
		String buyerName = "";
		String buyerContact = ""; 
		String[] vehicle = new String[6];
		int id = 0;
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			id = Integer.parseInt(idString);
			if(controller.vehicleExsist(id)){
				buyerName = JOptionPane.showInputDialog(null, "Enter the buyer's name:");
				buyerContact = JOptionPane.showInputDialog(null, "Enter the buyer's contact:");
				vehicle = controller.getVehicleFromId(id);
			}
			
			if (controller.sellVehicle(id, buyerName, buyerContact)) {
				JOptionPane.showMessageDialog(null, "Vehicle sold successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterBG);
				controlPanel.validate();
				controlPanel.repaint();
				invMainFrame.validate();
				refreshInventory();
			} else {
				JOptionPane.showMessageDialog(null, "Couldn't sell vehicle.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
		}
	}
	
	public void removeVehicleMenu() {
		try {
			String idString = JOptionPane.showInputDialog(null, "Enter the id of the vehicle:");
			if (idString == null) { // checks if no input
				return;
			}
			int id = Integer.parseInt(idString);
			if (controller.vehicleExsist(id) == false) {
				JOptionPane.showMessageDialog(null, "Vehicle not found!");
			} else {
				String[] vehicle = controller.getVehicleFromId(id);
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this vehicle\nwith id: " + id, "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					if (controller.removeVehicle(id)) {
						JOptionPane.showMessageDialog(null, "Vehicle removed successfully.");
						controlPanel.remove(inventoryBG);
						controlPanel.remove(filterBG);
						controlPanel.validate();
						controlPanel.repaint();
						invMainFrame.validate();
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

			if (controller.addCar(make, model, color, year, (int) price, type)){
				JOptionPane.showMessageDialog(null, "Car has been added successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterBG);
				refreshInventory();
				invMainFrame.validate();
				controlPanel.validate();
				controlPanel.repaint();
				
				
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

			if (controller.addMotorcycle(make, model, color, year, (int) price, handlebarType)){
				JOptionPane.showMessageDialog(null, "Motorcycle has been added successfully.");
				controlPanel.remove(inventoryBG);
				controlPanel.remove(filterBG);
				controlPanel.validate();
				controlPanel.repaint();
				invMainFrame.validate();
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