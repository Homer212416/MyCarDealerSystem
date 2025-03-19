package SE.project.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import SE.project.controller.LoginPageController;

public class LoginPage {
	private JFrame mainFrame;
	private JFrame requestPmainFrame;
	private JFrame resetPmainFrame;
	private LoginPageController controller;

	public LoginPage() {
		this.controller = new LoginPageController();
		prepareLoginGUI();
	}

	public static void main(String[] args) {
		new LoginPage();
	}

	private void prepareLoginGUI() {
		//mainFrame is the whole window
		//controlPanel is the writable area within the mainframe
		/*
			ControlPanel
		 ____________________
		|		Car Image	|
		|___________________|
		|		Grid A		|
		|					|
		|					|
		|					|
		|					|
		|					|
		---------------------
				Grid A
		 ___________________
		|	loginLabel		|
		|					|
		|	usernameBar		|
		|	passwordBar		|
		|					|
		|	forgotButton submitButton|
		|					|
		---------------------
		*/
		mainFrame = new JFrame("Login");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 300, 400);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setLayout(null);

		//this is the car image across the top of the screen
		JLabel carImage = new JLabel(new ImageIcon(LoginPage.class.getResource("/images/bg.jpg")));
		carImage.setBounds(0, -68, 300, 168);
		controlPanel.add(carImage);

		JPanel gridA = new JPanel();
		gridA.setBackground(new Color(230, 230, 230));
		gridA.setBounds(0, 100, 280, 250);
		gridA.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		gridA.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		controlPanel.add(gridA);

		JLabel loginLabel = new JLabel("Login to your account:");
		loginLabel.setFont(new Font("HP Simplified Hans", Font.PLAIN, 18));
		gbcA.anchor = GridBagConstraints.CENTER;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.gridwidth = 2;
		gbcA.insets = new Insets(0, 0, 0, 0);
		gridA.add(loginLabel, gbcA);

		JTextField usernameBar = new JTextField("Username", 18);
		gbcA.insets = new Insets(20, 10, 0, 0);
		gbcA.gridwidth = 2;
		gbcA.gridx = 0;
		gbcA.gridy = 1;
		gridA.add(usernameBar, gbcA);

		JPasswordField passwordBar = new JPasswordField("Password", 18);
		gbcA.gridx = 0;
		gbcA.gridy = 2;
		gridA.add(passwordBar, gbcA);

		JButton forgotButton = new JButton("Forgot Password?");
		gbcA.gridwidth = 1;
		gbcA.anchor = GridBagConstraints.EAST;
		gbcA.gridx = 0;
		gbcA.gridy = 3;
		gbcA.weightx = .5;
		gbcA.insets = new Insets(30, 0, 0, 20);
		forgotButton.setFont(new Font("HP Simplified Hans", Font.PLAIN, 8));
		forgotButton.setOpaque(false);
		forgotButton.setContentAreaFilled(false);
		forgotButton.setBorderPainted(false);
		gridA.add(forgotButton, gbcA);

		JButton submitButton = new JButton("Submit");
		gbcA.anchor = GridBagConstraints.WEST;
		gbcA.gridwidth = 1;
		gbcA.gridx = 1;
		gbcA.gridy = 3;
		gbcA.weightx = .5;
		gridA.add(submitButton, gbcA);
		
		//populates MainFrame with the control panel
		mainFrame.setContentPane(controlPanel);
		//make window show on screeen
		mainFrame.setVisible(true);
		
