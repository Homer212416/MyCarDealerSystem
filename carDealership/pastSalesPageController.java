package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;

public class pastSalesPageController{
	private int[] security;
	private int userID;
	private User user;
	
	public pastSalesPageController(int ID){
		this.userID = ID;
		user = new User();
		try{
			security = user.getPageSecurity(userID);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		pastSalesPage frame = new pastSalesPage(this);
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
	
	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box){
		String[] pageElements = new String[]{"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts","Sign Out"};
		for(String element : pageElements){
			box.addItem(element);
		}
		
	}
	
	public String[] getAllSales(){
		//currently in dealership.java
		//reroute to SaleDAO
		String[] sales = {Main.m_dealership.showSalesHistory()};
		return(sales);
	}
}
	