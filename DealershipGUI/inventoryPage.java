
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
import java.awt.Image;

public class inventoryPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public inventoryPage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      inventoryPage inventoryPage = new inventoryPage();
      //inventoryPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Inventory");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 665, 665);
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
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
		
		//page menu in upper right hand corner
		final DefaultComboBoxModel pageMenuDD = new DefaultComboBoxModel();
		pageMenuDD.addElement("");
		pageMenuDD.addElement("Inventory");
		pageMenuDD.addElement("Dealership Info");
		pageMenuDD.addElement("Sales History");
		pageMenuDD.addElement("Manage User Accounts");
		pageMenuDD.addElement("Sign Out");
		
		
		
		final JComboBox pageMenuDDB = new JComboBox(pageMenuDD);    
		pageMenuDDB.setSelectedIndex(0);
		JScrollPane pageMenuDDP = new JScrollPane(pageMenuDDB);  
		pageMenuDDP.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDDP);
		//actions for page drop down
		pageMenuDDB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (pageMenuDDB.getSelectedIndex() == 2){
				dealerShipInfoPage dealer = new dealerShipInfoPage();
				mainFrame.dispose();
			} else if(pageMenuDDB.getSelectedIndex() == 3){
				pastSalesPage sales = new pastSalesPage();
				mainFrame.dispose();
			}else if(pageMenuDDB.getSelectedIndex() == 4){
				accountManagePage accounts = new accountManagePage();
				mainFrame.dispose();
			}else if(pageMenuDDB.getSelectedIndex() == 5){
				loginPage login = new loginPage();
				mainFrame.dispose();
			}
         }          
		});
		//capacity and price on bottom
		JLabel cap = new JLabel("Size: 4/90");
		//cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		cap.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		cap.setBounds(30, 600, 200, 25);
		controlPanel.add(cap);
		
		JLabel totalV = new JLabel("Total Inventory Value: 10,000");
		totalV.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		totalV.setBounds(240, 600, 200, 25);
		controlPanel.add(totalV);
		
		
		//edit inventory button on search bar left hand side
		final DefaultComboBoxModel addVehicle = new DefaultComboBoxModel();
		addVehicle.addElement("");
		addVehicle.addElement("Add Car");
		addVehicle.addElement("Add Motorcycle");
		addVehicle.addElement("Edit Vehicle");
		addVehicle.addElement("Sell Vehicle");
		addVehicle.addElement("Remove Vehicle");

		gbcS.anchor = GridBagConstraints.NORTHWEST;
		final JComboBox addVehicleBox = new JComboBox(addVehicle);    
		addVehicleBox.setSelectedIndex(0);
		//addVehicleBox.setForeground(Color.GRAY);
		JScrollPane addVehiclePane = new JScrollPane(addVehicleBox);    
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
		gbcS.insets = new Insets(0,10,0,0);
		searchBarBG.add(editLabel, gbcS); 
		
		//search bar on right hand side
		JTextField searchBar  = new JTextField("Search ID", 15);
		searchBar.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		//gbcS.anchor = GridBagConstraints.NORTHEAST;
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
		
		searchBarBG.add(MagButton, gbcS);
		
		//filter portion right hand side
		//make filter
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel make = new JLabel("Make");
		make.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 3;
		gbcF.ipady = 0;
		filterBG.add(make, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox Honda = new JCheckBox("Honda");
		Honda.setBackground(new Color(230, 230, 230));
		Honda.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox BMW = new JCheckBox("BMW");
		BMW.setBackground(new Color(230, 230, 230));
		BMW.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox Volkswagon = new JCheckBox("Volkswagon");
		Volkswagon.setBackground(new Color(230, 230, 230));
		Volkswagon.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridx = 0;
		gbcF.gridy = 4;
		filterBG.add(Honda, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 5;
		filterBG.add(BMW, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 6;
		
		filterBG.add(Volkswagon, gbcF);
		

		//model filter
		gbcF.anchor = GridBagConstraints.NORTH;
		
		JLabel model = new JLabel("Model");
		model.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 7;
		gbcF.ipady = 0;
		filterBG.add(model, gbcF);
		
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox accord = new JCheckBox("Accord");
		accord.setBackground(new Color(230, 230, 230));
		accord.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox oneSeries = new JCheckBox("1 Series");
		oneSeries.setBackground(new Color(230, 230, 230));
		oneSeries.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox bug = new JCheckBox("Beetle");
		bug.setBackground(new Color(230, 230, 230));
		bug.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.ipady = 0;
		gbcF.gridx = 0;
		gbcF.gridy = 8;
		filterBG.add(accord, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 9;
		filterBG.add(oneSeries, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 10;
		filterBG.add(bug, gbcF);
		
		
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel color = new JLabel("Color");
		color.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 11;
		gbcF.ipady = 0;
		filterBG.add(color, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		final JCheckBox Blue = new JCheckBox("Blue");
		Blue.setBackground(new Color(230, 230, 230));
		Blue.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox Yellow = new JCheckBox("Yellow");
		Yellow.setBackground(new Color(230, 230, 230));
		Yellow.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		final JCheckBox Red = new JCheckBox("Red");
		Red.setBackground(new Color(230, 230, 230));
		Red.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridx = 0;
		gbcF.gridy = 12;
		filterBG.add(Blue, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 13;
		filterBG.add(Yellow, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 14;
		filterBG.add(Red, gbcF);
		//year filter
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel year = new JLabel("Year");
		year.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 15;
		gbcF.ipady = 0;
		
		filterBG.add(year, gbcF);

		gbcF.anchor = GridBagConstraints.NORTHWEST;
		SpinnerModel yearMinSpinnerModel = new SpinnerNumberModel(1900,1900,2025, 1);//min, max,step
		JSpinner minSpinner = new JSpinner(yearMinSpinnerModel);
		JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(minSpinner, "#");
		minSpinner.setEditor(minEditor);	
		minSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 16;
		gbcF.insets = new Insets(0, 5, 0, 0);
		filterBG.add(minSpinner, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHEAST;
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(2025,1900, 2025,1);
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		JSpinner.NumberEditor maxEditor = new JSpinner.NumberEditor(maxSpinner, "#");
		maxSpinner.setEditor(maxEditor);
		maxSpinner.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = 16;
		//gbcF.insets = new Insets(0, 0, 0, 20);
		filterBG.add(maxSpinner, gbcF);
		
		gbcF.anchor = GridBagConstraints.CENTER;
		gbcF.fill = GridBagConstraints.BOTH;
		JLabel dashyear = new JLabel(" - ");
		dashyear.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = 16;
		gbcF.insets = new Insets(0, -15, 0, 0);
		filterBG.add(dashyear, gbcF);
		
		gbcF.fill = GridBagConstraints.NONE;
		gbcF.anchor = GridBagConstraints.NORTH;
		JLabel price = new JLabel("Price");
		price.setFont(new Font("HP Simplified Hans", Font.BOLD, 12));
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 17;
		gbcF.insets = new Insets(5, 0, 0, 0);
		filterBG.add(price, gbcF);
		
		//price slider
		gbcF.anchor = GridBagConstraints.NORTH;
		JSlider priceSlider = new JSlider(0, 5000, 5000);
		priceSlider.setBackground(new Color(230, 230, 230));
		gbcF.insets = new Insets(0, 10, 0, 0);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 18;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(priceSlider, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel min = new JLabel("0");
		min.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 19;
		gbcF.ipady = 0;
		gbcF.weightx = .2;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		filterBG.add(min, gbcF);
		
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JLabel max = new JLabel("5000");
		max.setFont(new Font("HP Simplified Hans", Font.PLAIN, 12));
		gbcF.insets = new Insets(0, -15, 0, 0);
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = 19;
		gbcF.weighty =.2;
		gbcF.fill = GridBagConstraints.NONE;
		filterBG.add(max, gbcF);
		
		
		
		//display options
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
		//gbcD.insets = new Insets(0,-50,0,0);
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
	
	
		//inventory list
		final DefaultListModel inventoryModel = new DefaultListModel();

		inventoryModel.addElement("Blue Honda Accord 2013");
		inventoryModel.addElement("Red BMW Series One 2020");
		inventoryModel.addElement("Yellow Volkswagon Beetle 1960");



		
		
		
		
		//inventory image list
		JPanel car1 = new JPanel();
		car1.setBackground(Color.WHITE);
		car1.setLayout(new GridBagLayout());
		GridBagLayout layoutC1 = new GridBagLayout();
		car1.setLayout(layoutC1);
		GridBagConstraints gbcC1 = new GridBagConstraints();
		
		JLabel c1Img = new JLabel("");
		Image carIconImage = (Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		Image newCarImage = carIconImage.getScaledInstance(80, 70,Image.SCALE_DEFAULT);
		c1Img.setIcon(new ImageIcon(newCarImage));
		gbcC1.gridx = 0;
		gbcC1.gridy = 0;
		gbcC1.anchor = GridBagConstraints.NORTHWEST;
		car1.add(c1Img, gbcC1);
		JTextArea c1Info = new JTextArea(6, 20);
		c1Info.setForeground(Color.BLACK);
		c1Info.setText("ID: 1 \nMake: Honda \nModel: Accord \nColor: Blue \nYear: 2012 \nPrice: 1,000 ");
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
		
		JPanel car2 = new JPanel();
		car2.setBackground(Color.WHITE);
		car2.setLayout(new GridBagLayout());
		GridBagLayout layoutC2 = new GridBagLayout();
		car2.setLayout(layoutC2);
		GridBagConstraints gbcC2 = new GridBagConstraints();
		
		JPanel car3 = new JPanel();
		car3.setBackground(Color.WHITE);
		car3.setLayout(new GridBagLayout());
		GridBagLayout layoutC3 = new GridBagLayout();
		car3.setLayout(layoutC3);
		GridBagConstraints gbcC3 = new GridBagConstraints();
		
		JLabel c3Img = new JLabel("");
		c3Img.setIcon(new ImageIcon(newCarImage));
		gbcC3.gridx = 0;
		gbcC3.gridy = 0;
		gbcC3.weighty = 0;
		gbcC3.anchor = GridBagConstraints.NORTHWEST;
		car3.add(c3Img, gbcC3);
		
		JTextArea c3Info = new JTextArea(6, 20);
		c3Info.setForeground(Color.BLACK);
		c3Info.setText("ID: 2 \nMake: BMW \nModel: 1 Series \nColor: Red \nYear: 2024 \nPrice: 2,000 ");
		c3Info.setWrapStyleWord(true);
		c3Info.setLineWrap(true);
		c3Info.setOpaque(false);
		c3Info.setEditable(false);
		c3Info.setFocusable(false);
		gbcC3.anchor = GridBagConstraints.NORTHWEST;
		gbcC3.gridx = 0;
		gbcC3.gridy = 1;
		gbcC3.weightx = .9;
		gbcC3.weighty = 1;
		car3.add(c3Info, gbcC3);
		
		JLabel c2Img = new JLabel("");
		c2Img.setIcon(new ImageIcon(newCarImage));
		gbcC2.gridx = 0;
		gbcC2.gridy = 0;
		gbcC2.weighty = 0;
		gbcC2.anchor = GridBagConstraints.NORTHWEST;
		car2.add(c2Img, gbcC2);
		
		JTextArea c2Info = new JTextArea(6, 20);
		c2Info.setForeground(Color.BLACK);
		c2Info.setText("ID: 2 \nMake: Volkswagon \nModel: Beetle \nColor: Yellow \nYear: 1950 \nPrice: 500 ");
		c2Info.setWrapStyleWord(true);
		c2Info.setLineWrap(true);
		c2Info.setOpaque(false);
		c2Info.setEditable(false);
		c2Info.setFocusable(false);
		gbcC2.anchor = GridBagConstraints.NORTHWEST;
		gbcC2.gridx = 0;
		gbcC2.gridy = 1;
		gbcC2.weightx = .9;
		gbcC2.weighty = 1;
		car2.add(c2Info, gbcC2);

		JPanel carInv = new JPanel();
		carInv.setBackground(Color.WHITE);
		carInv.setBounds(10, 225, 440, 375);
		carInv.setLayout(new GridBagLayout());
		GridBagLayout layoutCarInv = new GridBagLayout();
		carInv.setLayout(layoutCarInv);
		GridBagConstraints gbcCarInv = new GridBagConstraints();
		gbcCarInv.insets = new Insets(5,5,5,5);
		gbcC2.anchor = GridBagConstraints.NORTHWEST;
		gbcCarInv.gridx = 1;
		gbcCarInv.gridy = 0;
		gbcCarInv.weightx = .9;
		gbcCarInv.weighty = 1;
		carInv.add(car1, gbcCarInv);
		gbcCarInv.gridx = 0;
		gbcCarInv.gridy = 0;
		carInv.add(car2, gbcCarInv);
		gbcCarInv.gridx = 0;
		gbcCarInv.gridy = 1;
		carInv.add(car3, gbcCarInv);
		
		
		final JList inventoryList = new JList(inventoryModel);
		inventoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		inventoryList.setSelectedIndex(0);
		inventoryList.setVisibleRowCount(19);
		JScrollPane inventoryListScrollPane = new JScrollPane(carInv);   
		inventoryListScrollPane.setPreferredSize(new Dimension(440, 375));		
		inventoryList.setFont(new Font("HP Simplified Hans", Font.PLAIN, 14));
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