package SE.project.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SE.project.carDealership.Sale;

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
            String query = "INSERT INTO sales (vehicleID, buyerName, buyerContact) VALUES ('"
                    + sale.getVehicleID() + "', '"
                    + sale.getBuyerName() + "', '"
                    + sale.getBuyerContact() + "');";
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
                        resultSet.getString("buyerContact")));
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
