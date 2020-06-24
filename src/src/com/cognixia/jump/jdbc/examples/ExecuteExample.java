package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class ExecuteExample {
	
	public static void main(String[] args) {
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			
			Statement stmt = conn.createStatement();
			
			// execute() -> returns a boolean
			// true: a ResultSet object will be returned
			// false: no ResultSet object returned, some sort of update with data
			
			boolean b = stmt.execute("select * from student");
			
			if(b) {
				System.out.println("select statement returned true");
			}
			else {
				System.out.println("select statement returned false");
			}
			
			stmt.close();
			
			
			Statement stmt2 = conn.createStatement();
			
			b = stmt2.execute("update student set credits = 90 where student_id = 10000");
			
			// will update the student credits above, the false only indicates that 
			// no result set object will be returned
			if(b) {
				System.out.println("update statement returned true");
			}
			else {
				System.out.println("update statement returned false");
			}
			
			stmt2.close();
			
			
			
			Statement stmt3 = conn.createStatement();
			
			b = stmt3.execute("create table temp ( num int )");
			
			// returns false
			if(b) {
				System.out.println("create table statement returned true");
			}
			else {
				System.out.println("create table statement returned false");
			}
			
			
			conn.close();
			
		} catch( SQLException e) {
			e.printStackTrace();
		}
		
	}

}
