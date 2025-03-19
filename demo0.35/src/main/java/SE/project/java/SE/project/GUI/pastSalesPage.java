package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import SE.project.controller.PastSalesPageController;
import SE.project.carDealership.Sale;

import java.util.ArrayList;

public class pastSalesPage {
	private JFrame mainFrame;
	private PastSalesPageController controller;

	public pastSalesPage() {
		this.controller = new PastSalesPageController();
		prepareInventoryGUI();
	}

	public static void main(String[] args) {
		new pastSalesPage();
	}

	private void prepareInventoryGUI() {
		mainFrame = new JFrame("Past Sales");
		mainFrame.setBounds(0, 0, 650, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setLayout(null);

		JLabel carImage = new JLabel(new ImageIcon(pastSalesPage.class.getResource("/images/backgroundd.jpg")));
		carImage.setBounds(0, -50, 650, 200);
		controlPanel.add(carImage);

		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		filterBG.setBounds(450, 175, 200, 450);
		controlPanel.add(filterBG);

		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		displayBG.setBounds(0, 150, 450, 30);
		controlPanel.add(displayBG);

		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 175, 440, 430);
		controlPanel.add(inventoryBG);

		// Page menu
		final DefaultComboBoxModel<String> pageMenuDD = new DefaultComboBoxModel<>();
		pageMenuDD.addElement("");
		pageMenuDD.addElement("Inventory");
		pageMenuDD.addElement("Dealership Info");
		pageMenuDD.addElement("Sales History");
		pageMenuDD.addElement("Manage User Accounts");
		pageMenuDD.addElement("Sign Out");

		final JComboBox<String> pageMenuDDB = new JComboBox<>(pageMenuDD);
		pageMenuDDB.setSelectedIndex(0);
		JScrollPane pageMenuDDP = new JScrollPane(pageMenuDDB);
		pageMenuDDP.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDDP);

		pageMenuDDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageMenuDDB.getSelectedIndex() == 1) {
					//new inventoryPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 2) {
					new dealerShipInfoPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 4) {
					new accountManagePage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 5) {
					new LoginPage();
					mainFrame.dispose();
				}
			}
		});

		// Display past sales
		final DefaultListModel<String> salesModel = new DefaultListModel<>();
		ArrayList<Sale> sales = controller.getAllSales();
		for (Sale sale : sales) {
			salesModel.addElement(sale.toString());
		}

		final JList<String> salesList = new JList<>(salesModel);
		salesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		salesList.setSelectedIndex(0);
		salesList.setVisibleRowCount(19);
		JScrollPane salesListScrollPane = new JScrollPane(salesList);
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5, 0, 0, 0);
		inventoryBG.add(salesListScrollPane, gbcI);

		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}
}
