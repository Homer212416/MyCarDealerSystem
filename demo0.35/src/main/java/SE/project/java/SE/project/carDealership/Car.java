package SE.project.carDealership;

public class Car extends Vehicle {
	private static final long serialVersionUID = -8046573803286029881L;
	private String carType;

	public Car(String make, String model, String color, int year, double price, String carType) {
		super(make, model, color, year, price);
		this.carType = carType;
	}

	// constructor only for the database to create a car object
	public Car(int id, String make, String model, String color, int year, double price, String carType,
			boolean inInventory) {
		super(id, make, model, color, year, price, inInventory);
		this.carType = carType;
	}

	public String getCarType() {
		return carType;
	}
}
