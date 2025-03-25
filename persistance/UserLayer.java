package persistance;

import java.sql.SQLException;

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
	}

	public UserLayer(int ID, String firstName, String lastName, String jobTitle, String email, String password, int editSecurity, int pageSecurity) throws SQLException {
		u_firstName= firstName;
		u_lastName = lastName;
		u_ID = ID;
		u_jobTitle = jobTitle;
		u_email = email;
		u_password = password; 
		u_pageSecurity = pageSecurity;
		u_editSecurity = editSecurity;
		System.out.println("newUSer : " + u_pageSecurity);
		DBManager.getInstance().runInsert("INSERT INTO usersInfo " + "(firstName, lastName, jobTitle, email, password, editSecurity, pageSecurity) " + "VALUES" + " ('" + firstName + "', '" + lastName + "', '" + jobTitle + "', '" + email + "', '" + password + "', " + editSecurity + ", " +pageSecurity +");");
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
			System.out.println("User : " + u_pageSecurity);
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
		System.out.println(userID);
		try{
			var pageS = DBManager.getInstance().runQuery("SELECT ID, jobTitle, pageSecurity FROM usersInfo WHERE ID =" + userID + "");
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