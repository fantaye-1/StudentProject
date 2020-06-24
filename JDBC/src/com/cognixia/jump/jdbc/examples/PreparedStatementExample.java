package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class PreparedStatementExample {

	public static void main(String[] args) {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter student ID: ");
			int id = sc.nextInt();
			
			sc.close();
			
			// preparing the statement w/ our query
			PreparedStatement pstmt = conn.prepareStatement("select * from student where student_id = ?");
			
			// set params for the prepared statement
			pstmt.setInt(1, id);
			
			// get the result
			ResultSet rs = pstmt.executeQuery();
			
			// move result set point to first row
			boolean b = rs.next();
			
			if(b) {
				System.out.println("Student with id = " + id + " is " + rs.getString("first_name") + " " + rs.getString("last_name"));
			}else {
				System.out.println("No student with that id");
			}
			
			PreparedStatement pstmt2 = conn.prepareStatement("update student set credits = 50 where gender =? and credits=?");
			
			pstmt2.setString(1, "F");
			pstmt2.setInt(2, 42);
			
			int count = pstmt2.executeUpdate();
			System.out.println("Students updated: " + count);
			
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
