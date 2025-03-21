package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class dealerShipInfoPageController{
	
	
	public void dealerShipInfoPageController(){
		dealerShipInfoPage frame = new dealerShipInfoPage(this);
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
	
	public void setDeleteEnabled(JButton button){
		//get info from UserDAO
		button.setEnabled(true);
		//set up to be true if admin false if else
	}

	public void deletePressed(){//route to DealershipDAO
	}
}