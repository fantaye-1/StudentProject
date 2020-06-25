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
			
			
			// execute() will return a boolean
			// true -> a ResultSet obj will be returned
			// false -> no ResultSet obj; some sort of update w/ data
			
			boolean b = stmt.execute("select * from student");
			
			if(b) {
				System.out.println("Select statment returned true");
			}else {
				System.out.println("Select statement returned false");
			}
			
			stmt.close();
			
			Statement stmt2 = conn.createStatement();
			b = stmt2.execute("update student set credits = 90 where student_id = 10000");
			
			// will update the student credits above; false only indicates that no ResultSet obj is returned
			if(b) {
				System.out.println("Update statment returned true");
			}else {
				System.out.println("Update statement returned false");
			}
			
			stmt2.close();

			Statement stmt3 = conn.createStatement();
			b = stmt3.execute("create table temp ( num int )");
			
			// returns false when creating b/c no ResultSet
			if(b) {
				System.out.println("create table statment returned true");
			}else {
				System.out.println("create table	 statement returned false");
			}
			
			conn.close();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
