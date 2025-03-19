package SE.project.controller;

import SE.project.carDealership.Dealership;
import SE.project.carDealership.IllegalCapacityException;

public class FirstLaunchPageController {

    public boolean createDealership(String name, String location, int capacity) {
        try {
            Dealership dealership = new Dealership(name, location, capacity);
            dealership.save();
            return true;
        } catch (IllegalCapacityException e) {
            return false;
        }
    }
}
