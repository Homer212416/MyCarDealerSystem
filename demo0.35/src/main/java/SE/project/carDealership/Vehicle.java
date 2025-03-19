package SE.project.carDealership;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    private static final long serialVersionUID = -8537773978564014927L;
    protected int id;
    protected String make, model, color;
    protected int year;
    protected double price;
    protected boolean inInventory = true; // it is in the inventory by default

    public Vehicle(String make, String model, String color, int year, double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
    }

    // constructor only for the database to create a vehicle object
    public Vehicle(int id, String make, String model, String color, int year, double price, boolean inInventory) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.inInventory = inInventory;
    }

    // getters

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean getInInventory() {
        return inInventory;
    }

}
