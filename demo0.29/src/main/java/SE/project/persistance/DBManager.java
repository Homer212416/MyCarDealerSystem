package SE.project.persistance;

import java.io.File;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	// the only instance of DBManager is stored as a static field
	private static DBManager m_dbManager;

	private String m_dbPath;
	private Connection m_connection;

	// constructor
	private DBManager() throws SQLException {
		// var fileSystem = FileSystems.getDefault();
		// m_dbPath = fileSystem.getPath(System.getProperty("user.home"),
		// "dealership.sqlite3").toString();
		// initialization of the fileSystem and m_dbPath is moved to initDB() for higher
		// cohesion
		initDB(); // initialization of the database is called
	}

	// lazy instantiation, instantiate the DBManager when getInstance is called
	// return the only instance
	public static DBManager getInstance() throws SQLException {
		if (m_dbManager == null) { // if the instance is not created
			m_dbManager = new DBManager(); // constructor is called
		}
		return m_dbManager; // return the instance
	}

	// initialization of the database
	private void initDB() throws SQLException {

		var fileSystem = FileSystems.getDefault();
		m_dbPath = fileSystem.getPath(System.getProperty("user.home"), "dealership.sqlite3").toString();

		// var dbFile = new File(m_dbPath);
		// var mustCreateTables = !dbFile.exists();

		var url = "jdbc:sqlite:" + m_dbPath;
		try {
			// DriverManager.getConnection():
			// If the database file specified by m_dbPath does not exist, SQLite will create
			// a new database file at that location.
			// if the database file already exists, a connection to the database is
			// established
			m_connection = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			m_connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// if (!mustCreateTables) {
		// System.out.println("DB file " + m_dbPath + " already exists. Not creating the
		// database.");
		// } else {
		// System.out.println("Creating the DB file " + m_dbPath + " and the tables.");
		// createTables();
		// }
		// do not need to check if the dbfile exists because the dbfile is created in
		// the
		// DriverManager.getConnection()
		createTables();
		System.out.println("Creating the DB file " + m_dbPath + " and the tables.");
	}

	public void runInsert(String query) throws SQLException {
		System.out.println("Will run insert query: " + query);
		var stmt = m_connection.createStatement();
		stmt.execute(query);
		m_connection.commit();
		System.out.println("Insert query executed successfully");
	}

	public ResultSet runQuery(String query) throws SQLException {
		System.out.println("Will run query: " + query);
		var stmt = m_connection.createStatement();
		return stmt.executeQuery(query);
	}

	private void createTables() throws SQLException {
		System.out.println("Creating the dealerships table");
		var dealershipSQL = "CREATE TABLE IF NOT EXISTS dealerships ("
				+ "name TEXT PRIMARY KEY, "
				+ "location TEXT NOT NULL, "
				+ "capacity INTEGER NOT NULL);";

		var stmt = m_connection.createStatement();
		stmt.execute(dealershipSQL);

		var userSQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL, "
				+ "passWord TEXT NOT NULL, "
				+ "roleId INTEGER);";

		stmt.execute(userSQL);

		var roleSQL = "CREATE TABLE IF NOT EXISTS roles (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "role TEXT NOT NULL);";

		stmt.execute(roleSQL);

		var addAdminRoleSQL = "INSERT INTO roles (role) VALUES ('Admin');";
		stmt.execute(addAdminRoleSQL);

		var addManagerRoleSQL = "INSERT INTO roles (role) VALUES ('Manager');";
		stmt.execute(addManagerRoleSQL);

		var addSalesPersonRoleSQL = "INSERT INTO roles (role) VALUES ('Salesperson');";
		stmt.execute(addSalesPersonRoleSQL);

		m_connection.commit();

	}

}
