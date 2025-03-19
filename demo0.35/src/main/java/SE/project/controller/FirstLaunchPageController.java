package SE.project.controller;

import SE.project.carDealership.Dealership;
import SE.project.carDealership.IllegalCapacityException;

public class FirstLaunchPageController {

    public boolean createDealership(String name, String location, int capacity) {
        try {
            new Dealership(name, location, capacity);
            return true;
        } catch (IllegalCapacityException e) {
            return false;
        }
    }
}
