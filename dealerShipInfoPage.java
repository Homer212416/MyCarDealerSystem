
package carDealership;

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

public class dealerShipInfoPage {
	private JFrame dealerMainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private Image carImage;
	private Image carImageHeader;
	public JComboBox pageMenuDD;
	private dealerShipInfoPageController controller;
	private static Image newCarHeaderImage;
	private GridBagConstraints gbcC;

	public dealerShipInfoPage(dealerShipInfoPageController controller) {
		this.controller = controller;
		carImage = Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg"));
		carImageHeader = Toolkit.getDefaultToolkit()
				.getImage(dealerShipInfoPage.class.getResource("/images/backgroundd.jpg"));
		newCarHeaderImage = carImageHeader.getScaledInstance(300, 150, Image.SCALE_DEFAULT);

		prepareInventoryGUI();
	}

	public static void main(String[] args) {
		// dealerShipInfoPage dealerShipInfoPage = new dealerShipInfoPage();
		// dealerShipInfoPage.showEventDemo();
	}

	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI() {
		dealerMainFrame = new JFrame("Dealership Information");
		dealerMainFrame.setIconImage(carImage);
		dealerMainFrame.setBounds(0, 0, 650, 650);
		dealerMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// setup panel layout DONT CHANGE///////////////////////////////////////////////
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 650, 650);
		controlPanel.setLayout(new GridLayout());

		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);
		gbcC = new GridBagConstraints();

		JLabel carImage = new JLabel("");
		carImage.setBackground(Color.BLACK);
		carImage.setOpaque(true);
		carImage.setIcon(new ImageIcon(newCarHeaderImage));
		carImage.setBounds(0, -50, 650, 200);

		JPanel searchBarBG = new JPanel();
		searchBarBG.setBackground(Color.GRAY);
		searchBarBG.setLayout(new GridBagLayout());
		GridBagLayout layoutS = new GridBagLayout();
		searchBarBG.setLayout(layoutS);
		GridBagConstraints gbcS = new GridBagConstraints();

		searchBarBG.setBounds(0, 150, 650, 50);
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridwidth = 2;
		gbcC.gridy = 1;
		controlPanel.add(searchBarBG, gbcC);

		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setBounds(450, 175, 200, 450);
		GridBagConstraints gbcF = new GridBagConstraints();
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.gridx = 2;
		gbcC.gridwidth = 1;
		gbcC.gridy = 4;
		controlPanel.add(filterBG, gbcC);

		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(0, 150, 450, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		gbcC.gridx = 1;
		gbcC.gridwidth = 2;
		gbcC.gridy = 3;
		controlPanel.add(displayBG, gbcC);

		JPanel dealershipBG = new JPanel();
		dealershipBG.setBackground(Color.WHITE);
		dealershipBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		dealershipBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		dealershipBG.setBounds(10, 175, 440, 430);
		gbcC.insets = new Insets(5, 0, 0, 0);
		gbcC.gridx = 1;
		gbcC.gridwidth = 1;
		gbcC.gridy = 4;
		gbcC.weightx = .6;
		gbcC.weighty = .6;
		controlPanel.add(dealershipBG, gbcC);
		///////////////////////////////////////////////////////////////////////////////////////////////

		// page menu in upper right hand
		// corner//////////////////////////////////////////////////////////
		pageMenuDD = new JComboBox();
		controller.fillPageElements(pageMenuDD);
		DefaultListSelectionModel pageMenuModel = new DefaultListSelectionModel();
		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);
		pageMenuDD.setRenderer(pageMenuEnableRender);
		pageMenuDD.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDD);
		controller.setDisabledPages(pageMenuModel);
		pageMenuDD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pageMenuSelect(pageMenuDD.getSelectedIndex(), dealerMainFrame);
			}
		});

		gbcC.anchor = GridBagConstraints.NORTHEAST;
		gbcC.fill = GridBagConstraints.NONE;
		gbcC.insets = new Insets(0, 0, 0, 0);
		gbcC.gridx = 0;
		gbcC.gridwidth = 3;
		gbcC.gridy = 0;
		gbcC.weightx = 0;
		gbcC.weighty = 0;
		controlPanel.add(pageMenuDD, gbcC);

		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		controlPanel.add(carImage, gbcC);

		// delete
		// button///////////////////////////////////////////////////////////////////////////////
		JButton deleteDealer = new JButton("Delete Dealership");
		// enable delete button based on user permissions
		Boolean enable = controller.setDeleteEnabled();
		if (!enable) {
			deleteDealer.setEnabled(false);
		}
		gbcF.insets = new Insets(5, 0, 0, 0);
		gbcF.anchor = GridBagConstraints.NORTH;
		gbcF.weighty = .9;
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(deleteDealer, gbcF);

		deleteDealer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// confirm delete dealership
				int response = JOptionPane.showConfirmDialog(dealerMainFrame,
						"Are you sure you want to delete the dealership?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					controller.deleteDealer();
					dealerMainFrame.dispose();
					controller.goToFirstLaunchPage();
				}

			}
		});

		// all dealershipInfo
		String[] dealer = controller.getDealershipInfo();

		// DealershipInformationList list///////////////////////////////////////////
		final DefaultListModel inventoryModel = new DefaultListModel();

		inventoryModel.addElement(dealer[0]);
		inventoryModel.addElement(dealer[1]);
		inventoryModel.addElement(dealer[2]);
		inventoryModel.addElement(dealer[3]);
		inventoryModel.addElement(dealer[4]);
		inventoryModel.addElement(dealer[5]);
		inventoryModel.addElement(dealer[6]);
		inventoryModel.addElement(dealer[7]);
		final JList inventoryList = new JList(inventoryModel);
		inventoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		inventoryList.setSelectedIndex(0);
		inventoryList.setVisibleRowCount(19);
		JScrollPane inventoryListScrollPane = new JScrollPane(inventoryList);
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcF.ipady = 0;
		// gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		gbcI.insets = new Insets(-5, 0, 0, 0);
		dealershipBG.add(inventoryListScrollPane, gbcI);
		dealerMainFrame.setContentPane(controlPanel);
		dealerMainFrame.setVisible(true);
	}
}