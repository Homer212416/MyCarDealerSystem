package SE.project.controller;

import SE.project.carDealership.Dealership;
import java.sql.SQLException;

public class DealershipInfoPageController {

    private Dealership dealership;

    public DealershipInfoPageController() throws SQLException{
        this.dealership = new Dealership();
    }

    public String[] getDealershipInfo() {
        return dealership.displayAllInfo();
    }

    public boolean deleteDealership() {
        return dealership.deleteDealership();
    }
}
