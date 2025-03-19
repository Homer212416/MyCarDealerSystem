package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import SE.project.controller.DealershipInfoPageController;

public class accountManagePage {
	private JFrame mainFrame;
	private JFrame auMainFrame;
	private JFrame euMainFrame;
	private JFrame deleteUserMainFrame;
	private JFrame AddUserMainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JTextField fNameT;
	private JTextField jobTitleT;
	private JTextField emailT;
	private JTextField lNameT;
	private int verifiedEdit = 0;
	// private User[] allUsers;
	int nextEmployeeID = 0;
	int usersIndex = -1;
	private DealershipInfoPageController controller;

	public accountManagePage() {
		//this.controller = new DealershipInfoPageController();
		prepareInventoryGUI();
	}

	public static void main(String[] args) {
		accountManagePage accountManagePage = new accountManagePage();
		// accountManagePage.showEventDemo();
	}

	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI() {
		mainFrame = new JFrame("Manage Users Accounts");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 650, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 650, 650);
		controlPanel.setLayout(new GridLayout());

		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(null);

		JLabel carImage = new JLabel(new ImageIcon(accountManagePage.class.getResource("/images/backgroundd.jpg")));
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
		filterBG.setBounds(550, 225, 90, 435);
		GridBagConstraints gbcF = new GridBagConstraints();
		controlPanel.add(filterBG);

		JPanel displayBG = new JPanel();
		displayBG.setBackground(new Color(230, 230, 230));
		displayBG.setLayout(new GridBagLayout());
		GridBagLayout layoutD = new GridBagLayout();
		displayBG.setLayout(layoutD);
		displayBG.setBounds(0, 200, 650, 30);
		GridBagConstraints gbcD = new GridBagConstraints();
		controlPanel.add(displayBG);

		JPanel inventoryBG = new JPanel();
		inventoryBG.setBackground(Color.WHITE);
		inventoryBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		inventoryBG.setLayout(layoutI);
		GridBagConstraints gbcI = new GridBagConstraints();
		inventoryBG.setBounds(10, 230, 540, 430);
		controlPanel.add(inventoryBG);

