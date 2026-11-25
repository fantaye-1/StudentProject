package com.cognixia.jump.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerProperties {

private static Connection connection = null;
	
	private static void makeConnection() {
		
		// env vars DB_URL / DB_USERNAME / DB_PASSWORD override resources/config.properties
		DatabaseConfig config = DatabaseConfig.fromEnvironment();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
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
		
		Connection conn = ConnectionManagerProperties.getConnection();
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
