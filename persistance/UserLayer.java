package persistance;
import java.util.*;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
public class UserLayer {
	private int u_ID;
	private String u_firstName;
	private String u_lastName;
	private String u_jobTitle;
	private String u_email;
	private String u_password;
	private String id_firstName;
	private String id_lastName;
	private int u_editSecurity;
	private int u_pageSecurity;
	private int id_pageSecurity;
	private String id_userID;
	private String id_jobTitle;
	
	public UserLayer() {
		try{
				var users = "CREATE TABLE IF NOT EXISTS usersInfo (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "firstName text NOT NULL, lastName text NOT NULL, jobTitle text not NULL, email text NOT NULL, password NOT NULL)";
				DBManager.getInstance().runInsert(users);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}

	public UserLayer(String firstName, String lastName, String jobTitle, String email, String password) throws SQLException {
		u_firstName= firstName;
		u_lastName = lastName;
		u_jobTitle = jobTitle;
		u_email = email;
		u_password = password; 

	
		try{
			DBManager.getInstance().runInsert("INSERT INTO usersInfo " + "(firstName, lastName, jobTitle, email, password) " + "VALUES" + " ('" + firstName + "', '" + lastName + "', '" + jobTitle + "', '" + email + "', '" + password + "');");
				
		}catch(SQLException e){
				System.out.println(e.getMessage());
	
			}
	}
	
	public int getNewID(String firstName, String lastName, String jobTitle, String email, String password){
		int newUserID = -1;
		try{
			var resultSet = DBManager.getInstance().runQuery("SELECT ID FROM usersInfo WHERE firstName = '" + firstName + "'AND lastName = '" + lastName + "'AND email = '" + email + "';");
			
			while (resultSet.next()) {
				newUserID = resultSet.getInt("ID");
				return newUserID;	
			}				
		}catch(SQLException e){
				System.out.println(e.getMessage());
				return -1;
			}
		return newUserID;
	}
		
	public boolean existsAndSet() throws SQLException {
		var resultSet = DBManager.getInstance().runQuery("SELECT * FROM usersInfo");
		boolean usersFound = false;

		while (resultSet.next()) {
			usersFound = true;
			u_firstName = resultSet.getString("firstName");
			u_lastName = resultSet.getString("lastName");
			u_ID = resultSet.getInt("ID");
			u_jobTitle = resultSet.getString("jobTitle");
			u_email = resultSet.getString("email");
			u_password = resultSet.getString("password");
			u_pageSecurity = resultSet.getInt("pageSecurity");
			u_editSecurity = resultSet.getInt("editSecurity");
			
		}

		return usersFound;
	}
	
	public String getName(int userID) throws SQLException{
		var name = DBManager.getInstance().runQuery("SELECT ID, firstName, lastName FROM usersInfo WHERE ID =" + userID + "");
		while (name.next()) {
			id_userID = name.getString("ID");
			id_firstName = name.getString("firstName");
			id_lastName = name.getString("lastName");
		}
		return(id_firstName + " " + id_lastName);
	}
	
	public int getPageSecurity(int userID) throws SQLException{
		
		try{
			var pageS = DBManager.getInstance().runQuery("SELECT ID, usersInfo.jobTitle,security.jobTitle, pageSecurity FROM usersInfo, security WHERE ID =" + userID + " AND usersInfo.jobTitle = security.jobTitle");
			while (pageS.next()) {
				id_jobTitle = pageS.getString("jobTitle");
				id_userID = pageS.getString("ID");
				id_pageSecurity = pageS.getInt("pageSecurity");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id_pageSecurity;
	}
	
	public int getEditSecurity(int userID) throws SQLException{
		
		try{
			var pageS = DBManager.getInstance().runQuery("SELECT ID, usersInfo.jobTitle,security.jobTitle, editSecurity FROM usersInfo, security WHERE ID =" + userID + " AND usersInfo.jobTitle = security.jobTitle");
			while (pageS.next()) {
				id_jobTitle = pageS.getString("jobTitle");
				id_userID = pageS.getString("ID");
				id_pageSecurity = pageS.getInt("editSecurity");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id_pageSecurity;
	}
	
	public String[][] getAllUsers(){
		int count = 0;

		try{
			
			ResultSet rs3 = DBManager.getInstance().runQuery("SELECT COUNT(*) AS count FROM usersInfo");
			while(rs3.next()){
				count = rs3.getInt("count");
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String[][] usersInfo = new String[count][];
		try {
            String query = "SELECT * FROM usersInfo";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
			int x = 0;
			while (resultSet.next()) {
				String first = resultSet.getString("firstName");
				String last = resultSet.getString("lastName");
				String job = resultSet.getString("jobTitle");
				String email = resultSet.getString("email");
				String ID = Integer.toString(resultSet.getInt("ID"));
				String[] user = {first, last, job, email, ID}; 
				
				usersInfo[x] = user;
				x++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
        return usersInfo;      
	}
	
	public String checkPassword(int userID){
		String storedPassword = "";
		try {
			String query = "SELECT password FROM usersInfo WHERE ID =" + userID + "";
			ResultSet resultSet = DBManager.getInstance().runQuery(query);
			while (resultSet.next()) {
				storedPassword = resultSet.getString("password");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return storedPassword;
	}

	// public static helper methods for password hashing, etc.
    public static String hashPassword(String password) {
        // hash the password using a SHA-256 algorithm
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] hashBytes = digest.digest(password.getBytes());

            // Convert the byte array to a readable hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


	/*
	public boolean validateUserLogin(String username){
		if(userName exsist){
			check if password correct{
				if true return true
		}}else{return false}
		
	}
	
	
	public String generatePassword(){
	//use has to generatePassword
	//store password
	}
	
	public boolean validateUserInfo(){
	}
	
	public boolean validateNewPassword(){}
	*/
}
