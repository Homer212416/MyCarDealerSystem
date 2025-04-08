package carDealership;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;
import java.util.*;
import persistance.DBManager;
import persistance.UserLayer;

public class accountManagePageController{
	private int[] security;
	private int userID;
	private User user;
	private UserLayer userLayer;
	private accountManagePage frame;
	private Boolean result = false;
	
	public accountManagePageController(int ID, int width, int height){
		this.userID = ID;
		user = new User();
		userLayer = new UserLayer();
		try{
				security = user.getPageSecurity(userID);
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		frame = new accountManagePage(this, width, height);
	}
	
	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box){
		String[] pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		for(String element : pageElements){
			box.addItem(element);
		}
		
	}
	
	public void setDisabledPages(DefaultListSelectionModel ddb){
		if(security.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(0, 3);
			ddb.addSelectionInterval(5, 5);
		}
	}
	
	public void pageMenuSelect(int sel, JFrame mainFrame, int w, int h){
		boolean contains = false;
		
		for(int page: security){
			if(sel == page)
				contains = true;
		}
		if(contains){
			if (sel== 1){
				inventoryPageController inv = new inventoryPageController(userID,w,h);
				mainFrame.dispose();
			}else if (sel== 2){
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 3){
				new pastSalesPageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 4){
				new accountManagePageController(userID, w, h);
				mainFrame.dispose();
			}else if(sel== 5){
				new loginPageController();
				mainFrame.dispose();
			}
		}
	}

	public static String generatePassword() {
		// generatePassword
		String alphabeta = "abcdefghijklmnopqrstuvwxyz";
		String password = "";
		for (int i = 0; i < 5; i++) {
			int randomIndex = (int) (Math.random() * alphabeta.length());
			password += alphabeta.charAt(randomIndex);
		}
		return password;
	}

	public String[][] getAllUsers() {
		String[][] usersInfo = userLayer.getAllUsers();
		return usersInfo;
	}
	
	public void adminUser(String type, JToggleButton button, ArrayList<JTextField> textBoxes){
		switch(type){
				case "delete":
					frame.deleteUserLoginPage(button);
					break;
				case "edit":
					frame.editUserPage(button, textBoxes);
					break;
				case "add":
					frame.addUserLoginPage(button);
					break;
			}
	}
	
	public void adminUser(String type, JToggleButton button){
		switch(type){
				case "delete":
					frame.deleteUserLoginPage(button);
					break;
				case "add":
					frame.addUserLoginPage(button);
					break;
			}
	}
	public void isAdmin(String password, JFrame oldpage, String type, JToggleButton button, ArrayList<JTextField> textBoxes){
		//if password is valid for user return true
		result = false;
		
		String storedPassword = userLayer.checkPassword(userID);
		
		if(storedPassword.matches(password)){result = true;}
	//close confirm password page
		oldpage.dispose();

		if(result){
			switch(type){
				case "delete":
					frame.deleteAdminConfirmed(true, button);
					break;
				case "edit":
					frame.editAdminConfirmed(true, button, textBoxes);
					break;
				case "add":
					frame.addUserPage(true, button);
					break;
			}
		}
		if(!result){
			switch(type){
				case "delete":
					frame.deleteAdminConfirmed(false, button);
					break;
				case "edit":
					frame.editAdminConfirmed(false, button, textBoxes);
					break;
				case "add":
					frame.addUserPage(false, button);
					break;
			}
		}
	}
		
	public void isAdmin(String password, JFrame oldpage, String type, JToggleButton button){
		//if password is valid for user return true
		result = false;
		
		String storedPassword = userLayer.checkPassword(userID);
		
		if(storedPassword.matches(password)){result = true;}
		//close confirm password page
		//System.out.println("password result: " + result);
		oldpage.dispose();
		if(result){
			switch(type){
				case "delete":
					frame.deleteAdminConfirmed(true, button);
					break;
				case "add":
					frame.addUserPage(true, button);
					break;
			}
		}
		if(!result){
			switch(type){
				case "delete":
					frame.deleteAdminConfirmed(false, button);
					break;
				case "add":
					frame.addUserPage(false, button);
					break;
			}
		}
	}

	public void editedUser(String[] editedInfo) {
		// update database with new user info
		String userID = editedInfo[0];
		String firstName = editedInfo[1];
		String lastName = editedInfo[2];
		String jobTitle = editedInfo[3];
		String email = editedInfo[4];
		String query = "UPDATE usersInfo SET firstName = '" + firstName + "', lastName = '" + lastName
				+ "', jobTitle = '" + jobTitle + "', email = '" + email + "' WHERE ID = " + userID;
		try {
			DBManager.getInstance().runInsert(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void pressedSubmit() {
		// open addUserPage
		//frame.addUserPage(true, button);
	}

	public int newUserSubmit(String[] newUserInfo, JFrame oldPage) {
		// create new user from user info
		int s = -1;
		String firstName = newUserInfo[0];
		String lastName = newUserInfo[1];
		String jobTitle = newUserInfo[2];
		String password = newUserInfo[4];
		String email = newUserInfo[3];
		boolean success = false;
		//String query = "INSERT INTO usersInfo (firstName, lastName, jobTitle, email, password) VALUES ('" + firstName
				//+ "', '" + lastName + "', '" + jobTitle + "', '" + password + "')";
		try {
			User newUser = new User(firstName, lastName, jobTitle, email, password);
			success = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		oldPage.dispose();
		if(success){
			s = userLayer.getNewID(firstName, lastName, jobTitle, email, password);
		}
		
		return s;
	}

	public void removeUser(int userID) {
		// remove user with userID from database and refresh user list
		// frame.populateUser()
		String query = "DELETE FROM usersInfo WHERE ID = " + userID;
		try {
			DBManager.getInstance().runInsert(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		frame.populateUsers();
	}
}