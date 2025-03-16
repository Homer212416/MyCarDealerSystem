package SE.project.carDealership;

import SE.project.persistance.DealershipDAO;

public class Dealership {
	private String name;
	private String location;
	private int capacity;
	private DealershipDAO dealershipDAO;

	public Dealership() {
		dealershipDAO = new DealershipDAO();
		bufferAllInfo();
	}

	public Dealership(String name, String location, int capacity) {
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		dealershipDAO = new DealershipDAO();
		dealershipDAO.insertDealership(this);
	}

	public boolean exists() {
		return dealershipDAO.exists();
	}

	public void bufferAllInfo() {
		Dealership dealership = dealershipDAO.getDealership();
		if (dealership != null) {
			this.name = dealership.getName();
			this.location = dealership.getLocation();
			this.capacity = dealership.getCapacity();
		}
	}

	public String[] getInfoGUI() {
		String[] info = new String[3];
		info[0] = name;
		info[1] = location;
		info[2] = Integer.toString(capacity);
		return info;
	}

	public void delete() {
		dealershipDAO.deleteDealership();
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getCapacity() {
		return capacity;
	}
}
