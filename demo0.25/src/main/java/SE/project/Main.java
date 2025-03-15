package SE.project;

import SE.project.GUI.*;
import SE.project.carDealership.Dealership;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // if no dealerships exist, go to FirstLaunchPage
        // else, go to loginPage
        var dealership = new Dealership();
        try {
            if (!dealership.existsAndSet()) {
                new FirstLaunchPage();
            } else {
                new LoginPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
}