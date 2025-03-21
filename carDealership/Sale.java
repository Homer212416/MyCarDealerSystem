package carDealership;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {
	private static final long serialVersionUID = -7473395562909124471L;
	private Vehicle vehicle;
	private int vehicleID;
	private String buyerName;
	private String buyerContact;
	private LocalDate saleDate;

	public Sale(Vehicle vehicle, String buyerName, String buyerContact, LocalDate saleDate) {
		this.vehicle = vehicle;
		this.buyerName = buyerName;
		this.buyerContact = buyerContact;
		this.saleDate = saleDate;
		//add vehicleID to contructor
		this.vehicleID = 1;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

}
