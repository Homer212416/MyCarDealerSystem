
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

public class deleteUserPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public deleteUserPage(){
		prepareInventoryGUI();
	}
	public static void main(String[] args){
      deleteUserPage deleteUserPage = new deleteUserPage();
      //deleteUserPage.showEventDemo();
	}
	@SuppressWarnings("unchecked")
	private void prepareInventoryGUI(){
		mainFrame = new JFrame("Delete User");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 400, 200);
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
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
		
		//JLabel announcement = new JLabel("Please fill out all the information below and a new temporary password will be sent to your email shortly");
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
		
		//request username and password
		gbcI.insets = new Insets(5, 5,5, 5);
		JLabel userNameL = new JLabel("Username: ");
		gbcI.anchor = GridBagConstraints.EAST;
		gbcI.gridx = 0;
		gbcI.gridy = 0;
		infoBG.add(userNameL, gbcI);
		
		JLabel passwordL = new JLabel("Password: ");
		gbcI.gridx = 0;
		gbcI.gridy = 1;
		infoBG.add(passwordL, gbcI);
		
		//gbcI.insets = new Insets(5, 5,5, 5);
		gbcI.fill = GridBagConstraints.HORIZONTAL;
		JTextField userNameT = new JTextField(15);
		gbcI.anchor = GridBagConstraints.WEST;
		gbcI.gridx = 1;
		gbcI.gridy = 0;
		infoBG.add(userNameT, gbcI);
		
		JPasswordField confirmPasswordBar  = new JPasswordField("Password",18);
		confirmPasswordBar.setEchoChar('~');
		gbcI.gridx = 1;
		gbcI.gridy = 1;
		gbcI.weightx = .9;
		infoBG.add(confirmPasswordBar, gbcI);
		
		//submit button
		JButton requestB = new JButton("Confirm");
		gbcI.fill = GridBagConstraints.NONE;
		gbcI.anchor = GridBagConstraints.NORTHWEST;
		gbcI.gridx = 2;
		gbcI.gridy = 4;
		gbcI.weightx = .9;
		gbcI.weighty = .3;
		infoBG.add(requestB, gbcI);
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
		
	}
}