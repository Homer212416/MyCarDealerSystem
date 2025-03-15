
package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import SE.project.carDealership.Dealership;

import javax.swing.border.EmptyBorder;

import java.sql.SQLException;

public class dealerShipInfoPage {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public dealerShipInfoPage() {
		prepareInventoryGUI();
	}

	public static void main(String[] args) {
		new dealerShipInfoPage();
		// dealerShipInfoPage.showEventDemo();
	}

	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI() {
		mainFrame = new JFrame("Dealership Information");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 650, 650);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
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
		// controlPanel.add(searchBarBG);

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

		// page menu in upper right hand corner
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

		pageMenuDDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageMenuDDB.getSelectedIndex() == 1) {
					inventoryPage inventory = new inventoryPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 3) {
					pastSalesPage sales = new pastSalesPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 4) {
					accountManagePage accounts = new accountManagePage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 5) {
					LoginPage login = new LoginPage();
					mainFrame.dispose();
				}
			}
		});

		// only keep delete button
		// delete dealer button
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JButton deleteDealershipButton = new JButton("Delete Dealership");
		// addDealer.setEnabled(false);
		gbcF.fill = GridBagConstraints.HORIZONTAL;
		gbcF.weighty = .1;
		gbcF.insets = new Insets(7, 0, 0, 0);
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(deleteDealershipButton, gbcF);

		// Add action listener for the delete dealership button
		deleteDealershipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add a confirmation dialog
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the dealership?",
						"Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				// If yes, delete the dealership
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						Dealership dealership = new Dealership();
						dealership.delete();
						JOptionPane.showMessageDialog(null, "Dealership deleted successfully.");
						mainFrame.dispose();
						new FirstLaunchPage();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Error deleting dealership: " + ex.getMessage());
					}
				}
			}
		});

		// System.out.println(Main.m_dealership.getInfoGUI());
		// inventory list
		final DefaultListModel inventoryModel = new DefaultListModel();
		var dealership = new Dealership();
		String[] info = dealership.getInfoGUI();
		inventoryModel.addElement("Name: " + info[0]);
		inventoryModel.addElement("Location: " + info[1]);
		inventoryModel.addElement("Inventory Capacity: " + info[2]);

		/*
		 * String[] dealer = Main.m_dealership.getInfoGUI();
		 * inventoryModel.addElement("Name: " + dealer[0]);
		 * inventoryModel.addElement("Location: " + dealer[1]);
		 * inventoryModel.addElement("Inventory Capacity: " + dealer[2]);
		 * inventoryModel.addElement("Available Space: " + dealer[3]);
		 * inventoryModel.addElement("Total Cars: " + dealer[4]);
		 * inventoryModel.addElement("Total Motorcycles: " + dealer[5]);
		 * inventoryModel.addElement("Total Sales Profit: " + dealer[6]);
		 * inventoryModel.addElement("Total Vehicles Sold: " + dealer[7]);
		 * 
		 */
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
		// gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(5, 0, 0, 0);
		inventoryBG.add(inventoryListScrollPane, gbcI);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}
}
