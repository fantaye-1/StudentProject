package com.cognixia.jump.jdbc.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.jump.jdbc.connection.ConnectionManager;

public class PreparedStatementExercise {

	public static void main(String[] args) {
		// Create program where user can find students and credits earned by class (fresh, soph, etc.)
		// class is determined only by # of credits (see below chart in comments for range)
		// user can choose a class and get a list of students with their full name and # credits earned
		// use a prepared statement
		// at bottom, display total count of students for each class (also use PreparedStatement)
		/*
		 * Fresh: 0 to 30
		 * Soph: 30 to 60
			Junior: 60 to 90
			Senior: 90 +
		 */
		
		try {
			
			Connection conn = ConnectionManager.getConnection();
			Scanner input = new Scanner(System.in);
			System.out.println("Please choose a class standing to view students and their credits earned: ");
			System.out.println("1) Freshman  2) Sophomore  3) Junior  4) Senior");
			System.out.println("(Enter 1, 2, 3, or 4)");
			int classStanding = input.nextInt();
			
			String where = generateWhereClause(classStanding);
			generatePreparedStatement(where, conn);
			
			conn.close();
			main(args);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String generateWhereClause(int classStanding) {
		
		String credits = "";
		
		switch(classStanding) {
		case 1:
			credits = "where credits < 30";
			break;
		case 2:
			credits = "where credits >= 30 and credits < 60";
			break;
		case 3:
			credits = "where credits >= 60 and credits < 90";
			break;
		case 4:
			credits = "where credits >= 90";	
			break;
		}
		
		return credits;
	}
	
	public static void generatePreparedStatement(String whereClause, Connection conn) throws SQLException {
		
		String query = "select concat(first_name, \" \", last_name) as full_name,\r\n" + 
				"credits from student " + whereClause;
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		while(rs.next()) {
			String name = rs.getString("full_name");
			String credits = rs.getString("credits");
			count++;
			System.out.println("Name: " + name + ", Credits: " + credits);
		}
		System.out.println("Count: " + count);
		
	}
	
}
