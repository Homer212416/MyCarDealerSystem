package carDealership;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sale implements Serializable {
    private static final long serialVersionUID = -7473395562909124471L;
    private Vehicle vehicle;
    private int vehicleID;
    private String buyerName;
    private String buyerContact;
    private LocalDate saleDate;
	
	public Sale(){}
    // Full constructor
    public Sale(int vehicleID, String buyerName, String buyerContact) {
        this.vehicle = vehicle;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.saleDate = LocalDate.now();
        this.vehicleID = vehicleID;
    }

    // Simplified constructor
    public Sale(int vehicleID, String buyerName, String buyerContact, LocalDate saleDate) {
        this.vehicleID = vehicleID;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.saleDate = saleDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleID +
               ", Buyer: " + buyerName +
               ", Contact: " + buyerContact +
               ", Date: " + saleDate;
    }
}
