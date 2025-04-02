package persistance;

import carDealership.Sale;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleDAO implements DAOInterface<Sale> {

    public SaleDAO() {
        createTable();
    }

    private void createTable() {
        String saleSQL = "CREATE TABLE IF NOT EXISTS sales ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "vehicleId INTEGER NOT NULL, "
                + "buyerName TEXT NOT NULL, "
                + "buyerContact TEXT NOT NULL, "
                + "saleDate TEXT NOT NULL);";
        try {
            DBManager.getInstance().runInsert(saleSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Sale sale) {
        try {
            String query = "INSERT INTO sales (vehicleID, buyerName, buyerContact, saleDate) VALUES ('"
                    + sale.getVehicleID() + "', '"
                    + sale.getBuyerName() + "', '"
                    + sale.getBuyerContact() + "', '"
                    + sale.getSaleDate().toString() + "');";
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
    public boolean insert(int ID, String buyerName, String buyerContact) {
        LocalDate date = LocalDate.now();
		try {
            String query = "INSERT INTO sales (vehicleID, buyerName, buyerContact, saleDate) VALUES ("
                    + ID + ", '"
                    + buyerName + "', '"
                    + buyerContact + "', '"
                    + date.toString() + "');";
            DBManager.getInstance().runInsert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
    @Override
    public ArrayList<Sale> getAll() {
        try {
            String query = "SELECT * FROM sales";
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            ArrayList<Sale> sales = new ArrayList<>();
            while (resultSet.next()) {
                sales.add(new Sale(
                        resultSet.getInt("vehicleId"),
                        resultSet.getString("buyerName"),
                        resultSet.getString("buyerContact"),
                        LocalDate.parse(resultSet.getString("saleDate"))
                ));
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void keepOnlyOneTestSale() {
        /*try {	
            String deleteQuery = "ELETE FROM sales
                WHERE id NOT IN (
                    SELECT id FROM sales
                    WHERE buyerName = 'Test Buyer' AND buyerContact = 'buyer@email.com'
                    ORDER BY id ASC
                    LIMIT 1
                );";
            DBManager.getInstance().runInsert(deleteQuery);
            System.out.println(" Cleaned up duplicate test sales.");
			
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}