package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class StatementExample {
	
	public static void main(String[] args) {
		
		try {
			// create connection
			Connection conn = ConnectionManager.getConnection();
			
			// create statement
			Statement stmt = conn.createStatement();
			System.out.println("Statement created");
			
			// query passed to statement to execute
			int count =  stmt.executeUpdate("update student set credits = 82 where student_id = 10000");
			
			System.out.println("Rows updated: " + count);
			
			// try to do insert and delete from table student
			
			count = stmt.executeUpdate("insert into student values(null,'first', 'last', 'M', '2020-01-30', 30, 10000, 10000 )");
			
			System.out.println("Inserted rows: " + count);
			
			count = stmt.executeUpdate("delete from student where first_name = 'first'");
			
			System.out.println("Deleted rows: " + count);
			
			
			// close our connections
			stmt.close();
			conn.close();
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
