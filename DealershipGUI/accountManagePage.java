
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

public class accountManagePage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public accountManagePage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      accountManagePage accountManagePage = new accountManagePage();
      //accountManagePage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Manage Users Accounts");
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
		carImage.setIcon(new ImageIcon(accountManagePage.class.getResource("/images/backgroundd.jpg")));
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
		filterBG.setBounds(550, 175, 90, 450);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);
		
		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(0, 150, 550, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);
		
		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 180, 540, 430);
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
		JButton editUser = new JButton("Edit");
		editUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		//gbcF.fill = GridBagConstraints.HORIZONTAL;
		//gbcF.weighty = .5;
		gbcF.insets = new Insets(8, 5, 0, 0);
		gbcF.weightx = .8;
		editUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(editUser, gbcF);
		
		JButton deleteUser = new JButton("Delete");
		deleteUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		deleteUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		//gbcF.weighty = .5;
		gbcF.gridx = 1;
		gbcF.gridy = 0;
		filterBG.add(deleteUser, gbcF);

		JButton editUser2 = new JButton("Edit");
		editUser2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		gbcF.insets = new Insets(8, 5, 0, 0);
		gbcF.weightx = .8;
		editUser2.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcF.gridx = 0;
		gbcF.gridy = 1;
		filterBG.add(editUser2, gbcF);
		
		JButton deleteUser2 = new JButton("Delete");
		deleteUser2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		deleteUser2.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcF.weighty = .5;
		gbcF.gridx = 1;
		gbcF.gridy = 1;
		filterBG.add(deleteUser2, gbcF);
		
		gbcD.insets = new Insets(5, 15,5, 60);
		//gbcD.fill = GridBagConstraints.HORIZONTAL;
		gbcD.anchor = GridBagConstraints.WEST;
		JLabel fNameL = new JLabel("Name ");
		//gbcD.weighty = .1;
		gbcD.gridx = 0;
		gbcD.gridy = 0;
		displayBG.add(fNameL, gbcD);
		
		JLabel lNameL = new JLabel("UserName ");
		gbcD.gridx = 1;
		gbcD.gridy = 0;
		displayBG.add(lNameL, gbcD);
		
		gbcD.insets = new Insets(5,-15,5, 55);
		JLabel jobTitleL = new JLabel("Job Title ");
		gbcD.gridx = 2;
		gbcD.gridy = 0;
		
		displayBG.add(jobTitleL, gbcD);
		
		gbcD.insets = new Insets(5,0,5, 75);
		JLabel emailL = new JLabel("Email ");
		gbcD.gridx = 3;
		gbcD.gridy = 0;

		displayBG.add(emailL, gbcD);
		
		gbcD.insets = new Insets(5,0,5, 5);
		gbcD.fill = GridBagConstraints.HORIZONTAL;
		JLabel passwordL = new JLabel("Password ");
		gbcD.gridx = 4;
		gbcD.gridy = 0;
		gbcD.weightx = .1;
		displayBG.add(passwordL, gbcD);
		
		
		gbcI.insets = new Insets(5, 5,5, 5);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.weightx = .8;
		JTextField fNameT = new JTextField("Generic Name", 15);
		fNameT.setEditable(false);		
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		inventoryBG.add(fNameT, gbcI);
		
		
		JTextField lNameT = new JTextField("Name",15);
		lNameT.setEditable(false);
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		inventoryBG.add(lNameT, gbcI);
		
		JTextField jobTitleT = new JTextField("Position", 15);
		jobTitleT.setEditable(false);
		gbcI.gridx = 2;
		gbcI.gridy = 0;
		inventoryBG.add(jobTitleT, gbcI);
		
		JTextField emailT = new JTextField("fakeemail@gmail.com", 30);
		emailT.setEditable(false);
		gbcI.gridx = 3;
		gbcI.gridy = 0;
		inventoryBG.add(emailT, gbcI);
		
		JPasswordField passwordBar  = new JPasswordField("Password",18);
		passwordBar.setEditable(false);
		passwordBar.setEchoChar('~');
		gbcI.gridx = 4;
		gbcI.gridy = 0;
		inventoryBG.add(passwordBar, gbcI);
		
		JTextField fNameT2 = new JTextField("Generic Name", 15);
		fNameT2.setEditable(false);
		//fNameT.setBorder(BorderFactory.createCompoundBorder(fNameT.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, -100)));
		gbcI.weighty = .9;
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		//gbcI.insets = new Insets(-380, 0, 5,5);
		inventoryBG.add(fNameT2, gbcI);
		
		JTextField lNameT2 = new JTextField("Name",15);
		lNameT2.setEditable(false);
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		inventoryBG.add(lNameT2, gbcI);
		
		JTextField jobTitleT2 = new JTextField("Position", 15);
		jobTitleT2.setEditable(false);
		gbcI.gridx = 2;
		gbcI.gridy = 1;
		inventoryBG.add(jobTitleT2, gbcI);
		
		JTextField emailT2 = new JTextField("fakeemail@gmail.com", 30);
		emailT2.setEditable(false);
		gbcI.gridx = 3;
		gbcI.gridy = 1;
		inventoryBG.add(emailT2, gbcI);
		
		JPasswordField passwordBar2  = new JPasswordField("Password",18);
		passwordBar2.setEditable(false);
		passwordBar2.setEchoChar('~');
		gbcI.gridx = 4;
		gbcI.gridy = 1;
		gbcI.weighty = .9;
		inventoryBG.add(passwordBar2, gbcI); 
		
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}
}