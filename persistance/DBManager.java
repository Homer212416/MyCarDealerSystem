package persistance;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class DBManager {

	private static DBManager m_dbManager;

	private String m_dbPath;
	private Connection m_connection;

	private DBManager() throws SQLException {
		var fileSystem = FileSystems.getDefault();
		m_dbPath = fileSystem.getPath(System.getProperty("user.home"), "dealership.sqlite3").toString();

		initDB();
	}

	public void runInsert(String query) throws SQLException {
		System.out.println("Will run insert query: " + query);
		var stmt = m_connection.createStatement();
		stmt.execute(query);
		m_connection.commit();
	}

	public ResultSet runQuery(String query) throws SQLException {
		System.out.println("runQuery Method Ran");
		System.out.println("Will run query: " + query);
		var stmt = m_connection.createStatement();
		return stmt.executeQuery(query);
	}

	private void initDB() throws SQLException {
		var dbFile = new File(m_dbPath);
		var mustCreateTables = !dbFile.exists();
		var url = "jdbc:sqlite:" + m_dbPath;
		try {
			m_connection = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			m_connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (!mustCreateTables) {
			System.out.println("DB file " + m_dbPath + " already exists. Not creating the database.");
		} else {
			System.out.println("Creating the DB file " + m_dbPath + " and the tables.");
			createTables();
		}
	}

	private void createTables() throws SQLException {
		System.out.println("Creating the dealerships table");
		var dealershipSQL = "CREATE TABLE IF NOT EXISTS dealerships (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " name text NOT NULL, location text NOT NULL, capacity INTEGER);";

		var stmt = m_connection.createStatement();
		stmt.execute(dealershipSQL);

		var userSQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " name text NOT NULL, passWord text NOT NULL, roleId INTEGER);";

		stmt.execute(userSQL);
		
		
		var roleSQL = "CREATE TABLE IF NOT EXISTS roles (id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ " role text NOT NULL);";

		stmt.execute(roleSQL);
		
		var users = "CREATE TABLE IF NOT EXISTS usersInfo (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "firstName text NOT NULL, lastName text NOT NULL, jobTitle text not NULL, email text NOT NULL, password NOT NULL, editSecurity INTEGER NOT NULL, pageSecurity INTEGER NOT NULL)";
				
		stmt.execute(users);
		
		var addAdminRoleSQL = "INSERT INTO roles (role) VALUES ('Admin');";
		stmt.execute(addAdminRoleSQL);

		var addManagerRoleSQL = "INSERT INTO roles (role) VALUES ('Manager');";
		stmt.execute(addManagerRoleSQL);
		
		var addSalesPersonRoleSQL = "INSERT INTO roles (role) VALUES ('Salesperson');";
		stmt.execute(addSalesPersonRoleSQL);

		m_connection.commit();

	}

	public static DBManager getInstance() throws SQLException {
		if (m_dbManager == null) {
			m_dbManager = new DBManager();
		}

		return m_dbManager;
	}
	
	private void createUserTables() throws SQLException {
		var stmt = m_connection.createStatement();
		var users = "CREATE TABLE IF NOT EXISTS usersInfo (ID PRIMARY KEY AUTOINCREMENT,"
				+ "firstName text NOT NULL, lastName text NOT NULL, jobTitle text not NULL, email text NOT NULL, password NOT NULL)";
		//stmt.execute(users);
	}
	
	

}