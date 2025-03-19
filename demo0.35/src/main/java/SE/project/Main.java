package SE.project;

import SE.project.GUI.*;
import SE.project.carDealership.Dealership;

public class Main {
    public static void main(String[] args) {
        // if no dealerships exist, go to FirstLaunchPage
        // else, go to loginPage
        Dealership dealership = new Dealership();
        if (!dealership.exists()) {
            new FirstLaunchPage();
        } else {
            new LoginPage();
        }

    }
}
