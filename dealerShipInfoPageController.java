package carDealership;

import javax.swing.JFrame;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.util.HashMap;

import persistance.DealershipLayer;

public class dealerShipInfoPageController {
	private int[] security;
	private int userID;
	private User user;
	private DealershipLayer dealershipLayer;

	public dealerShipInfoPageController(int ID) {
		this.userID = ID;
		user = new User();
		dealershipLayer = new DealershipLayer();
		try {
			security = user.getPageSecurity(userID);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dealerShipInfoPage frame = new dealerShipInfoPage(this);
	}

	public void setDisabledPages(DefaultListSelectionModel ddb) {
		if (security.length == 5) {
			ddb.addSelectionInterval(0, 5);
		} else {
			ddb.addSelectionInterval(4, 4);
		}
	}

	public void pageMenuSelect(int sel, JFrame mainFrame) {
		boolean contains = false;

		for (int page : security) {
			if (sel == page)
				contains = true;
		}

		if (contains) {
			if (sel == 1) {
				inventoryPageController inv = new inventoryPageController(userID);
				mainFrame.dispose();
			} else if (sel == 2) {
				dealerShipInfoPageController dsC = new dealerShipInfoPageController(userID);
				mainFrame.dispose();
			} else if (sel == 3) {
				new pastSalesPageController(userID);
				mainFrame.dispose();
			} else if (sel == 4) {
				new accountManagePageController(userID);
				mainFrame.dispose();
			} else if (sel == 5) {
				new loginPageController();
				mainFrame.dispose();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box) {
		String[] pageElements = new String[] { "", "Inventory", "Dealership Info", "Sales History",
				"Manage User Accounts", "Sign Out" };
		for (String element : pageElements) {
			box.addItem(element);
		}

	}

	public String[] getDealershipInfo() {
		// currently in dealership.java
		// reroute to DealershipDAO
		HashMap<String, String> infoMap = dealershipLayer.getAllDealershipInfo();
		String[] info = new String[infoMap.size()];
		info[0] = "name: " + infoMap.get("name");
		info[1] = "location: " + infoMap.get("location");
		info[2] = "capacity: " + infoMap.get("capacity");
		info[3] = "available space: " + infoMap.get("available space");
		info[4] = "total cars: " + infoMap.get("total cars");
		info[5] = "total motorcycles: " + infoMap.get("total motorcycles");
		info[6] = "total sales: " + infoMap.get("total sales");
		info[7] = "total vehicles sold: " + infoMap.get("total vehicles sold");
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
		dealershipLayer.deleteDealership();
	}

	public void goToFirstLaunchPage() {
		// go to first launch page
		new FirstLaunchPageController();
	}
}