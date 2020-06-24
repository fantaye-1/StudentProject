package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class ResultSetExample {
	
	public static void main(String[] args) {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from student");
			
			while(rs.next()) {
				
				int id = rs.getInt("student_id");
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Date dob = rs.getDate("date_of_birth");
				
				System.out.println("id = " + id + ", name = " + firstName + " " + lastName 
						+ ", dob = " + dob);
			}
			
			rs.close();
			stmt.close();
			
			
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery("select * from department");
			
			System.out.println("\n\nDEPARTMENTS:");
			System.out.println("------------------------");
			while(rs2.next()) {
				
				int id = rs2.getInt("dept_id");
				String name = rs2.getString("dept_name");
				String phone = rs2.getString("dept_phone");
				
				System.out.println("id = " + id + ", name = " + name + ", phone = " + phone);
				
			}
			
			ResultSet rs3 = stmt2.executeQuery("select * from address");
			
			
			stmt2.close();
			rs2.close();
			
			
			
			
			conn.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
