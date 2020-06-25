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
			// create connection & statement
			Connection conn = ConnectionManager.getConnection();
			Statement stmt = conn.createStatement();
	
			ResultSet rs = stmt.executeQuery("select * from student");
			while(rs.next()) {
				
				int id = rs.getInt("student_id");
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Date dob = rs.getDate("date_of_birth");
				
				System.out.println("ID: " + id);
				System.out.println("First name: " + firstName);
				System.out.println("Last name: " + lastName);
				System.out.println("DOB: " + dob);
			}
			// close connections (both statement and connection)
			
			rs.close();
			stmt.close();
			
			// try to avoid reusing names if you don't close them
			Statement stmt2 = conn.createStatement();	
			//currently points to just before first row
			ResultSet rs2 = stmt2.executeQuery("select * from university.department");
			// then will move and point to each row when call .next()
			while(rs2.next()) {
				int id = rs2.getInt("dept_id");
				String name = rs2.getString("dept_name");
				String phone = rs2.getString("dept_phone");
				
				System.out.println("Id: " + id + " Name: " + name + " Phone: " + phone);
			}
			
			stmt2.close();
			
			Statement stmt3 = conn.createStatement();	
			ResultSet rs3 = stmt3.executeQuery("select concat(first_name, \" \", last_name) as full_name,\r\n" + 
					"DATE_FORMAT(FROM_DAYS(DATEDIFF(now(),date_Of_birth)), '%Y')+0 AS age\r\n" + 
					"from student");
		
			System.out.println("STUDENTS\n---------------\n");
			while(rs3.next()) {
				String name = rs3.getString("full_name");
				int age = rs3.getInt("age");
				System.out.println("Name: " + name + ", Age: " + age);
			}
			
			rs3.close();
			stmt3.close();
			conn.close();
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}

	}

}
