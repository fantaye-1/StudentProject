package com.cognixia.jump.jdbc.project;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImp implements StudentDAO {

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addStudent(Student student) {
		// insert into student values(id, fname, lname, gender, dob, credits, address_id, dept_id);
		
		// grab the id for department
		int deptId = student.getDept().getId();
		
		// grab id for address
		int addressId = student.getAddress().getId();
		
		// add in values to statement
		
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		// id, fname, lname, gender, dob, credits, addr_id, dept_id);
		// credits was missing from statement -TK
		try (PreparedStatement sstmt = conn.prepareStatement("update student set first_name = ?, last_name = ?,"
				+ " gender = ?, date_of_birth =?, credits =?, address_id=?, dept_id=? where student_id = ?");) {

			// this was marked as 9 and I changed to 8, in case wondering of errors -TK
			sstmt.setInt(8, student.getId());

			sstmt.setString(1, student.getFirstName());
			sstmt.setString(2, student.getLastName());
			// was missing line for gender and so rest of numbers were off, changed -TK
			sstmt.setString(3, student.getGender());
			sstmt.setDate(4, student.getDob());
			sstmt.setInt(5, student.getCredits());
			sstmt.setInt(6, student.getAddress().getId());
			// grab the id for the address // int addrId = student.getAddress().getId();
//					AddressDAOImp addr = new AddressDAOImp();
//					addr.updateAddress(student.getAddress().getId());

			// grab the id for the department // int deptId = student.getDept().getId();
			// this was 6 and should be 7 -TK
			sstmt.setInt(7, student.getDept().getId());
			int update = sstmt.executeUpdate();

			if (update > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
