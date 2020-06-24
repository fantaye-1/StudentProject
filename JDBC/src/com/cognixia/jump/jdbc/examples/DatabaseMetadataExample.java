package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetadataExample {

	public static void main(String[] args) {

			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
				DatabaseMetaData dbmd = conn.getMetaData();
				
				ResultSet rs = dbmd.getTables("university", "public", "%", new String[] {"TABLE"});
				
				while(rs.next()) {
					System.out.println(rs.getString("TABLE_NAME"));
				}
				
				rs.close();
				
				System.out.println("\n-------\n");
				ResultSet dbs = dbmd.getCatalogs();
				while(dbs.next()) {
					System.out.println(dbs.getString(1));
				}
				
				System.out.println("\n-------\n");
				System.out.println("Driver name: " + dbmd.getDriverName());
				System.out.println("Driver version: " + dbmd.getDriverVersion());
				System.out.println("User name: " + dbmd.getUserName());
				
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
