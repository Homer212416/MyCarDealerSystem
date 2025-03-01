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

public class loginPage{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	public loginPage(){
      prepareLoginGUI();
	}
	public static void main(String[] args){
      loginPage loginPage = new loginPage();  
      //loginPage.showEventDemo();       
	}
	private void prepareLoginGUI(){
		mainFrame = new JFrame("Login");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/icon.jpg")));
		mainFrame.setBounds(0, 0, 300, 400);
     
		mainFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
			}        
		});    
      
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(230, 230, 230));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBounds(0, 0, 300, 400);
		controlPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setOpaque(true);
		Image carIconImage = (Toolkit.getDefaultToolkit().getImage(loginPage.class.getResource("/images/bg.jpg")));
		Image newCarImage = carIconImage.getScaledInstance(300, 168,Image.SCALE_DEFAULT);
		lblNewLabel_1.setIcon(new ImageIcon(newCarImage));
		lblNewLabel_1.setBounds(0, -68, 300, 168);
		controlPanel.add(lblNewLabel_1);
		
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
		//gbcA.insets = new Insets(20,20,0,0);
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
		//gbcA.insets = new Insets(10,-80,0,0);
		gbcA.anchor = GridBagConstraints.WEST;
		gbcA.gridwidth= 1;
		gbcA.gridx = 1;
		gbcA.gridy = 3;
		gbcA.weightx = .5;
		//gbcA.weighty = .2;
		//gbcA.insets = new Insets(10,130,0,0);
		gridA.add(submitButton,gbcA);
		
		mainFrame.setContentPane(controlPanel);
		mainFrame.setVisible(true);
	}
}