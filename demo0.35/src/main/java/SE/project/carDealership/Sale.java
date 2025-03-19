package SE.project.carDealership;

import SE.project.persistance.DAOInterface;
import SE.project.persistance.SaleDAO;
import java.io.Serializable;

public class Sale implements Serializable {
	private static final long serialVersionUID = -7473395562909124471L;
	private int vehicleID;
	private String buyerName;
	private String buyerContact;
	private DAOInterface<Sale> saleDAO;

	public Sale(int vehicleID, String buyerName, String buyerContact) {
		this.vehicleID = vehicleID;
		this.buyerName = buyerName;
		this.buyerContact = buyerContact;

		this.saleDAO = new SaleDAO();
	}

	public int getVehicleID() {
		return vehicleID;
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

	public void save() {
		saleDAO.insert(this);
	}
}