		//tell program to act if button is presse
		forgotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestPasswordPage();
			}
		});

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameBar.getText();
				String password = new String(passwordBar.getPassword());
				/* if (controller.validateLogin(username, password)) {
					new inventoryPage();
					mainFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(mainFrame, "Invalid username or password.", "Login Failed",
							JOptionPane.ERROR_MESSAGE);
				} */
				
				new inventoryPage();
			}
		});
	}

	private void requestPasswordPage() {
		//requestPmainFrame is the whole window
		//controlPanel is the writable area within the mainframe
		/*MainFrame
			ControlPanel
		 ____________________
		| announcement BG	|
		|___________________|
		|		infoBG		|
		|					|
		|					|
		|					|
		|					|
		|					|
		---------------------
			announcementBG	
		 ____________________
		|  announcement		|
		|___________________|		
				
		 ______infoBG____________
		|	userNameL userT		|    L is label T is textbox
		|	fNameL fNameT		|
		|	lNameL lNameT		|
		|	jobTitleL jobTitleT	|
		|	emailL emailT		|
		|						|
		|			requestB	|	B is button
		|						|
		---------------------
		*/
		
		requestPmainFrame = new JFrame("Request New Password");
		requestPmainFrame
				.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		requestPmainFrame.setBounds(0, 0, 315, 335);
		requestPmainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				requestPmainFrame.dispose();
			}
		});

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 315, 335);
		controlPanel.setLayout(new GridLayout());

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
		announcement.setText(
				"Please fill out all the information below and a new temporary password will be sent to your email shortly");
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

		JLabel userNameL = new JLabel("User Name: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weighty = .5;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);

		JLabel fNameL = new JLabel("First Name: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weighty = .5;
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(fNameL, gbcI);

		JLabel lNameL = new JLabel("Last Name: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 2;
		infoBG.add(lNameL, gbcI);

		JLabel jobTitleL = new JLabel("Job Title: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 3;
		infoBG.add(jobTitleL, gbcI);

		JLabel emailL = new JLabel("Email: ");
		gbcI.gridx = 0;
		gbcI.gridy = 4;
		infoBG.add(emailL, gbcI);
		gbcI.anchor = GridBagConstraints.WEST;

		JTextField fNameT = new JTextField(15);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.weightx = .99;
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		infoBG.add(fNameT, gbcI);

		JTextField userNameT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);

		JTextField lNameT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 2;
		infoBG.add(lNameT, gbcI);

		JTextField jobTitleT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 3;
		infoBG.add(jobTitleT, gbcI);

		JTextField emailT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 4;
		infoBG.add(emailT, gbcI);

		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 5;
		infoBG.add(requestB, gbcI);
		requestPmainFrame.setContentPane(controlPanel);
		requestPmainFrame.setVisible(true);

		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userNameT.getText();
				controller.requestPasswordReset(username);
				JOptionPane.showMessageDialog(requestPmainFrame, "Password reset request sent.", "Request Sent",
						JOptionPane.INFORMATION_MESSAGE);
				resetPasswordPage();
				requestPmainFrame.dispose();
			}
		});
	}

	private void resetPasswordPage() {
		//resetPmainFrame is the whole window
		//controlPanel is the writable area within the mainframe
		/*MainFrame
			ControlPanel
		 ____________________
		| announcement BG	|
		|___________________|
		|		infoBG		|
		|					|
		|					|
		|					|
		|					|
		|					|
		---------------------
			announcementBG	
		 ____________________
		|  announcement		|
		|___________________|		
				
		 ______infoBG____________
		|	userNameL userT		|    L is label T is textbox
		|	tempL tempPasswordBar|
		|	newPassL newPasswordBar|
		|	confirmPassL confirmPasswordBar|
		|						|
		|						|
		|			requestB	|	B is button
		|						|
		---------------------
		*/
		resetPmainFrame = new JFrame("Reset Password");
		resetPmainFrame
				.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/icon.jpg")));
		resetPmainFrame.setBounds(0, 0, 315, 335);
		resetPmainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				resetPmainFrame.dispose();
			}
		});

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 315, 335);
		controlPanel.setLayout(new GridLayout());

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
		announcement.setText(
				"Please fill out all the information below to reset your password. This must be done after recieving a temporary password from an admin.");
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

		JLabel userNameL = new JLabel("Username: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weighty = .5;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);

		JLabel tempL = new JLabel("Old Password: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(tempL, gbcI);

		JLabel newPassL = new JLabel("New Password: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 2;
		infoBG.add(newPassL, gbcI);

		JLabel confirmPassL = new JLabel("Confirm Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 3;
		infoBG.add(confirmPassL, gbcI);
		gbcI.anchor = GridBagConstraints.WEST;

		JPasswordField tempPasswordBar = new JPasswordField("Password", 18);
		tempPasswordBar.setEchoChar('~');
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.weightx = .99;
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		infoBG.add(tempPasswordBar, gbcI);

		JPasswordField newPasswordBar = new JPasswordField("Password", 18);
		newPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 2;
		infoBG.add(newPasswordBar, gbcI);

		JPasswordField confirmPasswordBar = new JPasswordField("Password", 18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 3;
		infoBG.add(confirmPasswordBar, gbcI);

		JTextField userNameT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);

		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		infoBG.add(requestB, gbcI);
		resetPmainFrame.setContentPane(controlPanel);
		resetPmainFrame.setVisible(true);

		requestB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userNameT.getText();
				String oldPassword = new String(tempPasswordBar.getPassword());
				String newPassword = new String(newPasswordBar.getPassword());
				String confirmPassword = new String(confirmPasswordBar.getPassword());

				if (!newPassword.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(resetPmainFrame, "Passwords do not match.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				controller.resetPassword(username, oldPassword, newPassword);
				JOptionPane.showMessageDialog(resetPmainFrame, "Password reset successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				resetPmainFrame.dispose();
			}
		});
	}
}
