package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;

public class dealerShipInfoPageController{
	private int[] security;
	private int userID;
	private User user;
	
	public dealerShipInfoPageController(int ID){
		this.userID = ID;
		user = new User();
		try{
			security = user.getPageSecurity(userID);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		dealerShipInfoPage frame = new dealerShipInfoPage(this);
	}
	
	public void setDisabledPages(DefaultListSelectionModel ddb){
		if(security.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(4, 4);
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
	
	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box){
		String[] pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		for(String element : pageElements){
			box.addItem(element);
		}
		
	}
	
	public String[] getDealershipInfo(){
		//currently in dealership.java
		//reroute to DealershipDAO
		return(Main.m_dealership.getInfoGUI());
	}
	
	public boolean setDeleteEnabled(){
		//get info from UserDAO
		for(int num: security){
			if(num == 4){
				return true;
			}
		}
		return false;
		
		//set up to be true if admin false if else
	}

	public void deleteDealer(String name){//route to DealershipDAO
		//use name to find dealership in database and remove
	}
}