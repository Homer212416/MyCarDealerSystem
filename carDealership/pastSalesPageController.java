package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class pastSalesPageController{
	
	public void pastSalesPageController(){
		pastSalesPage frame = new pastSalesPage(this);
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
	