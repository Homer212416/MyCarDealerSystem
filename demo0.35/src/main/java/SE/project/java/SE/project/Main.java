package SE.project;

import SE.project.GUI.*;
import SE.project.carDealership.Dealership;
import SE.project.carDealership.*;
import SE.project.persistance.*;
import java.sql.SQLException;

public class Main {
	public static VehicleDAO inventory;
	public static Dealership dealership;
	
    public static void main(String[] args) throws SQLException{
        // if no dealerships exist, go to FirstLaunchPage
        // else, go to loginPage
        Dealership dealership = new Dealership();
		System.out.println("main");
        if (!dealership.exists()) {
            System.out.println("no dealership exsist");
			new FirstLaunchPage();
        } else {
			System.out.println("Name on main: " + dealership.getName());
            new LoginPage();
			VehicleDAO inventory = new VehicleDAO();

			
        }
		

    }
}
