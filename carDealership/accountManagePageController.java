package carDealership;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;

import persistance.UserLayer;


public class accountManagePageController{
	private int[] security;
	private int userID;
	private User user;
	private UserLayer userLayer;
	private accountManagePage frame;
	private Boolean result = false;
	
	public accountManagePageController(int ID){
		this.userID = ID;
		user = new User();
		userLayer = new UserLayer();
		try{
			security = user.getPageSecurity(userID);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		frame = new accountManagePage(this);
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
	
	public String generatePassword(){
		//generatePassword
		return("abcde");
	}
	
	public String[][] getAllUsers(){
		String[][] usersInfo = userLayer.getAllUsers();
		return usersInfo;
	}
	
	public void adminUser(String type){
		switch(type){
				case "delete":
					frame.deleteUserLoginPage();;
					break;
				case "edit":
					frame.editUserPage();;
					break;
				case "add":
					frame.addUserLoginPage();
					break;
			}
	}
	
	public void isAdmin(String password, JFrame oldpage, String type){
		//if password is valid for user return true
		result = true;
		//close confirm password page
		oldpage.dispose();
		if(result){
			switch(type){
				case "delete":
					frame.deleteAdminConfirmed();
					break;
				case "edit":
					frame.editAdminConfirmed();
					break;
				case "add":
					frame.addUserPage();
					break;
			}
		}
	}
	
	public void editedUser(String[] editedInfo){
		//update database with new user info
		}
		
		
	public static void pressedSubmit(){
		//open addUserPage
		
	}
	
	public void newUserSubmit(String[] newUserInfo, JFrame oldPage){
		//create new user from user info
		//User newUser = new User(sdfsd)
		oldPage.dispose();
	}
		
	public void removeUser(int userID){
		//remove user with userID from database and refresh user list
		//frame.populateUser()
	}
}