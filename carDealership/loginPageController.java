package carDealership;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.*;

import persistance.DBManager;

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
	
public void submitPressed(JFrame currentFrame, String userIdStr, char[] passwordChars) {
    try {
        int userId = Integer.parseInt(userIdStr.trim());
        String password = new String(passwordChars);

        //SQL QUery
        String query = "SELECT * FROM usersInfo WHERE ID = " + userId + " AND password = '" + password + "'";

        ResultSet rs = DBManager.getInstance().runQuery(query);

        if (rs.next()) {
            JOptionPane.showMessageDialog(currentFrame, "Login successful. Welcome!");
            currentFrame.dispose();
            new inventoryPageController(userId);
        } else {
            JOptionPane.showMessageDialog(currentFrame, "Invalid user ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(currentFrame, "User ID must be a number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(currentFrame, "An error occurred during login.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
	
public void requestSubmitPressed(JFrame oldPage, int userId, String firstName, String lastName, String jobTitle, String email) {
    try {
        // Build query string with raw SQL
        String query = "SELECT * FROM usersInfo WHERE ID = " + userId +
                       " AND firstName = '" + firstName + "'" +
                       " AND lastName = '" + lastName + "'" +
                       " AND jobTitle = '" + jobTitle + "'" +
                       " AND email = '" + email + "'";

        ResultSet rs = DBManager.getInstance().runQuery(query);

        if (rs.next()) {
            // Match found – allow reset
            JOptionPane.showMessageDialog(oldPage, "User verified. You can now reset your password.");
            oldPage.dispose();
            login.resetPasswordPage(userId); // Open reset screen with passed ID
        } else {
            //  No match
            JOptionPane.showMessageDialog(oldPage, "Information does not match our records.", "Verification Failed", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(oldPage, "Error verifying user info.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
	
	
public void newPasswordSubmitPressed(JFrame oldPage, int userId, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(oldPage, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Build raw SQL update query
        String updateQuery = "UPDATE usersInfo SET password = '" + newPassword + "' WHERE ID = " + userId;

        DBManager.getInstance().runInsert(updateQuery);

        JOptionPane.showMessageDialog(oldPage, " Password reset successfully.");
        oldPage.dispose();

        //  Return to login screen
        login = new loginPage(this);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(oldPage, "Error while resetting password.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
	
}