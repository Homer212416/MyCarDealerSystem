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

public class pastSalesPage{
	private JFrame salesMainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private pastSalesPageController controller;
	private static Image newCarHeaderImage;
	private static Image carHeaderImage;
	

	public pastSalesPage(pastSalesPageController controller, int width, int height){
		this.controller = controller;
		carHeaderImage = Toolkit.getDefaultToolkit().getImage(dealerShipInfoPage.class.getResource("/images/backgroundd.jpg"));
		newCarHeaderImage = carHeaderImage.getScaledInstance(300, 150,Image.SCALE_DEFAULT);
		
		prepareInventoryGUI(width, height);
	}
	public static void main(String[] args){
      //pastSalesPage pastSalesPage = new pastSalesPage();
      //pastSalesPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(int width, int height){
		salesMainFrame = new JFrame("Past Sales");
		salesMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		salesMainFrame.setBounds(0, 0, width, height);
		salesMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, width, height);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);	
		GridBagConstraints gbcC = new GridBagConstraints();
		
		JLabel carImage = new JLabel("");
		carImage.setBackground(Color.BLACK);
		carImage.setOpaque(true);
		carImage.setIcon(new ImageIcon(newCarHeaderImage));
		carImage.setBounds(0, -50, 650, 200);
		gbcC.anchor = GridBagConstraints.NORTH;  
		gbcC.fill = GridBagConstraints.BOTH;
		
		
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
		gbcC.anchor = GridBagConstraints.NORTH;  
		gbcC.gridx = 2;
		gbcC.gridwidth = 1;
		gbcC.gridy = 4;
		gbcC.weightx = .1;
		//controlPanel.add(filterBG,gbcC);
		
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
		//controlPanel.add(displayBG,gbcC);
		
		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 175, 440, 430);
		gbcC.insets = new Insets(5,0,0,0);
		gbcC.gridx = 0;
		gbcC.gridwidth = 1;
		gbcC.gridy = 4;
		gbcC.weightx = .6;
		gbcC.weighty= .6;
		controlPanel.add(inventoryBG,gbcC);
		
		//page menu in upper right hand corner
		JComboBox pageMenuDD = new JComboBox();
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
				Rectangle r = salesMainFrame.getBounds();
				int h = r.height;
				int w = r.width;
				controller.pageMenuSelect(pageMenuDD.getSelectedIndex(), salesMainFrame, w, h);
			}
		});
		gbcC.anchor = GridBagConstraints.NORTHEAST;
		gbcC.fill = GridBagConstraints.NONE; 
		gbcC.insets = new Insets(0,0,0,0);
		gbcC.gridx = 0;
		gbcC.gridwidth = 3;
		gbcC.gridy = 0;
		gbcC.weightx = 0;
		gbcC.weighty= 0;
		controlPanel.add(pageMenuDD,gbcC);
		
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		controlPanel.add(carImage,gbcC);
		//sales list
		
		final DefaultListModel salesModel = new DefaultListModel();

		String[] columnNames = {"Vehicle ID", "Buyer", "Contact", "Date"};

        // Parse the sale strings to split into 4 columns
        String[] sales = controller.getAllSales();
        String[][] tableData = new String[sales.length][4];

        for (int i = 0; i < sales.length; i++) {
            String sale = sales[i];
            String[] parts = sale.split(", ");
            for (int j = 0; j < parts.length; j++) {
                String[] keyValue = parts[j].split(": ");
                if (keyValue.length == 2) {
                    tableData[i][j] = keyValue[1];
                }
            }
        }

        JTable salesTable = new JTable(tableData, columnNames);
        salesTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(salesTable);
		inventoryBG.add(scrollPane);
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		gbcI.gridwidth = 3;
		gbcI.weightx = .9;
		gbcI.weighty = 1;
		gbcF.ipady = 0;
		//gbcI.weighty = .4;
		gbcI.fill = GridBagConstraints.BOTH;
		inventoryBG.add(scrollPane, gbcI);
		salesMainFrame.setContentPane(controlPanel);
		salesMainFrame.setVisible(true);
		
	
	}
}