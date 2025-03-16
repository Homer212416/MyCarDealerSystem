package SE.project.carDealership;

import SE.project.persistance.VehicleDAO;
import java.sql.SQLException;

public class Motorcycle extends Vehicle {
	private static final long serialVersionUID = -3762021630826741458L;
	private String handlebarType;
	private VehicleDAO vehicleDAO;

	public Motorcycle(String make, String model, String color, int year, double price, String handlebarType) {
		super(make, model, color, year, price);
		this.handlebarType = handlebarType;
		this.vehicleDAO = new VehicleDAO();
	}

	public Motorcycle(Motorcycle m) {
		this(m.make, m.model, m.color, m.year, m.price, m.handlebarType);
		this.vehicleDAO = new VehicleDAO();
	}

	public void save() throws SQLException {
		vehicleDAO.insertVehicle(this);
	}

	public void delete() throws SQLException {
		vehicleDAO.deleteVehicle(this.id);
	}

	public static Motorcycle getMotorcycle(int id) throws SQLException {
		return (Motorcycle) new VehicleDAO().getVehicleById(id);
	}

	public void displayInfo() {
		String motorcycleName = make + " " + model + " " + year;
		System.out.println("ID: " + id);
		System.out.println("Motorcycle: " + motorcycleName);
		System.out.println("Color: " + color);
		System.out.println("Handlebar type: " + handlebarType);
		System.out.println("Price: " + price + " SAR");
	}

	public String getHandlebarType() {
		return handlebarType;
	}

	public void setHandlebarType(String handlebarType) {
		this.handlebarType = handlebarType;
	}

	public String toString() {
		return super.toString() + "\nHandelbarType: " + handlebarType;
	}
}
