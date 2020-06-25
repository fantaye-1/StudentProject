package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;
import com.cognixia.jump.jdbc.dao.DepartmentDAO;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;

public class StudentDAOImp implements StudentDAO {

	private Connection conn = ConnectionManagerProperties.getConnection();
	
	// Abrham
	@Override
	public List<Student> getAllStudents() {
		List<Student> studentList = new ArrayList<>();

		// find all students
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("select * from Student");) {

			while (rs.next()) {

				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				Date dob = rs.getDate(5);
				int credits = rs.getInt(6);
				int address_id = rs.getInt(7);
				// TODO need to call the address get address method for @AddressDAOImp AND
				// change the null
				AddressDAO address = new AddressDAOImp();
				// address.getAddressById(address_id);

				int dep = rs.getInt(8);
				// TODO need to call the department get department method for @departmentDAOImp
				// AND change the null
				DepartmentDAO depa = new DepartmentDAOImp();
				depa.getDepartmentById(dep);
				// add to list
				Student std = new Student(id, firstName, lastName, gender, dob, credits,
						address.getAddressById(address_id), depa.getDepartmentById(dep));
				studentList.add(std);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return studentList;
	}

	// Abrham
	@Override
	public Student getStudentById(int std_id) {
		Student std = null;

		// select * from department where dept_id = ?
		try (PreparedStatement sstmt = conn.prepareStatement("select * from student where student_id = ?")) {

			sstmt.setInt(1, std_id);

			ResultSet rs = sstmt.executeQuery();

			if (rs.next()) {

				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				Date dob = rs.getDate(5);
				int credits = rs.getInt(6);
				int address_id = rs.getInt(7);
				AddressDAO address = new AddressDAOImp();
				// address.getAddressById(address_id);
				int dep = rs.getInt(8);
				DepartmentDAO depa = new DepartmentDAOImp();
				// depa.getDepartmentByID(dep);

				std = new Student(id, firstName, lastName, gender, dob, credits, address.getAddressById(address_id),
						depa.getDepartmentById(dep));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return std;
	}

	@Override
	public boolean addStudent(Student student) {
		try {
			
			PreparedStatement sstmt = conn.prepareStatement("insert "
				+ "into student values(?,?,?,?,?,?,?,?)");
			
			sstmt.setInt(1, student.getId());
			sstmt.setString(2, student.getFirstName());
			sstmt.setString(3, student.getLastName());
			// error: this should be gender -TK
			//sstmt.setString(4, student.getLastName());
			sstmt.setString(4, student.getGender());
			sstmt.setDate(5, student.getDob());
			sstmt.setInt(6, student.getCredits());
			sstmt.setInt(7, student.getAddress().getId());
			// error: this was 7, needs to be 8 -TK
			sstmt.setInt(8, student.getDept().getId());
			
			int insert = sstmt.executeUpdate();
			
			if(insert > 0) {
				return true;
			}
			
			sstmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return false;
	}

	// Abrham
	@Override
	public boolean updateStudent(Student student) {
		//id, fname, lname, gender, dob, credits, addr_id, dept_id);
		// credits was missing from statement -TK
		try(PreparedStatement sstmt = conn.prepareStatement("update student set first_name = ?, last_name = ?,"
				+ " gender = ?, date_of_birth =?, credits =?, address_id=?, dept_id=? where student_id = ?");) {
			
			// this was marked as 9 and I changed to 8, in case wondering of errors -TK
			sstmt.setInt(8, student.getId());
			
			sstmt.setString(1, student.getFirstName());
			sstmt.setString(2, student.getLastName());
			// was missing line for gender and so rest of numbers were off, changed -TK
			sstmt.setString(3,  student.getGender());
			sstmt.setDate(4, student.getDob());
			sstmt.setInt(5, student.getCredits());
			sstmt.setInt(6, student.getAddress().getId());
			// grab the id for the address	//	int addrId = student.getAddress().getId();
//			AddressDAOImp addr = new AddressDAOImp();
//			addr.updateAddress(student.getAddress().getId());
			
			
			// grab the id for the department //	int deptId = student.getDept().getId();
			// this was 6 and should be 7 -TK
			sstmt.setInt(7, student.getDept().getId());					
			int update = sstmt.executeUpdate();
			
			if(update > 0) {
				return true;
			}					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	// Abrham
	@Override
	public boolean deleteStudent(int id) {
		
		// error: statement had dept_id in where clause instead of student_id -TK
		try(PreparedStatement sstmt = conn.prepareStatement("delete from student where student_id = ?")) {

			sstmt.setInt(1, id);

			int deleted = sstmt.executeUpdate();

			if (deleted > 0) {
				return true;
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
