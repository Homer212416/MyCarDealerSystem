package carDealership;

import persistance.*;
import carDealership.Car;
import carDealership.Motorcycle;
import carDealership.Vehicle;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PopulateData{
	
	public PopulateData(){
		try{
			User u = new User("Jamie", "Betts", "Admin", "JamieBetts@email.com", UserLayer.hashPassword("JamieBetts"));
			new User("Julie", "Nelson", "Manager", "JulieNelson@email.com", UserLayer.hashPassword("JulieNelson"));
			new User("Sam", "Reeve", "Manager", "SamReeve@email.com", UserLayer.hashPassword("SamReeve"));
			new User("Michelle", "Stewart", "Admin", "MichelleStewart@email.com", UserLayer.hashPassword("MichelleStewart"));
			new User("Milo", "Bradshaw", "Salesperson", "MiloBradshaw@email.com", UserLayer.hashPassword("MiloBradshaw"));
			new User("Earl", "Storms", "Salesperson", "EarlStorms@email.com", UserLayer.hashPassword("EarlStorms"));
			new User("Andrea", "Preble", "Salesperson", "AndreaPreble@email.com", UserLayer.hashPassword("AndreaPreble"));
			new User("Sandi", "Smith", "Salesperson", "SandiSmith@email.com", UserLayer.hashPassword("SandiSmith"));
			new User("Margaret", "Brown", "Salesperson", "MargaretBrown@email.com",UserLayer.hashPassword( "MargaretBrown"));
			new User("Michael", "Greene", "Salesperson", "MichaelGreene@email.com", UserLayer.hashPassword("MichaelGreene")); 
			
			VehicleDAO v = new VehicleDAO();
			v.addCar("Toyota","Carolla","Black",2012,6756,"Sedan");
			v.addCar("Toyota","Yaris","Blue",2007,6988,"Sedan");
			v.addCar("Mazda","Mazda3","Grey",2014,7495,"Sedan");
			v.addCar("Ford","C-MAX hybrid","Grey",2014,8046,"Hatchback");
			v.addCar("Mazda","Mazda3","Grey",2014,7495,"Sedan");
			v.addCar("Ford","C-MAX hybrid","Grey",2014,8046,"Hatchback");
			v.addCar("Honda","Civic Coupe","Blue",2014,7998,"Coupe");
			v.addCar("Honda","Civic","Grey",2007,7998,"Sedan");
			v.addCar("Subaru","XV Crosstrek","Green",2013,7998,"Hatchback");
			v.addCar("Hyundai","Elantra GT","Silver",2016,8077,"Hatchback");
			v.addCar("Lexus","RC 300","Silver",2024,52998,"Coupe");
			v.addCar("Nissan","Z","Black",2024,55593,"Coupe");
			v.addCar("Nissan","Versa","Blue",2024,21587,"Sedan");
			v.addCar("Nissan","Versa","Blue",2024,21587,"Sedan");
			v.addCar("Kia","Forte","White",2024,23184,"Sedan");
			v.addCar("Kia","Soul","Black",2025,25434,"Hatchback");
			v.addCar("Toyota","Tundra 4X4","Green",2025,82396,"Pickup");
			v.addMotorcycle("Suzuki","GSX-S 750","Black",2015,5995, "Motocross");
			v.addMotorcycle("Honda","REBEL 500 ABS","Grey",2023,7495, "Tracker");
			v.addMotorcycle("Suzuki","Boulevard C90","Silver",2006,3995, "Drag");
			v.addMotorcycle("Harley-Davidson","XL1200CX Roadster","Yellow",2018,9495, "Z-bar");
			v.addMotorcycle("Honda","Monkey","Orange",2024,5495, "Z-bar");
			v.addMotorcycle("Honda","SCL500","Black",2023,6495, "Moustache");
			v.addMotorcycle("Ducati","Supersport","Red",2024,19495, "Keystone");
			v.addMotorcycle("Ducati","Monster","Red",2018,8395, "Clubman");
			v.addMotorcycle("Honda","GL1800 Gold Wing","Red",2005,7495, "Ape Hanger");
			v.addMotorcycle("Suzuki","VL800 Volusia","Blue",2002,1995, "Chumps");
			v.addMotorcycle("Honda","GL1500CF Valkyrie Interstate","Red",2000,4995, "Ape Hanger");
			v.addMotorcycle("Honda","VF750C2 Magna Deluxe","White",1997,1995, "Ape Hanger");
			
			SaleDAO s = new SaleDAO();  
			s.insert(1,"Christina Smith","ChristinaSmith@email.com");
			s.insert(2,"Annette Willson","AnnetteWillson@email.com");
			s.insert(3,"Michelle Keen","MichelleKeen@email.com");
			s.insert(4,"Yasmin Rice","YasminRice@email.com");
			v.sellVehicle(1);
			v.sellVehicle(2);
			v.sellVehicle(3);
			v.sellVehicle(4);
		}catch(SQLException e){}
	
	
	}
	
	public static void main(String[] args){
		PopulateData pop = new PopulateData();
	}

	
}
/*
INSERT INTO vehicles(id,make,model,color,year,price,carType,handlebarType,inInventory) VALUES
 (NULL,"Toyota","Carolla","Black",2012,6756,"Sedan",NULL,"false")
,(NULL,
,(NULL,,NULL,"false")
,(NULL,,NULL,"false")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
,(NULL,,NULL,"true")
v.addMotorcycle("Suzuki","GSX-S 750","Black",2015,5995, "Motocross");
v.addMotorcycle("Honda","REBEL 500 ABS","Grey",2023,7495, "Tracker");
v.addMotorcycle("Suzuki","Boulevard C90","Silver",2006,3995, "Drag");
v.addMotorcycle("Harley-Davidson","XL1200CX Roadster","Yellow",2018,9495, "Z-bar");
v.addMotorcycle("Honda","Monkey","Orange",2024,5495, "Z-bar");
v.addMotorcycle("Honda","SCL500","Black",2023,6495, "Moustache");
v.addMotorcycle("Ducati","Supersport","Red",2024,19495, "Keystone");
v.addMotorcycle("Ducati","Monster","Red",2018,8395, "Clubman");
v.addMotorcycle("Honda","GL1800 Gold Wing","Red",2005,7495, "Ape Hanger");
v.addMotorcycle("Suzuki","VL800 Volusia","Blue",2002,1995, "Chumps");
v.addMotorcycle("Honda","GL1500CF Valkyrie Interstate","Red",2000,4995, "Ape Hanger");
v.addMotorcycle("Honda","VF750C2 Magna Deluxe","White",1997,1995, "Ape Hanger");


(2,"Annette Willson","AnnetteWillson@email.com","2025-02-15");
(3,"Michelle Keen","MichelleKeen@email.com","2025-03-23");
(4,"Yasmin Rice","YasminRice@email.com","2025-03-25");
*/