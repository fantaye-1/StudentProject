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
			int count = stmt.executeUpdate("update student set credits = 85 where student_id = 10000");
			System.out.println("Rows updated: " + count);
			
			
			// add a new student to student table, then remove them
			count = stmt.executeUpdate("insert into student(student_id, first_name, last_name, gender, date_of_birth, credits, address_id, dept_id)"
					+ " values(null, 'NewStudent', 'StudentAdd', 'M', '2000-09-08', 18, 10000, 10000)");
			System.out.println("Rows inserted: " + count);
			
			count = stmt.executeUpdate("delete from student where first_name = 'NewStudent'");
			System.out.println("Deleted rows: " + count);
			
			// close connections (both statement and connection)
			stmt.close();
			conn.close();
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}
	}

}
