package SE.project.carDealership;

public class Motorcycle extends Vehicle {
	private static final long serialVersionUID = -3762021630826741458L;
	private String handlebarType;

	public Motorcycle(String make, String model, String color, int year, double price, String handlebarType) {
		super(make, model, color, year, price);
		this.handlebarType = handlebarType;
	}

	// constructor only for the database to create a motorcycle object
	public Motorcycle(int id, String make, String model, String color, int year, double price, boolean inInventory,
			String handlebarType) {
		super(id, make, model, color, year, price, inInventory);
		this.handlebarType = handlebarType;
	}

	public String getHandlebarType() {
		return handlebarType;
	}
}
