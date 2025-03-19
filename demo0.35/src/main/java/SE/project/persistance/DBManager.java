package SE.project.persistance;

import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	private static DBManager m_dbManager;
	private String m_dbPath;
	private Connection m_connection;

	// singleton pattern, private constructor
	private DBManager() throws SQLException {
		initDB();
	}

	// lazy instantiation
	public static DBManager getInstance() throws SQLException {
		if (m_dbManager == null) {
			m_dbManager = new DBManager();
		}
		return m_dbManager;
	}

	private void initDB() throws SQLException {
		m_dbPath = java.nio.file.Paths.get(System.getProperty("user.home"), "dealership.sqlite3").toString();

		var fileSystem = FileSystems.getDefault();
		m_dbPath = fileSystem.getPath(System.getProperty("user.home"), "dealership.sqlite3").toString();

		var url = "jdbc:sqlite:" + m_dbPath;
		try {
			// for SQLite, if the URL does not exist, it will create a new database
			m_connection = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			m_connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// for inserting, creating tables
	public int runInsert(String sql) throws SQLException {
		System.out.println("Will run insert: " + sql);
		var stmt = m_connection.createStatement();
		int result = stmt.executeUpdate(sql);
		m_connection.commit();
		return result;
	}

	// for selecting
	public ResultSet runQuery(String sql) throws SQLException {
		System.out.println("Will run query: " + sql);
		var stmt = m_connection.createStatement();
		return stmt.executeQuery(sql);
	}

	// -----------------------------------------------------------------
	// test method
	public static void main(String[] args) {
		try {
			DBManager.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DBManager.getInstance().runInsert("CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY, name TEXT);");
			DBManager.getInstance().runInsert("INSERT INTO test (name) VALUES ('test');");
			ResultSet rs = DBManager.getInstance().runQuery("SELECT * FROM test;");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " Name: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
