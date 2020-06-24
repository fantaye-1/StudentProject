package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetadataExample {
	
	public static void main(String[] args) {
		
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?serverTimezone=EST5EDT", "root", "Root@123");
			
			DatabaseMetaData dbmd = conn.getMetaData();
			
			ResultSet rs = dbmd.getTables("university", "public", "%", new String[] { "TABLE" });
			
			// get all table names in university database
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			rs.close();
			
			
			
			System.out.println("\n---------------\n");
			
			ResultSet dbs = dbmd.getCatalogs();
			
			while(dbs.next()) {
				System.out.println(dbs.getString(1));
			}
			dbs.close();
			
			
			System.out.println("\n---------------\n");
			
			// many different getter methods for info on your connection
			System.out.println("Driver name: " + dbmd.getDriverName());
			System.out.println("Driver version: " + dbmd.getDriverVersion());
			System.out.println("Username: " + dbmd.getUserName());
			
			
			
			
			
			
			conn.close();
			
		} catch(SQLException e) {
			
		}
		
		
	}

}
