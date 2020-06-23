package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class ResultSetMetadataExample {

	public static void main(String[] args) {
		
		try(Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from student")){
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			for(int col = 1; col < rsmd.getColumnCount(); col++) {
				String name = rsmd.getColumnName(col);
				String type = rsmd.getColumnTypeName(col);
				int typeNum = rsmd.getColumnType(col);
				
				System.out.println(col + ".) Name: " + name + " Type: " + type
						+ ", Type #: " + typeNum);
			}
			
			
		}catch(SQLException e) {
			
		}

	}

}
