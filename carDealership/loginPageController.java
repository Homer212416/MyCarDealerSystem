package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class loginPageController{
	private loginPage login;
	
	public loginPageController(){
		login = new loginPage(this);
	}

	//set actions
	public void forgotPressed(JFrame oldPage){
		login.requestPasswordPage();
		oldPage.dispose();		
	}
	
	public void submitPressed(JFrame newPage, String username, char[] password){
		//validate user information when pressed
		newPage.dispose();
		new inventoryPageController(1);

	}
	
	public void requestSubmitPressed(JFrame oldPage){
		
		//validate user information
		//then send new password
		//add button functions
		login.resetPasswordPage();
		oldPage.dispose();
		
	}
	
	public void newPasswordSubmitPressed(JFrame oldPage){
		//validate password match
		//update datebase open login page
		login = new loginPage(this);
		oldPage.dispose();
	}
}