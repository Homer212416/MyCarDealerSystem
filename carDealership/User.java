package carDealership;

import persistance.UserLayer;

import java.sql.SQLException;
import java.time.LocalDate;

public class User {
	private int ID;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String email;
	private String password;
	public User[] users;
	public UserLayer u_UserLayer;
	private int editSecurity;
	private int pageSecurity;
	
	public User(int ID, String firstName, String lastName, String jobTitle, String email, String password) throws SQLException{
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.email = email;
		this.password = password;
		ID = 0;
		switch(jobTitle){
			case "SalesPerson":
				System.out.println("is salesperson");
				editSecurity = 2;
				pageSecurity = 2;
				break;
			case "Manager":
				editSecurity = 1;
				pageSecurity = 2;
				System.out.println("is manager");
				break;
			case "Admin":
				editSecurity = 1;
				pageSecurity = 1;
				System.out.println("is admin");
				break;
		}
		System.out.println(editSecurity);
		u_UserLayer = new UserLayer(ID, firstName,lastName, jobTitle, email, password, editSecurity, pageSecurity);
		
	}
	
	public int getEditSecurity(int ID){
		return(1);//return actual editSecurity
	}
	
	public int getPageSecurity(int ID){
		return(1);//return actual editSecurity
	}
	/*
	public boolean validateUserLogin(String username){
		return(u_UserLayer.validateUserLogin(username));
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