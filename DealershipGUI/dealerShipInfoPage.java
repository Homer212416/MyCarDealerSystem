
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

public class dealerShipInfoPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public dealerShipInfoPage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      dealerShipInfoPage dealerShipInfoPage = new dealerShipInfoPage();
      //dealerShipInfoPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Dealership Information");
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
		carImage.setIcon(new ImageIcon(dealerShipInfoPage.class.getResource("/images/backgroundd.jpg")));
		carImage.setBounds(0, -50, 650, 200);
		controlPanel.add(carImage);
		
		JPanel searchBarBG = new JPanel();
		searchBarBG.setBackground(Color.GRAY);
		searchBarBG.setLayout(new GridBagLayout());
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();
		searchBarBG.setBounds(0, 150, 650, 50);
		//controlPanel.add(searchBarBG);
		
		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setBounds(450, 175, 200, 450);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);
		
		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(0, 150, 450, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);
		
		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 175, 440, 430);
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

		
		//add and delete dealer button
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JButton addDealer = new JButton("Add Dealership");
		addDealer.setEnabled(false);
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		gbcF.weighty = .1;
		gbcF.insets = new Insets(7, 0, 0, 0);
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(addDealer, gbcF);
		
		JButton deleteDealer = new JButton("Delete Dealership");
		deleteDealer.setEnabled(false);
		gbcF.weighty = .9;
		gbcF.gridx = 0;
		gbcF.gridy = 1;
		filterBG.add(deleteDealer, gbcF);
		
	
		//inventory list
		final DefaultListModel inventoryModel = new DefaultListModel();

		inventoryModel.addElement("Fake Dealership");
		inventoryModel.addElement("1000 Rainbow Road, Montreal, QC");
		inventoryModel.addElement("Capacity: 100");
		inventoryModel.addElement("Cars Sold: 10");
		inventoryModel.addElement("Cars Available: 10");
		inventoryModel.addElement("Motocycles Sold: 10");
		inventoryModel.addElement("Motocycles Available: 10");
		inventoryModel.addElement("Profit: 10.10");


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