package com.cognixia.jump.jdbc.project;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
