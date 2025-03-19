package SE.project.carDealership;

import java.util.ArrayList;

import SE.project.persistance.DAOInterface;
import SE.project.persistance.DealershipDAO;

public class Dealership {
    private String name;
    private String location;
    private int capacity;
    private DAOInterface<Dealership> dealershipDAO;

    public Dealership() {
        dealershipDAO = new DealershipDAO();
        ArrayList<Dealership> infoList = dealershipDAO.getAll();
        name = infoList.get(0).getName();
        location = infoList.get(0).getLocation();
        capacity = infoList.get(0).getCapacity();
    }

    public Dealership(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        dealershipDAO = new DealershipDAO();
        save();
    }

    private void save() {
        dealershipDAO.insert(this);
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

    public boolean exists() {
        return DealershipDAO.exists();
    }

    public String[] displayAllInfo() {
        String[] info = new String[3];
        info[0] = name;
        info[1] = location;
        info[2] = Integer.toString(capacity);
        return info;
    }

    public boolean deleteDealership() {
        return DealershipDAO.deleteAll();
    }

}
