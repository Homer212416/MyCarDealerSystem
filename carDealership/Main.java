package carDealership;

import java.util.Scanner;

import persistance.DealershipLayer;
import persistance.UserLayer;

import java.io.*;
import java.sql.SQLException;

public class Main {
	public static Scanner input = new Scanner(System.in);
	public static Dealership m_dealership;
	

	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		var dealership = new DealershipLayer();
		var users = new UserLayer();
		// TODO: Add a method in DBManager to tell if the database was just created and
		// use it here
		if (!dealership.existsAndSet()) {
			FirstLaunchPageController newPage = new FirstLaunchPageController();
		} else {
			//m_dealership = new Dealership(dealership.getNname(), dealership.getLocation(), dealership.getCapacity());
			loginPageController myFrame = new loginPageController();
		}
	}
	
	public static void createDealership(String name, String location, int capacity)
			throws IllegalCapacityException, SQLException {
		if (capacity < 1 || capacity > 100) {
			throw new IllegalCapacityException();
		}
		m_dealership = new Dealership(name, location, capacity);
	}
	
	public static void save() throws IOException {
		File saveFile = new File("save.data");
		FileOutputStream outFileStream = null;
		try {
			outFileStream = new FileOutputStream(saveFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObjectOutputStream outObjStream = null;
		try {
			outObjStream = new ObjectOutputStream(outFileStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		outObjStream.writeObject(m_dealership);
		outObjStream.close();

	}
}
