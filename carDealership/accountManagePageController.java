package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;


public class accountManagePageController{
	private int[] security;
	private int userID;
	private User user;
	
	public accountManagePageController(int ID){
		this.userID = ID;
		user = new User();
		try{
			security = user.getPageSecurity(userID);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		accountManagePage frame = new accountManagePage(this);
	}
	
	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box){
		String[] pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		for(String element : pageElements){
			box.addItem(element);
		}
		
	}
	
	public void pageMenuSelect(int sel, JFrame mainFrame){
		boolean contains = false;
		
		for(int page: security){
			if(sel == page)
				contains = true;
		}
		if(contains){
			System.out.print(contains);
			if (sel== 1){
				inventoryPageController inv = new inventoryPageController(userID);
				mainFrame.dispose();
			}else if (sel== 2){
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID);
				mainFrame.dispose();
			}else if(sel== 3){
				new pastSalesPageController(userID);
				mainFrame.dispose();
			}else if(sel== 4){
				new accountManagePageController(userID);
				mainFrame.dispose();
			}else if(sel== 5){
				new loginPageController();
				mainFrame.dispose();
			}
		}
	}
	
	public void addUser(){
		/*
		addUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            addUserLoginPage();
         }          
		});
		*/
	}
	
	public void editUser(){
		/*
		editUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            editUserPage();
         }          
		});
		*/
	}
	
	public void deleteUser(){
		/*
		deleteUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            deleteUserLoginPage();
         }          
		});
		*/
	}
	
	public User[] getAllUsers(){
		User[] users = new User[2];
		return users;
	}
	public boolean adminUser(){
		return true; //if admin
	}
	
	public static void pressedSubmit(){
		//open addUserPage
		/*
		submitB.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				AddUserMainFrame.dispose();
				addUserPage();
			 }          
			});
		*/
	}
	
	public void newUserSubmit(){
		/*
		requestB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            auMainFrame.dispose();
			/* String firstname = fNameT.getText();
			String lastname = lNameT.getText();
			String jobTitle = jobTitleT.getText();
			String email = emailT.getText();
			
			User newUser = new User(firstname, lastname, jobTitle, email));
			newUser.setID(nextEmployeeID++);
			allUsers[usersIndex++] = newUser; 
         }          
		});
		*/
	}
	
	public void editUserLogin(){
		//if login info is correct make text boxs editable
		//possibly leave edit button pressed and second press will make text box uneditable
		/*
		submitB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            euMainFrame.dispose();
			fNameT.setEditable(true);	
			jobTitleT.setEditable(true);
			emailT.setEditable(true);
			lNameT.setEditable(true);
         }          
		});
		*/
	}
		
	public void deleteUserLogin(){
		/*submitB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            deleteUserMainFrame.dispose();
			
         }          
		});
		*/
	}
}