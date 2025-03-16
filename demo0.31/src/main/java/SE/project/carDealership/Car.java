package SE.project.carDealership;

import SE.project.persistance.VehicleDAO;
import java.sql.SQLException;

public class Car extends Vehicle {
	private static final long serialVersionUID = -8046573803286029881L;
	private String type;
	private VehicleDAO vehicleDAO;

	public Car(String make, String model, String color, int year, double price, String type) {
		super(make, model, color, year, price);
		this.type = type;
		this.vehicleDAO = new VehicleDAO();
	}

	public Car(Car c) {
		this(c.make, c.model, c.color, c.year, c.price, c.type);
		this.vehicleDAO = new VehicleDAO();
	}

	public void save() throws SQLException {
		vehicleDAO.insertVehicle(this);
	}

	public void delete() throws SQLException {
		vehicleDAO.deleteVehicle(this.id);
	}

	public static Car getCar(int id) throws SQLException {
		return (Car) new VehicleDAO().getVehicleById(id);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void displayInfo() {
		String carName = make + " " + model + " " + year;
		System.out.println("ID: " + id);
		System.out.println("Car: " + carName);
		System.out.println("Color: " + color);
		System.out.println("Type: " + type);
		System.out.println("Price: " + price + " SAR");
	}

	public String toString() {
		return super.toString() + "\nType: " + type;
	}
}
