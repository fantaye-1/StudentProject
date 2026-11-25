package com.cognixia.jump.jdbc.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cognixia.jump.jdbc.dao.Department;

class StudentDAOImpTest {

	private Connection conn;
	private StudentDAOImp dao;

	@BeforeEach
	void setUp() throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:mem:student;MODE=MySQL;DB_CLOSE_DELAY=-1");
		try (Statement st = conn.createStatement()) {
			st.execute("drop table if exists student");
			st.execute("create table student (student_id int primary key, first_name varchar(50), "
					+ "last_name varchar(50), gender varchar(1), date_of_birth date, credits int, "
					+ "address_id int, dept_id int)");
		}
		dao = new StudentDAOImp(conn);
	}

	@AfterEach
	void tearDown() throws SQLException {
		conn.close();
	}

	private static Student student(int id, String firstName, int credits) {
		return new Student(id, firstName, "Fantaye", "M", Date.valueOf("2000-01-15"), credits,
				new Address(3, "123 Main St", "Dayton", "OH", "45402"),
				new Department(5, "Computer Science", "555-0100"));
	}

	@Test
	void addStudentStoresEveryColumnInTheRightPlace() throws SQLException {
		assertTrue(dao.addStudent(student(1, "Abrham", 30)));

		try (Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from student where student_id = 1")) {
			assertTrue(rs.next());
			assertEquals("Abrham", rs.getString("first_name"));
			assertEquals("Fantaye", rs.getString("last_name"));
			assertEquals("M", rs.getString("gender"));
			assertEquals(Date.valueOf("2000-01-15"), rs.getDate("date_of_birth"));
			assertEquals(30, rs.getInt("credits"));
			assertEquals(3, rs.getInt("address_id"));
			assertEquals(5, rs.getInt("dept_id"));
		}
	}

	@Test
	void addingADuplicateIdReturnsFalse() {
		assertTrue(dao.addStudent(student(1, "Abrham", 30)));
		assertFalse(dao.addStudent(student(1, "Other", 10)));
	}

	@Test
	void updateStudentChangesCredits() throws SQLException {
		dao.addStudent(student(1, "Abrham", 30));

		assertTrue(dao.updateStudent(student(1, "Abrham", 45)));

		try (Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select credits from student where student_id = 1")) {
			assertTrue(rs.next());
			assertEquals(45, rs.getInt(1));
		}
	}

	@Test
	void deleteStudentRemovesOnlyThatStudent() {
		dao.addStudent(student(1, "Abrham", 30));
		dao.addStudent(student(2, "Tara", 20));

		assertTrue(dao.deleteStudent(1));
		assertFalse(dao.deleteStudent(1));
	}
}
