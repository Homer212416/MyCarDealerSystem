package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;
import java.util.HashMap;

import persistance.DealershipDAO;
import persistance.DealershipLayer;

public class dealerShipInfoPageController {
	private int[] security;
	private int userID;
	private User user;
	private DealershipDAO dealershipDAO;
	private DealershipLayer dealershipLayer;

	public dealerShipInfoPageController(int ID, int width, int height) {
		this.userID = ID;
		user = new User();
		dealershipDAO = new DealershipDAO();
		dealershipLayer = new DealershipLayer();
		try {
			security = user.getPageSecurity(userID);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dealerShipInfoPage frame = new dealerShipInfoPage(this, width, height);
	}
	
	public void setDisabledPages(DefaultListSelectionModel ddb){
		if(security.length == 5){
			ddb.addSelectionInterval(0, 5);
		}else{
			ddb.addSelectionInterval(0, 3);
			ddb.addSelectionInterval(5, 5);
		}
	}
	
	public void pageMenuSelect(int sel, JFrame mainFrame, int width, int height){
		boolean contains = false;
		
		for(int page: security){
			if(sel == page)
				contains = true;
		}
		
		if(contains){
			if (sel== 1){
				inventoryPageController inv = new inventoryPageController(userID,width,height);
				mainFrame.dispose();
			}else if (sel== 2){
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID, width, height);
				mainFrame.dispose();
			}else if(sel== 3){
				new pastSalesPageController(userID, width, height);
				mainFrame.dispose();
			}else if(sel== 4){
				new accountManagePageController(userID, width, height);
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

	public String[] getDealershipInfo() {
		// currently in dealership.java
		// reroute to DealershipDAO
		HashMap<String, String> infoMap = dealershipLayer.getAllDealershipInfo();
		String[] info = new String[infoMap.size()];
		info[0] = "Name: " + infoMap.get("name");
		info[1] = "Location: " + infoMap.get("location");
		info[2] = "Capacity: " + infoMap.get("capacity");
		info[3] = "Available Space: " + infoMap.get("available space");
		info[4] = "Total Cars: " + infoMap.get("total cars");
		info[5] = "Total Motorcycles: " + infoMap.get("total motorcycles");
		info[6] = "Total from Sales: " + infoMap.get("total sales");
		info[7] = "Total Vehicles Sold: " + infoMap.get("total vehicles sold");
		return info;
	}

	public boolean setDeleteEnabled() {
		// get info from UserDAO
		try {
			int[] security = user.getPageSecurity(userID);
			for (int num : security) {
				if (num == 4) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void deleteDealer() {// route to DealershipDAO
		// use name to find dealership in database and remove
		// delete all info from database
		dealershipDAO.deleteAll();
	}

	public void goToFirstLaunchPage() {
		// go to first launch page
		new FirstLaunchPageController();
	}
}