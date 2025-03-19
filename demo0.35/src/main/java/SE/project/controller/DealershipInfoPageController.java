package SE.project.controller;

import SE.project.carDealership.Dealership;

public class DealershipInfoPageController {

    private Dealership dealership;

    public DealershipInfoPageController() {
        this.dealership = new Dealership();
    }

    public String[] getDealershipInfo() {
        return dealership.displayAllInfo();
    }

    public boolean deleteDealership() {
        return dealership.deleteDealership();
    }
}
