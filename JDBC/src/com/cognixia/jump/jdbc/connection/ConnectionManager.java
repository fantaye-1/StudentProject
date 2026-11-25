package com.cognixia.jump.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// credentials come from DB_URL / DB_USERNAME / DB_PASSWORD or resources/config.properties
	private static final DatabaseConfig CONFIG = DatabaseConfig.fromEnvironment();
	
	// connect to database method
	public static Connection getConnection() {
		
		Connection conn = null;
		
		
		try { 
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // just in case Driver isn't being loaded
			conn = DriverManager.getConnection(CONFIG.getUrl(), CONFIG.getUsername(), CONFIG.getPassword());
			System.out.println("Connected");
			
		} catch (SQLException e) {
			System.out.println("Could not connect");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		
		Connection conn = ConnectionManager.getConnection();
		
		// run some code...
		
		try {
			conn.close();
			System.out.println("Connection was closed");
		} catch (SQLException e) {
			System.out.println("Couldn't close connection");
		}
	}
}
