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
import java.util.*;

public class accountManagePage{
	private JFrame accountMainFrame;
	private JFrame auaccountMainFrame;
	private JFrame euaccountMainFrame;
	private JFrame deleteUseraccountMainFrame;
	private JFrame AddUseraccountMainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JTextField fNameT;
	private JTextField jobTitleT;
	private JTextField emailT;
	private JTextField lNameT;
	private int verifiedEdit = 0;
	//private User[] allUsers;
	int nextEmployeeID = 0;
	int usersIndex = -1;
	private JToggleButton previous;
	private HashMap<JToggleButton,ArrayList<JTextField>> userTextBoxes;
	private Boolean adminConfirmed = false;
	private accountManagePageController controller;
	private JToggleButton current;
	private String deleteuserID;
	

	public accountManagePage(accountManagePageController controller){
		this.controller = controller;
		prepareInventoryGUI();
	}
	
		
	public static void main(String[] args){
      //accountManagePage accountManagePage = new accountManagePage();
      //accountManagePage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		accountMainFrame = new JFrame("Manage Users Accounts");
		accountMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		accountMainFrame.setBounds(0, 0, 650, 650);
		accountMainFrame.setLayout(new BorderLayout());
		accountMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});
		//create layout of panels DON'T CHANGE/////////////////////////////////////////////////////////
		controlPanel = new JPanel();
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
		controlPanel.add(searchBarBG);
		
		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(0, 200, 650, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);
		//////////////////////////////////////////////////////////////////////////////////////////
		
		
		//page menu in upper right hand corner
		JComboBox pageMenuDD = new JComboBox();
		controller.fillPageElements(pageMenuDD);
		DefaultListSelectionModel pageMenuModel = new DefaultListSelectionModel();
		EnabledJComboBoxRenderer pageMenuEnableRender = new EnabledJComboBoxRenderer(pageMenuModel);
		pageMenuDD.setRenderer(pageMenuEnableRender);
		pageMenuDD.setBounds(450, 20, 200, 25);
		controlPanel.add(pageMenuDD);
		
		pageMenuDD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pageMenuSelect(pageMenuDD.getSelectedIndex(), accountMainFrame);
			}
		});
		//add user Button/////////////////////////////////////////////////////////////////////////////////
		JButton addUser = new JButton("Add New User");
		addUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		addUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcS.insets = new Insets(0, 10, 0, 0);
		gbcS.anchor = GridBagConstraints.WEST;
		gbcS.weightx = .5;
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		searchBarBG.add(addUser, gbcS);
		
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					controller.adminUser("add");
			}
		});
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//header label for user info///////////////////////////////////////////////////////////////
		gbcD.insets = new Insets(5, 15,5, 85);
		gbcD.anchor = GridBagConstraints.WEST;
		JLabel fNameL = new JLabel("Name ");
		gbcD.gridx = 0;
		gbcD.gridy = 0;
		displayBG.add(fNameL, gbcD);
		
		JLabel lNameL = new JLabel("UserName ");
		gbcD.gridx = 1;
		gbcD.gridy = 0;
		displayBG.add(lNameL, gbcD);
		
		gbcD.insets = new Insets(5,-10,5, 55);
		JLabel jobTitleL = new JLabel("Job Title ");
		gbcD.gridx = 2;
		gbcD.gridy = 0;
		
		displayBG.add(jobTitleL, gbcD);
		
		gbcD.insets = new Insets(5,25,5,0);
		JLabel emailL = new JLabel("Email ");
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.weightx = .1;
		displayBG.add(emailL, gbcD);
		
		gbcD.insets = new Insets(5,0,5, 5);
		gbcD.fill = GridBagConstraints.HORIZONTAL;
		JLabel passwordL = new JLabel("Password ");
		gbcD.gridx = 4;
		gbcD.gridy = 0;
		////////////////////////////////////////////////////////////////////////////////////////////
		
		populateUsers();//need to refresh users if one is delete so it's in its own method
		accountMainFrame.setContentPane(controlPanel);
		accountMainFrame.setVisible(true);
	
	
	}
	
	public void populateUsers(){
		//create layout of panels DON'T CHANGE/////////////////////////////////////////////////////////
		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 230, 540, 430);
		controlPanel.add(inventoryBG);
		
		JPanel filterBG = new JPanel();
		filterBG.setBackground(new Color(230, 230, 230));
		filterBG.setLayout(new GridBagLayout());
		GridBagLayout layoutF = new GridBagLayout();
		filterBG.setLayout(layoutF);
		filterBG.setBounds(550, 225, 90, 435);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//populate user information//////////////////////////////////////////////////////////////
		int userGridY = 0;
		String[][] users = controller.getAllUsers();
		int totalUsers = users.length;
		int u = 0;
		System.out.println("users: " + totalUsers);
		userTextBoxes = new HashMap<JToggleButton,ArrayList<JTextField>>();

		//edit button pressed
		ActionListener editListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				current = ((JToggleButton) e.getSource());
				ArrayList<JTextField> textBoxes = userTextBoxes.get(current);
				if(previous == null || current != previous){
					controller.adminUser("edit");

				}if(current == previous){//if they are done editing
					for(JTextField textBox : textBoxes){
						//System.out.println(textBox);
						String userID = String.valueOf(current.getName().charAt(0));
						textBox.setEditable(false);	
						String firstName = fNameT.getText();
						String lastName = lNameT.getText();
						String jobTitle = jobTitleT.getText();
						String email =  emailT.getText();
						String[] editedUserInfo = {userID, firstName, lastName, jobTitle, email};
						controller.editedUser(editedUserInfo);
						
					}
					previous = null;	
				}else{previous = ((JToggleButton) e.getSource());}				
			}
		};
		
		ActionListener deleteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				current = ((JToggleButton) e.getSource());
				controller.adminUser("delete");
				ArrayList<JTextField> textBoxes = userTextBoxes.get(current);
				deleteuserID = String.valueOf(current.getName().charAt(0));
				

				
			}
		};
		
		//create text box for user information////////////////////////////////////////////////////////////////
		for(String[] user : users){
			
			int userGridX = 0;
			int editGridX = 0;
			gbcI.insets = new Insets(5, 5,5, 5);
			gbcI.fill = GridBagConstraints.HORIZONTAL;
			gbcI.anchor = GridBagConstraints.NORTHWEST;
			gbcI.weightx = .8;
			
			
			fNameT = new JTextField(user[0], 15);
			fNameT.setName(user[4] +"_first");
			fNameT.setEditable(false);		
			gbcI.gridx = userGridX++;
			gbcI.gridy = userGridY;
			inventoryBG.add(fNameT, gbcI);
		
			lNameT = new JTextField(user[1],15);
			lNameT.setEditable(false);
			lNameT.setName(user[4] +"_last");
			gbcI.gridx = userGridX++;
			gbcI.gridy = userGridY;
			inventoryBG.add(lNameT, gbcI);
			
			jobTitleT = new JTextField(user[2], 15);
			jobTitleT.setName(user[4] +"_job");
			jobTitleT.setEditable(false);
			gbcI.gridx = userGridX++;
			gbcI.gridy = userGridY;
			inventoryBG.add(jobTitleT, gbcI);
			
			emailT = new JTextField(user[3], 30);
			emailT.setName(user[4] +"_email");
			emailT.setEditable(false);
			gbcI.gridx = userGridX++;
			gbcI.gridy = userGridY++;
			
			//editDelete for each user
			gbcF.anchor = GridBagConstraints.NORTHWEST;
			JToggleButton editUser = new JToggleButton("Edit");
			editUser.setName(user[4] + "_edit");
			editUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
			editUser.addActionListener(editListener);
			//gbcF.fill = GridBagConstraints.HORIZONTAL;
			//gbcF.weighty = .5;
			gbcF.insets = new Insets(8, 5, 0, 0);
			gbcF.weightx = .8;
			editUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
			gbcF.gridx = editGridX++;
			gbcF.gridy = userGridY;
			filterBG.add(editUser, gbcF);
			
			JToggleButton deleteUser = new JToggleButton("Delete");
			deleteUser.setName(user[4] + "_Delete");
			deleteUser.addActionListener(deleteListener);
			deleteUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
			deleteUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
			//gbcF.weighty = .5;
			gbcF.gridx = editGridX++;
			gbcF.gridy = userGridY++;
			
			u++;
			if(u >= totalUsers){
				gbcI.weightx = .9;
				gbcI.weighty = .2;
				gbcF.weightx = .9;
				gbcF.weighty = .2;
			}
			inventoryBG.add(emailT, gbcI);
			filterBG.add(deleteUser, gbcF);
			ArrayList<JTextField> texts = new ArrayList<JTextField>(){
				{
				add(fNameT);
				add(lNameT);
				add(jobTitleT);
				add(emailT);
				}
			};
			
			this.userTextBoxes.put(editUser, texts);

		}
		
		///////////////////////////////////////////////////////////////////////////////////////
			
	}
	
	public void editAdminConfirmed(	){//if password is correct 		
		ArrayList<JTextField> textBoxes = userTextBoxes.get(current);
		for(JTextField textBox : textBoxes){
			textBox.setEditable(true);
		} 	
	}
	
	public void deleteAdminConfirmed(){	//if password is correct 
		int id = Integer.valueOf(deleteuserID);
		controller.removeUser(id);
		deleteuserID = null;		
	}
	
	
	
	public void addUserLoginPage(){
		//create layout of panels DON'T CHANGE/////////////////////////////////////////////////////////
		AddUseraccountMainFrame = new JFrame("Create New User?");
		AddUseraccountMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		AddUseraccountMainFrame.setBounds(0, 0, 400, 200);
		AddUseraccountMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            AddUseraccountMainFrame.dispose();
			}        
		});
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 400, 200);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 400, 50);
		controlPanel.add(announcementBG);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 390, 130);
		GridBagConstraints gbcI = new GridBagConstraints();
		controlPanel.add(infoBG);
		
		JTextArea announcement = new JTextArea(1, 30);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Confirm password to confirm you wish to create a new user");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.anchor = GridBagConstraints.CENTER;
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.insets = new Insets(0, 10, 0, 10);
		announcementBG.add(announcement, gbcA);
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//request password
		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);
		
		JPasswordField confirmPasswordBar  = new JPasswordField("Password",18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;
		
		infoBG.add(confirmPasswordBar, gbcI);
		
		//submit button
		JButton submitB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(submitB, gbcI);
		AddUseraccountMainFrame.setContentPane(controlPanel);
		AddUseraccountMainFrame.setVisible(true);
		
		submitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] adminPasswordChar =  confirmPasswordBar.getPassword();
				String adminPassword = new String(adminPasswordChar);
				controller.isAdmin(adminPassword, AddUseraccountMainFrame, "add");
				
			}
		});
		
	}
	
	
	public void addUserPage(){
		auaccountMainFrame = new JFrame("Create New User");
		auaccountMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		auaccountMainFrame.setBounds(0, 0, 315, 335);
		auaccountMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            auaccountMainFrame.dispose();
			}        
		});
		//create layout of panels DON'T CHANGE/////////////////////////////////////////////////////////
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 315, 335);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 300, 50);
		controlPanel.add(announcementBG);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 290, 250);
		GridBagConstraints gbcI = new GridBagConstraints();
		controlPanel.add(infoBG);
		
		JTextArea announcement = new JTextArea(2, 20);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Please fill out all the information below to create a new user");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.weighty = 1;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		announcementBG.add(announcement, gbcA);
		///////////////////////////////////////////////////////////////////////////////////////
		
		//request first and last name, job title, email 
		JLabel fNameL = new JLabel("First Name: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weighty = .5;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(fNameL, gbcI);
		
		JLabel lNameL = new JLabel("Last Name: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(lNameL, gbcI);
		
		JLabel jobTitleL = new JLabel("Job Title: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 2;
		infoBG.add(jobTitleL, gbcI);
		
		JLabel emailL = new JLabel("Email: ");
		gbcI.gridx = 0;
		gbcI.gridy = 3;
		infoBG.add(emailL, gbcI);
		gbcI.anchor = GridBagConstraints.WEST;
		
		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 4;
		infoBG.add(passwordL, gbcI);
		gbcI.anchor = GridBagConstraints.WEST;
		
		JTextField fNameT = new JTextField(15);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.weightx = .99;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(fNameT, gbcI);
		
		JTextField lNameT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		infoBG.add(lNameT, gbcI);
		
		JTextField jobTitleT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 2;
		infoBG.add(jobTitleT, gbcI);
		
		JTextField emailT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 3;
		infoBG.add(emailT, gbcI);
		
		
		JPasswordField passwordT = new JPasswordField(18);
		//passwordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 4;
		infoBG.add(passwordT, gbcI);
		
		//submit button
		JButton generateB = new JButton("GeneratePassword");
		generateB.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		infoBG.add(generateB, gbcI);
		
		JButton submitB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 5;
		infoBG.add(submitB, gbcI);
		auaccountMainFrame.setContentPane(controlPanel);
		auaccountMainFrame.setVisible(true);
		
		
		generateB.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String genPassword = controller.generatePassword();
				passwordT.setText(genPassword);
			}
		});
		
		submitB.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String firstName = fNameT.getText();
				String lastName = lNameT.getText();
				String jobTitle = jobTitleT.getText();
				char[] passwordChar =  passwordT.getPassword();
				String password = new String(passwordChar);
				String[] newUserInfo = {firstName, lastName, jobTitle, password};
				controller.newUserSubmit(newUserInfo, auaccountMainFrame);
			}
		});
	}
	
	public void editUserPage(){
		euaccountMainFrame = new JFrame("Edit User Info");
		euaccountMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		euaccountMainFrame.setBounds(0, 0, 400, 200);
		euaccountMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            euaccountMainFrame.dispose();
			}        
		});
		//Panel layout DON'T CHANGE///////////////////////////////////////////////////////////////////////////////////////
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 400, 200);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 400, 50);
		controlPanel.add(announcementBG);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 390, 130);
		GridBagConstraints gbcI = new GridBagConstraints();
		controlPanel.add(infoBG);
		
		JTextArea announcement = new JTextArea(1, 30);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Please verify your password to confirm you wish to edit another user's information");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.anchor = GridBagConstraints.CENTER;
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.insets = new Insets(0, 10, 0, 10);
		announcementBG.add(announcement, gbcA);
		///////////////////////////////////////////////////////////////////////////////////////
		//request username and password
		
		gbcI.insets = new Insets(5, 5,5, 5);
		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);

		JPasswordField confirmPasswordBar  = new JPasswordField("Password",18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;
		
		infoBG.add(confirmPasswordBar, gbcI);
		
		//submit button
		JButton submitB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(submitB, gbcI);
		euaccountMainFrame.setContentPane(controlPanel);
		euaccountMainFrame.setVisible(true);
		
		submitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] adminPasswordChar =  confirmPasswordBar.getPassword();
				String adminPassword = new String(adminPasswordChar);
				controller.isAdmin(adminPassword, euaccountMainFrame, "edit");
				
			}
		});
		
	}
	
	public void deleteUserLoginPage(){
		deleteUseraccountMainFrame = new JFrame("Delete User");
		deleteUseraccountMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		deleteUseraccountMainFrame.setBounds(0, 0, 400, 200);
		deleteUseraccountMainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            deleteUseraccountMainFrame.dispose();
			}        
		});
		//Panel Layout DON'T CHANGE/////////////////////////////////////////////////////////////////////////////////////
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 400, 200);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);	
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 400, 50);
		controlPanel.add(announcementBG);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 390, 130);
		GridBagConstraints gbcI = new GridBagConstraints();
		controlPanel.add(infoBG);
		
		JTextArea announcement = new JTextArea(1,30);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Enter your username and password to confirm you wish to delete selected user. This action is not undoable.");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.anchor = GridBagConstraints.CENTER;
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.insets = new Insets(0, 10, 0, 10);
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		announcementBG.add(announcement, gbcA);
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//request username and password
		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);
		
		JPasswordField confirmPasswordBar  = new JPasswordField("Password",18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;
		infoBG.add(confirmPasswordBar, gbcI);
		
		//submit button
		JButton submitB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(submitB, gbcI);
		deleteUseraccountMainFrame.setContentPane(controlPanel);
		deleteUseraccountMainFrame.setVisible(true);
		
		submitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] adminPasswordChar =  confirmPasswordBar.getPassword();
				String adminPassword = new String(adminPasswordChar);
				controller.isAdmin(adminPassword, deleteUseraccountMainFrame, "delete");
				
			}
		});
		
		
		
	}
}