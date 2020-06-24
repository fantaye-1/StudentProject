package com.cognixia.jump.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BetterConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/university"; // Windows
	//private static final String URL = "jdbc:mysql://localhost:3306/university?serverTimezone=EST5EDT"; // Mac or Linux
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; // Windows: root, Mac: Root@123
	
	// the only connection we have, will always return this connection
	private static Connection connection = null;

	
	private static void makeConnection() {
		
		try {
			// loads in Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// make connection
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// returning connection
	public static Connection getConnection() {
		
		if(connection == null) { // if connection not established, create it before returning it
			makeConnection();
		}
		
		return connection;
	}
	
	
	public static void main(String[] args) {
		
		Connection conn = BetterConnectionManager.getConnection();
		System.out.println("Connection made");
		
		Connection conn2 = BetterConnectionManager.getConnection();
		
		try {
			conn.close();
			System.out.println("Closed connection");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
