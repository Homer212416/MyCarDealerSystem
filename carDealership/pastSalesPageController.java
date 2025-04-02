package carDealership;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import persistance.SaleDAO;

public class pastSalesPageController {
	private int[] security;
	private int userID;
	private User user;

	public pastSalesPageController(int ID) {
		this.userID = ID;
		user = new User();
		try {
			security = user.getPageSecurity(userID);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// For dev testing only — disabled for production
		//debugInsertTestSale();

		//  Clean duplicates (keep only one)
		//new SaleDAO().keepOnlyOneTestSale();

		// Launch the view
		pastSalesPage frame = new pastSalesPage(this);
	}

	public void pageMenuSelect(int sel, JFrame mainFrame) {
		boolean contains = false;
		for (int page : security) {
			if (sel == page)
				contains = true;
		}
		if (contains) {
			System.out.print(contains);
			switch (sel) {
				case 1 : new inventoryPageController(userID);
				case 2 : new dealerShipInfoPageController(userID);
				case 3 : new pastSalesPageController(userID);
				case 4 : new accountManagePageController(userID);
				case 5 : new loginPageController();
			}
			mainFrame.dispose();
		}
	}

	@SuppressWarnings("unchecked")
	public void fillPageElements(JComboBox box) {
		String[] pageElements = new String[] {
				"", "Inventory", "Dealership Info", "Sales History", "Manage User Accounts", "Sign Out"
		};
		for (String element : pageElements) {
			box.addItem(element);
		}
	}

	public String[] getAllSales() {
		SaleDAO saleDAO = new SaleDAO();
		ArrayList<Sale> salesList = saleDAO.getAll();
		String[] sales = new String[salesList.size()];
		for (int i = 0; i < salesList.size(); i++) {
			sales[i] = salesList.get(i).toString();
		}
		return sales;
	}

	// 🔧 Test method to insert a sale manually
	private void debugInsertTestSale() {
		SaleDAO saleDAO = new SaleDAO();
		Sale testSale = new Sale(1, "Test Buyer", "buyer@email.com", LocalDate.now());
		saleDAO.insert(testSale);
	}
}