		// page menu in upper right hand corner
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
		// add page functions
		pageMenuDDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (pageMenuDDB.getSelectedIndex() == 1) {
					//new inventoryPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 2) {
					new dealerShipInfoPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 3) {
					new pastSalesPage();
					mainFrame.dispose();
				} else if (pageMenuDDB.getSelectedIndex() == 5) {
					new LoginPage();
					mainFrame.dispose();
				}

			}
		});
		// add user
		JButton addUser = new JButton("Add New User");
		addUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		addUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcS.insets = new Insets(0, 10, 0, 0);
		gbcS.anchor = GridBagConstraints.WEST;
		gbcS.weightx = .5;
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		searchBarBG.add(addUser, gbcS);

		// addUser.setMnemonic(KeyEvent.VK_D);
		// addUser.setActionCommand("enable");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserLoginPage();
			}
		});

		// add and delete dealer button
		gbcF.anchor = GridBagConstraints.NORTHWEST;
		JButton editUser = new JButton("Edit");
		editUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		// gbcF.fill = GridBagConstraints.HORIZONTAL;
		// gbcF.weighty = .5;
		gbcF.insets = new Insets(8, 5, 0, 0);
		gbcF.weightx = .8;
		editUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		gbcF.gridx = 0;
		gbcF.gridy = 0;
		filterBG.add(editUser, gbcF);

		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editUserPage();
			}
		});

		JButton deleteUser = new JButton("Delete");
		deleteUser.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		deleteUser.setMargin(new java.awt.Insets(1, 1, 1, 1));
		// gbcF.weighty = .5;
		gbcF.gridx = 1;
		gbcF.gridy = 0;
		filterBG.add(deleteUser, gbcF);

		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserLoginPage();
			}
		});
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

		gbcD.insets = new Insets(5, 15, 5, 85);
		gbcD.anchor = GridBagConstraints.WEST;
		JLabel fNameL = new JLabel("Name ");
		gbcD.gridx = 0;
		gbcD.gridy = 0;
		displayBG.add(fNameL, gbcD);

		JLabel lNameL = new JLabel("UserName ");
		gbcD.gridx = 1;
		gbcD.gridy = 0;
		displayBG.add(lNameL, gbcD);

		gbcD.insets = new Insets(5, -10, 5, 55);
		JLabel jobTitleL = new JLabel("Job Title ");
		gbcD.gridx = 2;
		gbcD.gridy = 0;

		displayBG.add(jobTitleL, gbcD);

		gbcD.insets = new Insets(5, 25, 5, 0);
		JLabel emailL = new JLabel("Email ");
		gbcD.gridx = 3;
		gbcD.gridy = 0;
		gbcD.weightx = .1;
		displayBG.add(emailL, gbcD);

		gbcD.insets = new Insets(5, 0, 5, 5);
		gbcD.fill = GridBagConstraints.HORIZONTAL;
		JLabel passwordL = new JLabel("Password ");
		gbcD.gridx = 4;
		gbcD.gridy = 0;

		gbcI.insets = new Insets(5, 5, 5, 5);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.weightx = .8;
		fNameT = new JTextField("Generic Name", 15);
		fNameT.setEditable(false);
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		inventoryBG.add(fNameT, gbcI);

		lNameT = new JTextField("Name", 15);
		lNameT.setEditable(false);
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		inventoryBG.add(lNameT, gbcI);

		jobTitleT = new JTextField("Position", 15);
		jobTitleT.setEditable(false);
		gbcI.gridx = 2;
		gbcI.gridy = 0;
		inventoryBG.add(jobTitleT, gbcI);

		emailT = new JTextField("fakeemail@gmail.com", 30);
		emailT.setEditable(false);
		gbcI.gridx = 3;
		gbcI.gridy = 0;
		inventoryBG.add(emailT, gbcI);

		JTextField fNameT2 = new JTextField("Generic Name", 15);
		gbcI.weighty = .9;
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		inventoryBG.add(fNameT2, gbcI);

		JTextField lNameT2 = new JTextField("Name", 15);
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		inventoryBG.add(lNameT2, gbcI);

		JTextField jobTitleT2 = new JTextField("Position", 15);
		gbcI.gridx = 2;
		gbcI.gridy = 1;
		inventoryBG.add(jobTitleT2, gbcI);

		JTextField emailT2 = new JTextField("fakeemail@gmail.com", 30);
		gbcI.gridx = 3;
		gbcI.gridy = 1;
		inventoryBG.add(emailT2, gbcI);

		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}

	private void addUserLoginPage() {
		AddUserMainFrame = new JFrame("Create New User?");
		AddUserMainFrame
				.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		AddUserMainFrame.setBounds(0, 0, 400, 200);
		AddUserMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				AddUserMainFrame.dispose();
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

		// JLabel announcement = new JLabel("Please fill out all the information below
		// and a new temporary password will be sent to your email shortly");
		JTextArea announcement = new JTextArea(1, 30);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Enter your username and password to confirm you wish to create a new user");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.anchor = GridBagConstraints.CENTER;
		// gbcA.insets = new Insets(0, 10, 0, 10);
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.insets = new Insets(0, 10, 0, 10);
		announcementBG.add(announcement, gbcA);

		// request username and password
		gbcI.insets = new Insets(5, 5, 5, 5);
		JLabel userNameL = new JLabel("Username: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);

		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);

		gbcI.fill = GridBagConstraints.HORIZONTAL;
		JTextField userNameT = new JTextField(15);
		gbcI.anchor = GridBagConstraints.WEST;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);

		JPasswordField confirmPasswordBar = new JPasswordField("Password", 18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;

		infoBG.add(confirmPasswordBar, gbcI);

		// submit button
		JButton requestB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(requestB, gbcI);
		AddUserMainFrame.setContentPane(controlPanel);
		AddUserMainFrame.setVisible(true);

		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserMainFrame.dispose();
				addUserPage();
			}
		});
	}

	private void addUserPage() {
		auMainFrame = new JFrame("Create New User");
		auMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		auMainFrame.setBounds(0, 0, 315, 335);
		auMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				auMainFrame.dispose();
			}
		});

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

		// JLabel announcement = new JLabel("Please fill out all the information below
		// and a new temporary password will be sent to your email shortly");
		JTextArea announcement = new JTextArea(2, 20);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Please fill out all the information below to create a new user");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		// gbcA.anchor = GridBagConstraints.SOUTHWEST;
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.weighty = 1;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		announcementBG.add(announcement, gbcA);

		// request first and last name, job title, email
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

		JTextField passwordT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 4;
		infoBG.add(passwordT, gbcI);

		// submit button
		JButton generateB = new JButton("Genenrate Password");
		generateB.setFont(new Font("HP Simplified Hans", Font.PLAIN, 10));
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		infoBG.add(generateB, gbcI);

		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 5;
		infoBG.add(requestB, gbcI);
		auMainFrame.setContentPane(controlPanel);
		auMainFrame.setVisible(true);
		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auMainFrame.dispose();
				/*
				 * String firstname = fNameT.getText();
				 * String lastname = lNameT.getText();
				 * String jobTitle = jobTitleT.getText();
				 * String email = emailT.getText();
				 * 
				 * User newUser = new User(firstname, lastname, jobTitle, email));
				 * newUser.setID(nextEmployeeID++);
				 * allUsers[usersIndex++] = newUser;
				 */
			}
		});
	}

	private void editUserPage() {
		euMainFrame = new JFrame("Edit User Info");
		euMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		euMainFrame.setBounds(0, 0, 400, 200);
		euMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				euMainFrame.dispose();
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

		// JLabel announcement = new JLabel("Please fill out all the information below
		// and a new temporary password will be sent to your email shortly");
		JTextArea announcement = new JTextArea(1, 30);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Enter your username and password to confirm you wish to edit another user's information");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		gbcA.anchor = GridBagConstraints.CENTER;
		// gbcA.insets = new Insets(0, 10, 0, 10);
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.insets = new Insets(0, 10, 0, 10);
		announcementBG.add(announcement, gbcA);

		// request username and password
		gbcI.insets = new Insets(5, 5, 5, 5);
		JLabel userNameL = new JLabel("Username: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);

		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);

		gbcI.fill = GridBagConstraints.HORIZONTAL;
		JTextField userNameT = new JTextField(15);
		gbcI.anchor = GridBagConstraints.WEST;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);

		JPasswordField confirmPasswordBar = new JPasswordField("Password", 18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;

		infoBG.add(confirmPasswordBar, gbcI);

		// submit button
		JButton requestB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(requestB, gbcI);
		euMainFrame.setContentPane(controlPanel);
		euMainFrame.setVisible(true);

		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				euMainFrame.dispose();
				fNameT.setEditable(true);
				jobTitleT.setEditable(true);
				emailT.setEditable(true);
				lNameT.setEditable(true);
			}
		});
	}

	private void deleteUserLoginPage() {
		deleteUserMainFrame = new JFrame("Delete User");
		deleteUserMainFrame
				.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		deleteUserMainFrame.setBounds(0, 0, 400, 200);
		deleteUserMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				deleteUserMainFrame.dispose();
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

		// JLabel announcement = new JLabel("Please fill out all the information below
		// and a new temporary password will be sent to your email shortly");
		JTextArea announcement = new JTextArea(1, 30);
		announcement.setForeground(Color.BLACK);
		announcement.setText(
				"Enter your username and password to confirm you wish to delete selected user. This action is not undoable.");
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

		// request username and password
		gbcI.insets = new Insets(5, 5, 5, 5);
		JLabel userNameL = new JLabel("Username: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);

		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);

		// gbcI.insets = new Insets(5, 5,5, 5);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		JTextField userNameT = new JTextField(15);
		gbcI.anchor = GridBagConstraints.WEST;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);

		JPasswordField confirmPasswordBar = new JPasswordField("Password", 18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;
		infoBG.add(confirmPasswordBar, gbcI);

		// submit button
		JButton requestB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(requestB, gbcI);
		deleteUserMainFrame.setContentPane(controlPanel);
		deleteUserMainFrame.setVisible(true);

		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserMainFrame.dispose();

			}
		});

	}
}
