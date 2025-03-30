package persistance;


import carDealership.Car;
import carDealership.Motorcycle;
import carDealership.Vehicle;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;



public class VehicleDAO implements DAOInterface<Vehicle> {
	private int count = 0;
	
    public VehicleDAO() {
        createTable();
    }

    // Create the table if it does not exist
    private void createTable() {
        String vehiclesSQL = "CREATE TABLE IF NOT EXISTS vehicles (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "model text NOT NULL, make text NOT NULL, color text NOT NULL, year INTEGER, price INTEGER, carType String, handleBarType String)";
				
        // carType and handlebarType can be null because they are only used for cars and
        // motorcycles respectively
        try {
            DBManager.getInstance().runInsert(vehiclesSQL);
        } catch (SQLException e) {
            e.printStackTrace();
			//System.out.println("error occured");
        }
    }
	
    @Override
    public boolean insert(Vehicle vehicle) {
        try {
				
			String query = "INSERT INTO vehicles (make, model, color, year, price,carType, handlebarType, inInventory) VALUES ('"
                    + vehicle.getMake() + "', '"
                    + vehicle.getModel() + "', '"
                    + vehicle.getColor() + "', "
                    + vehicle.getYear() + ", "
                    + vehicle.getPrice() + ", '"
                    + (vehicle instanceof Car ? ((Car) vehicle).getType() : null) + "', '"
                    + (vehicle instanceof Motorcycle ? ((Motorcycle) vehicle).getHandlebarType() : null) + "', '"
                    + vehicle.getInInventory()
                    + "');";
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Vehicle> getAll() {
        try {
            String query = "SELECT * FROM vehicles";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Vehicle> vehicles = new ArrayList<>();
			while (resultSet.next()) {
                String carType = resultSet.getString("carType");
                String handlebarType = resultSet.getString("handlebarType");
				
                if (carType != null) {
                    vehicles.add(new Car(
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("year"),
                            resultSet.getInt("price"),
                            resultSet.getString("carType")));
							
                } else if (handlebarType != null) {
                    vehicles.add(new Motorcycle(
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("price"),
                            resultSet.getString("handlebarType")));
                }
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
	
	
	
	public int getTotalVehiclesInInventory(){
		count = 0;

		try{
			
			ResultSet rs3 = DBManager.getInstance().runQuery("SELECT COUNT(*) AS count FROM vehicles");
			while(rs3.next()){
				this.count = rs3.getInt("count");
				
				}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	
		return count;
	}
	
	public String[][] getAllDisplayInfo(){
		String[][] displayInfo = new String[count][];
		try {
            String query = "SELECT * FROM vehicles";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Vehicle> vehicles = new ArrayList<>();
			int x = 0;
			while (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				String make = resultSet.getString("make");
				String model = resultSet.getString("model");
				String color = resultSet.getString("color");
				int year = resultSet.getInt("year");
                int price = resultSet.getInt("price");
                String carType = resultSet.getString("carType");
				String handleBarType = resultSet.getString("handleBarType");
				String[] vehicle = {make, model, color, model, Integer.toString(year), Integer.toString(price), carType, handleBarType}; 
				displayInfo[x] = vehicle;
				x++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
        return displayInfo;      
	}
    // extra method for changing the inInventory status of a vehicle to false
    // when it is sold
    public static boolean removeFromInventory(int id) {
        try {
            String query = "UPDATE vehicles SET inInventory = FALSE WHERE id = " + id;
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static String[] getAllMakes(){
		//return list of allMakes with no duplicates
		//System.out.println("allmakes");
		String[] makes = {"Honda", "Mazda"};
		return(makes);
	}
	
	public static String[] getAllModels(){
		//return list of all models with no duplicates
		String[] models = {"Accord", "Something"};
		return(models);
	}
	
	public static String[] getAllColors(){
		//return list of all colors with no duplicates
		String[] colors = {"Red", "Blue"};
		return(colors);
	}
	
	public static int getMinYear(){
		//return year of oldest vehicle
		int minyear = 1960;
		return(minyear);
	}
	
	public static int getMaxYear(){
		//return year of oldest vehicle
		int maxyear = 2025;
		return(maxyear);
	}
	
	public static int getMinPrice(){
		//return Price of oldest vehicle
		int minPrice = 1960;
		return(minPrice);
	}
	
	public static int getMaxPrice(){
		//return Price of oldest vehicle
		int maxPrice = 2025;
		return(maxPrice);
	}
	
	public boolean exsist(int id){
		boolean exsists = false;
		
		try {
			String query = "SELECT * FROM vehicles WHERE ID = " + id + "";
			ResultSet resultSet = DBManager.getInstance().runQuery(query);
			while (resultSet.next()){ 
				exsists = true;
			}
		}catch(SQLException e){e.printStackTrace();}
		
		return exsists;
	}

	public static String[] getIndexFromId(int id){
		String[] vehicle = new String[7];
		try {
            String query = "SELECT * FROM vehicles WHERE ID = " + id + "";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
			while (resultSet.next()){ 
				vehicle[0] = resultSet.getString("make");
				vehicle[1] = resultSet.getString("model");
				vehicle[2] = resultSet.getString("color");
				vehicle[3] = Integer.toString(resultSet.getInt("year"));
                vehicle[4] = Integer.toString(resultSet.getInt("price"));
                if(resultSet.getString("carType") != null){
					vehicle[5] = resultSet.getString("carType");
					vehicle[6] = "car";
				}else{
					vehicle[5] = resultSet.getString("handleBarType");
					vehicle[6] = "motorcycle";
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
        return vehicle;      
	
	}
	
	public boolean editCar(String[] car){
		int id = Integer.valueOf(car[0]);
		//INSERT INTO vehicles (make, model, color, year, price,carType, handlebarType, inInventory) VALUES ('"
		
		try{
            String query = "UPDATE vehicles SET make = '" + car[1] + "', model = '" + car[2] + "', color = '" + car[3] + "',year = " + Integer.valueOf(car[4]) + ", price = " + Integer.valueOf(car[5]) + ", carType = '" + car[6] + "' WHERE id = " + id;
			//+", model = " + car[2] + ", color = " + car[3] + ",year = " + Integer.valueOf(car[4]) + ", price = " + Integer.valueOf(car[5]) + ", carType = " + car[6] + " WHERE id = " + id;
            DBManager.getInstance().runInsert(query);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean editMotorcycle(String[] moto){
		int id = Integer.valueOf(moto[0]);
		//INSERT INTO vehicles (make, model, color, year, price,motoType, handlebarType, inInventory) VALUES ('"
		
		try{
            String query = "UPDATE vehicles SET make = " + moto[1] +", model = " + moto[2] + ", color" + moto[3] + ",year = "  
							+ Integer.valueOf(moto[4]) + ", price = " + Integer.valueOf(moto[5]) + ", handleBarType = " + moto[6] + " WHERE id = " + id;
            DBManager.getInstance().runInsert(query);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	
}
 