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
	
	public User(){u_UserLayer = new UserLayer();}
	
	public User(int ID, String firstName, String lastName, String jobTitle, String email, String password) throws SQLException{
		//System.out.println("User Called");
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.email = email;
		this.password = password;
		ID = 0;
		switch(jobTitle){
			case "SalesPerson":
				//System.out.println("is salesperson");
				editSecurity = 2;
				pageSecurity = 2;
				break;
			case "Manager":
				editSecurity = 1;
				pageSecurity = 2;
				//System.out.println("is manager");
				break;
			case "Admin":
				editSecurity = 1;
				pageSecurity = 1;
				//System.out.println("is admin");
				break;
		}
		//System.out.println("user page security" + pageSecurity);
		u_UserLayer = new UserLayer(ID, firstName,lastName, jobTitle, email, password, editSecurity, pageSecurity);
		
	}
	
	public int getEditSecurity(int ID) throws SQLException{
		int edittingSecurity = u_UserLayer.getEditSecurity(ID);
		return edittingSecurity;
		
	}
	
	public int[] getPageSecurity(int ID) throws SQLException{
		
		int security = u_UserLayer.getPageSecurity(ID);
		
		int[] list = new int[5];
		switch(security){
			case 1:
				int[] onelist = {1,2,3,4,5};
				list = onelist;
				break;
			case 2:
				int[] twolist = {1,2,3,5};
				list = twolist;
				break;
			default:
				int[] deafaultlist = {1,2,3,5};
				list = deafaultlist;
				break;
		}
		
		return(list);//return actual editSecurity
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