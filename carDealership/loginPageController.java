package carDealership;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class loginPageController{
	
	public loginPageController(){
		loginPage login = new loginPage(this);
	}

	//set actions
	public void forgotPressed(){
		/*
		forgotButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            requestPasswordPage();
         }          
		});
		*/
		
	}
	
	public void submitPressed(){
		//validate user information when pressed
		/*
		submitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
			///need to check user name and password against data base
			//set userNameTemp = username in bar
			//set passwordMatch = password in bar
			//if(validateUserLogin()){
            inventoryPage myInventory = new inventoryPage();
			//}else{////errormessage////}
         }          
		});
		*/
	}
	
	public void requestSubmitPressed(){
		//validate user information
		//then send new password
		//add button functions
		/*
		requestB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
			 //need to validate user information
			//validateUserInfo
			//if valid generate new password
			resetPasswordPage();
			requestPmainFrame.dispose();
         }          
		});
		*/
	}
	
	public void newPasswordSubmitPressed(){
		/*
		requestB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //validateNewPassword()
			resetPmainFrame.dispose();
         }          
		});
		*/
	}
}