package com.cognixia.jump.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BetterConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/university"; // how to connect using Windows
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; // Windows: root

	private static Connection connection = null;
	
	private static void makeConnection() {
		
		try {
			// loads in driver in case it isn't loaded automatically
			Class.forName("com.mysql.cj.jdbc.Driver");
			// make connection
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		// we want Connection obj to be a Singleton
		// if isn't already established, create it
		if(connection == null) { 
			makeConnection();
		}
		return connection;
	}
	
	public static void main(String[] args) {
		
		Connection conn = BetterConnectionManager.getConnection();
		System.out.println("Connection made");
		
		try {
			conn.close();
			System.out.println("Closed connection");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
