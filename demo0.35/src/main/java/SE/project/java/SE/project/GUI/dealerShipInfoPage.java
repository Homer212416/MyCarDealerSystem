package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import SE.project.controller.DealershipInfoPageController;

public class dealerShipInfoPage {
	private JFrame mainFrame;
	private DealershipInfoPageController dealershipInfoPageController;

	public dealerShipInfoPage() {
		//this.dealershipInfoPageController = new DealershipInfoPageController();
		prepareInventoryGUI();
	}

	private void prepareInventoryGUI() {
		mainFrame = new JFrame("Dealership Information");
		mainFrame.setBounds(0, 0, 650, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setLayout(null);

		JLabel carImage = new JLabel(new ImageIcon(dealerShipInfoPage.class.getResource("/images/backgroundd.jpg")));
		carImage.setBounds(0, -50, 650, 200);
		controlPanel.add(carImage);

		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		filterBG.setBounds(450, 175, 200, 450);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);

		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		displayBG.setBounds(0, 150, 450, 30);
		new GridBagConstraints();
		controlPanel.add(displayBG);

		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 175, 440, 430);
		controlPanel.add(inventoryBG);

		// page menu
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
				} else if (pageMenuDDB.getSelectedIndex() == 3) {
					new pastSalesPage();
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

		// interaction with controller

		// delete dealership button
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JButton deleteDealershipButton = new JButton("Delete Dealership");
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		gbcF.weighty = .1;
		gbcF.insets = new Insets(7, 0, 0, 0);
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(deleteDealershipButton, gbcF);

		// action listener for delete dealership button
		deleteDealershipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the dealership?",
						"Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dealershipInfoPageController.deleteDealership();
					JOptionPane.showMessageDialog(null, "Dealership deleted successfully.");
					mainFrame.dispose();
					new FirstLaunchPage();
				}
			}
		});

		// display dealership info
		final DefaultListModel<String> inventoryModel = new DefaultListModel<>();
		String[] info = dealershipInfoPageController.getDealershipInfo();
		inventoryModel.addElement("Name: " + info[0]);
		inventoryModel.addElement("Location: " + info[1]);
		inventoryModel.addElement("Inventory Capacity: " + info[2]);

		final JList<String> inventoryList = new JList<>(inventoryModel);
		inventoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		inventoryList.setSelectedIndex(0);
		inventoryList.setVisibleRowCount(19);
		JScrollPane inventoryListScrollPane = new JScrollPane(inventoryList);
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5, 0, 0, 0);
		inventoryBG.add(inventoryListScrollPane, gbcI);

		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}

	// test

	public static void main(String[] args) {
		new dealerShipInfoPage();
	}
}
