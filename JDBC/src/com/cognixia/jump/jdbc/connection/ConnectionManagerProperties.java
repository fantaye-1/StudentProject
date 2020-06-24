package com.cognixia.jump.jdbc.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerProperties {

private static Connection connection = null;
	
	private static void makeConnection() {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String url = props.getProperty("url");
		String userName = props.getProperty("username");
		String password = props.getProperty("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
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
