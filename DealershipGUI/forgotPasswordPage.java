
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

public class forgotPasswordPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public forgotPasswordPage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      forgotPasswordPage forgotPasswordPage = new forgotPasswordPage();
      //forgotPasswordPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Request New Password");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 315, 335);
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
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
		
		//JLabel announcement = new JLabel("Please fill out all the information below and a new temporary password will be sent to your email shortly");
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
		
		//submit button
		JButton requestB = new JButton("Submit");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.weightx = .01;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		infoBG.add(requestB, gbcI);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
		
	}
}