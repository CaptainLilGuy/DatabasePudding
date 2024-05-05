package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

	public Connection connection;
	public Statement statement;
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUserTable() {
		String query = "CREATE TABLE IF NOT EXISTS Users("
				+ "UserID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "Username VARCHAR(50) NOT NULL,"
				+ "Email VARCHAR(50) NOT NULL,"
				+ "Password VARCHAR(50) NOT NULL"
				+ ")";
		try {
			exec(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createMenuTable() {
		String query = "CREATE TABLE IF NOT EXISTS Menu("
				+ "KodeMenu VARCHAR(6) PRIMARY KEY,"
				+ "NamaMenu VARCHAR(50) NOT NULL,"
				+ "HargaMenu INT NOT NULL,"
				+ "StockMenu INT NOT NULL"
				+ ")";
		try {
			exec(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void migrateTables() {
		createUserTable();
		createMenuTable();
	}
	
	public void exec(String query) {
		try {
			statement.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
