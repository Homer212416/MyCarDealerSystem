
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
		controlPanel.setLayout(layout);	
		GridBagConstraints gbc = new GridBagConstraints();
		
		//JLabel dropDown = new JLabel("Page Select", JLabel.LEFT);
		final DefaultComboBoxModel dropDown = new DefaultComboBoxModel();
		
		
		dropDown.addElement("");
		dropDown.addElement("Inventory");
		dropDown.addElement("Dealership Info");
		dropDown.addElement("Sales History");

		final JComboBox dropDownBox = new JComboBox(dropDown);    
		dropDownBox.setSelectedIndex(0);
		JScrollPane dropDownPane = new JScrollPane(dropDownBox);  
		dropDownPane.setBackground(Color.GRAY);
		dropDownPane.setForeground(Color.BLACK);
		dropDownBox.setBackground(Color.GRAY);
		dropDownBox.setForeground(Color.BLACK);
	
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth= 2;
		gbc.insets = new Insets(-90,0,0,-480);
		controlPanel.add(dropDownPane,gbc);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setIcon(new ImageIcon(inventoryPage.class.getResource("/images/backgroundd.jpg")));
		//lblNewLabel_1.setBounds(-402, -800, 19, 56);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth= 2;
		gbc.weighty = .05;
		gbc.insets = new Insets(-250,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		controlPanel.add(lblNewLabel_1,gbc);
		
		
		
		JPanel Hgrid = new JPanel();
		Hgrid.setBackground(Color.GRAY);
		Hgrid.setLayout(new GridBagLayout());
		GridBagLayout layoutH = new GridBagLayout();
		Hgrid.setLayout(layoutH);
		GridBagConstraints gbcH = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = .45;
		gbc.fill = GridBagConstraints.BOTH;
		controlPanel.add(Hgrid,gbc);
		
		JPanel Igrid = new JPanel();
		Igrid.setBackground(Color.WHITE);
		Igrid.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		Igrid.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipady = 5;
		gbc.gridwidth= 1;
		gbc.weightx =.75;
		gbc.weighty = .95;
		gbc.fill = GridBagConstraints.BOTH;
		controlPanel.add(Igrid,gbc);
		
		JPanel Fgrid = new JPanel();
		Fgrid.setBackground(new Color(230, 230, 230));
		Fgrid.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		Fgrid.setLayout(layoutF);
		GridBagConstraints gbcF = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.ipady = 5;
		gbc.weightx =.15;
		gbc.weighty = .95;
		gbc.fill = GridBagConstraints.BOTH;
		controlPanel.add(Fgrid,gbc);
		
		JTextField searchBar  = new JTextField("Search", 15);
		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.weightx =.75;
		//gbc.weighty = .1;
		gbcH.insets = new Insets(145,250,0,0);
		//gbcH.fill = GridBagConstraints.HORIZONTAL;
		Hgrid.add(searchBar, gbcH);
		
		JButton MagButton = new JButton("S");
		
		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.weightx =.5;
		//gbc.weighty = .1;
		gbcH.insets = new Insets(145,0,0,-450);
		//gbcH.fill = GridBagConstraints.HORIZONTAL;
		Hgrid.add(MagButton, gbcH);
		
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
		JButton showButton = new JButton("Show");
		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.insets = new Insets(135,-420,0,0);
		Hgrid.add(addVehiclePane, gbcH); 
		//Hgrid.add(showButton, gbcH); 
		JLabel editLabel = new JLabel("Edit Inventory: ");
		gbcH.gridx = 0;
		gbcH.gridy = 0;
		gbcH.insets = new Insets(135,-20,0,0);
		Hgrid.add(editLabel, gbcH); 
		
		
		JLabel price = new JLabel("Price");
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		Fgrid.add(price, gbcF);
		
		JLabel min = new JLabel("0");
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 2;
		//gbcF.weightx = .05;
		gbcF.ipady = 15;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		
		Fgrid.add(min, gbcF);
		
		JLabel max = new JLabel("5000");
		gbcF.gridwidth = 1;
		gbcF.gridx = 3;
		gbcF.gridy = 2;
		
		Fgrid.add(max, gbcF);
		
		JSlider priceSlider = new JSlider(0, 5000, 5000);
		gbcF.gridwidth = 3;
		gbcF.gridx = 0;
		gbcF.gridy = 1;
		gbcF.weightx = .25;
		gbcF.ipady = 0;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		Fgrid.add(priceSlider, gbcF);
		final DefaultListModel vegName = new DefaultListModel();

		vegName.addElement("Lady Finger                                                                                            ");
		vegName.addElement("Onion");
		vegName.addElement("Potato");
		vegName.addElement("Tomato");



		final JList vegList = new JList(vegName);
		vegList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		vegList.setSelectedIndex(0);
		vegList.setVisibleRowCount(19);
		JScrollPane vegListScrollPane = new JScrollPane(vegList);       
		JButton showButton2 = new JButton("Show");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcF.ipady = 0;
		//gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(30,0,0,0);
		Igrid.add(vegListScrollPane, gbcI);
		
		JLabel model = new JLabel("Model");
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 3;
		gbcF.ipady = 5;
		Fgrid.add(model, gbcF);
		final JCheckBox chkApple = new JCheckBox("Apple");
		final JCheckBox chkMango = new JCheckBox("Mango");
		final JCheckBox chkPeer = new JCheckBox("Peer");
		gbcF.gridx = 0;
		gbcF.gridy = 4;
		Fgrid.add(chkApple, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 5;
		Fgrid.add(chkMango, gbcF);
		gbcF.gridx = 0;
		gbcF.gridy = 6;
		gbcF.ipady = 15;
		Fgrid.add(chkPeer, gbcF);
		
		
		JLabel year = new JLabel("Year");
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 7;
		gbcF.ipady = 5;
		Fgrid.add(year, gbcF);
		
		SpinnerModel yearMinSpinnerModel = new SpinnerNumberModel(1900, //initial value
         1900, //min
         2025, //max
         1);//step
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearMinSpinnerModel, "#");
		JSpinner minSpinner = new JSpinner(yearMinSpinnerModel);
		gbcF.gridwidth = 1;
		gbcF.gridx = 0;
		gbcF.gridy = 8;
		gbcF.ipady = 5;
		Fgrid.add(minSpinner, gbcF);
		SpinnerModel yearMaxSpinnerModel = new SpinnerNumberModel(2025, //initial value
         1900, //min
         2025, //max
         1);//step
		JSpinner maxSpinner = new JSpinner(yearMaxSpinnerModel);
		gbcF.gridwidth = 1;
		gbcF.gridx = 2;
		gbcF.gridy = 8;
		gbcF.ipady = 5;
		Fgrid.add(maxSpinner, gbcF);
		
		JLabel dashyear = new JLabel("       - ");
		gbcF.gridwidth = 1;
		gbcF.gridx = 1;
		gbcF.gridy = 8;
		gbcF.ipadx = 5;
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		
		Fgrid.add(dashyear, gbcF);
		
		//JList makes = new JList(Object[] listData);
		
		    
		final DefaultListModel makesName = new DefaultListModel();
		final JList makesList = new JList(makesName);
		JScrollPane makesScrollPane = new JScrollPane(makesList);
		
		makesName.addElement("Honda");
		makesName.addElement("Toyata");
		makesName.addElement("Chrysler");
		makesName.addElement("BMW");
		
		
		makesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		makesList.setSelectedIndex(0);
		makesList.setVisibleRowCount(19);
		JScrollPane makesListScrollPane = new JScrollPane(makesList);       
		gbcF.gridx = 0;
		gbcF.gridy = 9;
		//gbcF.gridwidth = 3;
		gbcF.weightx = .9;
		gbcF.ipady = 0;
		//gbcI.weighty = .4;
		gbcF.fill = GridBagConstraints.BOTH;
		gbcF.insets = new Insets(30,0,0,0);
		//Fgrid.add(vegListScrollPane, gbcF);


		final JCheckBox dAll = new JCheckBox("Display All");
		final JCheckBox dMoto = new JCheckBox("Motocycles");
		final JCheckBox dCar = new JCheckBox("Car");
		gbcI.gridwidth = 0;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		gbcI.ipady = 15;
		gbcI.fill = GridBagConstraints.VERTICAL;
		gbcI.insets = new Insets(180, -300,0,0);
		//gbcI.weightx = .1;
		gbcI.weighty = 0;
		Igrid.add(dAll, gbcI);
		gbcI.gridwidth = 0;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		//gbcI.weightx = .2;
		gbcI.insets = new Insets(180,-100,0,0);
		Igrid.add(dMoto, gbcI);
		gbcI.gridwidth = 0;
		gbcI.gridx = 3;
		gbcI.gridy = 0;
		gbcI.ipady = 0;
		gbcI.insets = new Insets(180,-320,0,0);
		//gbcI.weightx = .33;
		Igrid.add(dCar, gbcI);
	
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
		gbcI.gridwidth = 0;
		gbcI.gridx = 4;
		gbcI.gridy = 0;
		gbcI.ipady = 0;
		gbcI.insets = new Insets(180,-100,0,0);
		//gbcI.weightx = .33;
		Igrid.add(sortMenuPane, gbcI);
	
		//controlPanel.add(vegListScrollPane,gbc);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
		

	}
}