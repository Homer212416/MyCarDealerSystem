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

public class loginPage{
	private JFrame mainFrame;
	private JFrame requestPmainFrame;
	private JFrame resetPmainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private loginPageController controller;
	
	public loginPage(loginPageController controller){
      this.controller = controller;
	  prepareLoginGUI();
	}
	public static void main(String[] args){
      //loginPage loginPage = new loginPage();  
      //loginPage.showEventDemo();       
	}
	private void prepareLoginGUI(){
		
		//create window
		mainFrame = new JFrame("Login");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 300, 400);
     
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});    
		
		//main panel to hold layout of panels
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 300, 400);
		controlPanel.setLayout(new GridBagLayout());
		GridBagLayout layoutC = new GridBagLayout();
		controlPanel.setLayout(layoutC);
		GridBagConstraints gbcC = new GridBagConstraints();
		
		//header car Image
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setOpaque(true);
		Image carIconImage = (Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/bg.jpg")));
		Image newCarImage = carIconImage.getScaledInstance(300, 168,Image.SCALE_DEFAULT);
		lblNewLabel_1.setIcon(new ImageIcon(newCarImage));
		//lblNewLabel_1.setBounds(0, -68, 300, 168);
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.HORIZONTAL;
		gbcC.insets = new Insets(-68,0,0,0);
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		controlPanel.add(lblNewLabel_1,gbcC);
		
		//panel to hold all labels and text boxes
		JPanel gridA = new JPanel();
		gridA.setBackground(new Color(230, 230, 230));
		//gridA.setBounds(0, 100, 280, 250);
		gridA.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		gridA.setLayout(layoutA);	
		GridBagConstraints gbcA = new GridBagConstraints();
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridy = 1;
		gbcC.weighty = .1;
		gbcC.weightx = .1;
		controlPanel.add(gridA,gbcC);
		
		JLabel loginLabel = new JLabel("Login to your account:");  
		loginLabel.setFont(new Font("HP Simplified Hans", Font.PLAIN, 18));
		gbcA.anchor = GridBagConstraints.CENTER;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		gbcA.gridwidth= 2;
		gbcA.insets = new Insets(0,0,0,0);
		gridA.add(loginLabel,gbcA);
		
		JTextField usernameBar  = new JTextField("Username", 18);
		gbcA.insets = new Insets(20,10,0,0);
		gbcA.gridwidth= 2;
		gbcA.gridx = 0;
		gbcA.gridy = 1;
		gridA.add(usernameBar,gbcA);
		
		JPasswordField passwordBar  = new JPasswordField("Password",18);
		//passwordBar.setEchoChar('~');
		
		gbcA.gridx = 0;
		gbcA.gridy = 2;
		gridA.add(passwordBar,gbcA);
		
		JButton forgotButton = new JButton("Forgot Password?");
		gbcA.gridwidth= 1;
		gbcA.anchor = GridBagConstraints.EAST;
		gbcA.gridx = 0;
		gbcA.gridy = 3;
		gbcA.weightx = .5;
		gbcA.insets = new Insets(30,0,0,20);
		forgotButton.setFont(new Font("HP Simplified Hans", Font.PLAIN, 8));
		forgotButton.setOpaque(false);
		forgotButton.setContentAreaFilled(false);
		forgotButton.setBorderPainted(false);
		gridA.add(forgotButton,gbcA);
		
		
		JButton submitButton = new JButton("Submit");
		gbcA.anchor = GridBagConstraints.WEST;
		gbcA.gridwidth= 1;
		gbcA.gridx = 1;
		gbcA.gridy = 3;
		gbcA.weightx = .5;
		gridA.add(submitButton,gbcA);
		
		
		submitButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = usernameBar.getText();
				char[] password = passwordBar.getPassword();
				
				controller.submitPressed(mainFrame, userName, password);
			}
		});
		
		forgotButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				controller.forgotPressed(mainFrame);
			}
		});
		
		
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);

	}
	
	public void requestPasswordPage(){
		requestPmainFrame = new JFrame("Request New Password");
		requestPmainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		requestPmainFrame.setBounds(0, 0, 315, 335);
		requestPmainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            requestPmainFrame.dispose();
			}        
		});
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 315, 335);
		controlPanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);	
		GridBagConstraints gbcC = new GridBagConstraints();
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 300, 50);
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.HORIZONTAL;
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		controlPanel.add(announcementBG,gbcC);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 290, 250);
		GridBagConstraints gbcI = new GridBagConstraints();
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridy = 1;
		gbcC.weighty = .1;
		gbcC.weightx = .1;
		controlPanel.add(infoBG,gbcC);
		
		JTextArea announcement = new JTextArea(2, 20);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Please fill out all the information below and a new temporary password will be sent to your email shortly");
		announcement.setWrapStyleWord(true);
		announcement.setLineWrap(true);
		announcement.setOpaque(false);
		announcement.setEditable(false);
		announcement.setFocusable(false);
		//gbcA.anchor = GridBagConstraints.SOUTHWEST;
		gbcA.fill = GridBagConstraints.BOTH;
		gbcA.weighty = 1;
		gbcA.gridx = 0;
		gbcA.gridy = 0;
		announcementBG.add(announcement, gbcA);
		
		//request first and last name, job title, email 
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
		
		//submit button
		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 5;
		infoBG.add(requestB, gbcI);
		requestPmainFrame.setContentPane(controlPanel);
		requestPmainFrame.setVisible(true);
		
		requestB.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				controller.requestSubmitPressed(requestPmainFrame);
			}
		});
	}
	
	
	public void resetPasswordPage(){
		resetPmainFrame = new JFrame("Reset Password");
		resetPmainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		resetPmainFrame.setBounds(0, 0, 315, 335);
		resetPmainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            resetPmainFrame.dispose();
			}        
		});
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 315, 335);
		controlPanel.setLayout(new GridLayout());
		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);	
		GridBagConstraints gbcC = new GridBagConstraints();
		
		JPanel announcementBG = new JPanel();
		announcementBG.setBackground(Color.RED);
		announcementBG.setLayout(new GridBagLayout());
		GridBagLayout layoutA = new GridBagLayout();
		announcementBG.setLayout(layoutA);
		GridBagConstraints gbcA = new GridBagConstraints();
		announcementBG.setBounds(0, 0, 300, 50);
		controlPanel.add(announcementBG);
		gbcC.anchor = GridBagConstraints.NORTH;
		gbcC.fill = GridBagConstraints.HORIZONTAL;
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		controlPanel.add(announcementBG,gbcC);
		
		JPanel infoBG = new JPanel();
		infoBG.setBackground(new Color(230, 230, 230));
		infoBG.setLayout(new GridBagLayout());
		GridBagLayout layoutI = new GridBagLayout();
		infoBG.setLayout(layoutI);
		infoBG.setBounds(10, 50, 290, 250);
		GridBagConstraints gbcI = new GridBagConstraints();
		gbcC.fill = GridBagConstraints.BOTH;
		gbcC.gridx = 0;
		gbcC.gridy = 1;
		gbcC.weighty = .1;
		gbcC.weightx = .1;
		controlPanel.add(infoBG,gbcC);
		
		JTextArea announcement = new JTextArea(2, 20);
		announcement.setForeground(Color.BLACK);
		announcement.setText("Please fill out all the information below to reset your password. This must be done after recieving a temporary password from an admin.");
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
		
		JPasswordField tempPasswordBar  = new JPasswordField("Password",18);
		tempPasswordBar.setEchoChar('~');
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		gbcI.weightx = .99;
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		infoBG.add(tempPasswordBar, gbcI);
		
		JPasswordField newPasswordBar  = new JPasswordField("Password",18);
		newPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 2;
		infoBG.add(newPasswordBar, gbcI);
		
		JPasswordField confirmPasswordBar  = new JPasswordField("Password",18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 3;
		infoBG.add(confirmPasswordBar, gbcI);
		
		JTextField userNameT = new JTextField(15);
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);
		
		//submit button
		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		infoBG.add(requestB, gbcI);
		resetPmainFrame.setContentPane(controlPanel);
		resetPmainFrame.setVisible(true);
		
		//set functions
		requestB.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				controller.newPasswordSubmitPressed(resetPmainFrame);
			}
		});
		
	}
}