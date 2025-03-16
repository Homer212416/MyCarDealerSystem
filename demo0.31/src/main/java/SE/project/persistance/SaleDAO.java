package SE.project.persistance;

import SE.project.carDealership.Sale;
import SE.project.carDealership.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SaleDAO {

    private VehicleDAO vehicleDAO;

    public SaleDAO() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void insertSale(Sale sale) {
        try {
            String query = "INSERT INTO sales (vehicleId, buyerName, buyerContact, saleDate) VALUES ("
                    + sale.getVehicle().getId() + ", '"
                    + sale.getBuyerName() + "', '"
                    + sale.getBuyerContact() + "', '"
                    + sale.getSaleDate() + "');";
            DBManager.getInstance().runInsert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSale(int id) {
        try {
            String query = "DELETE FROM sales WHERE id = " + id;
            DBManager.getInstance().runInsert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sale getSale(int id) {
        try {
            String query = "SELECT * FROM sales WHERE id = " + id;
            ResultSet resultSet = DBManager.getInstance().runQuery(query);
            if (resultSet.next()) {
                Vehicle vehicle = vehicleDAO.getVehicleById(resultSet.getInt("vehicleId"));
                return new Sale(
                        vehicle,
                        resultSet.getString("buyerName"),
                        resultSet.getString("buyerContact"),
                        LocalDate.parse(resultSet.getString("saleDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
