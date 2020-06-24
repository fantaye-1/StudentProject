package com.cognixia.jump.jdbc.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;
import com.cognixia.jump.jdbc.dao.DepartmentDAO;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class StudentDAOImp implements StudentDAO {

	// connection attribute to make access to db easier
		private Connection conn = ConnectionManagerProperties.getConnection();
		
	@Override
	public List<Student> getAllStudents() {
		
		List<Student> studentList = new ArrayList<>();
		
		// find all students
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Student"); ) {
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				Date dob = rs.getDate(5);
				int credits = rs.getInt(6);
				int address_id = rs.getInt(7);
				//TODO need to call the address get address method for @AddressDAOImp AND change the null
				AddressDAO address = new AddressDAOImp();
				//address.getAddressById(address_id);
				
				int dep = rs.getInt(8);
				//TODO need to call the department get department method for @departmentDAOImp AND change the null
				DepartmentDAO depa = new DepartmentDAOImp();
				depa.getDepartmentByID(dep);
				// add to list
				Student std = new Student(id, firstName, lastName, gender, dob, credits, address.getAddressById(address_id), depa.getDepartmentByID(dep));
				studentList.add(std);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return studentList;
	}

	@Override
	public Student getStudentById(int std_id) {
		
		Student std = null;
		
		// select * from department where dept_id = ?
		try(PreparedStatement sstmt = conn.prepareStatement("select * from student where student_id = ?")) {
			
			sstmt.setInt(1,std_id );
			
			ResultSet rs = sstmt.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				Date dob = rs.getDate(5);
				int credits = rs.getInt(6);
				int address_id = rs.getInt(7);				
				AddressDAO address = new AddressDAOImp();
				//address.getAddressById(address_id);				
				int dep = rs.getInt(8);			
				DepartmentDAO depa = new DepartmentDAOImp();
				//depa.getDepartmentByID(dep);
				
				std = new Student(id, firstName, lastName, gender, dob, credits, address.getAddressById(address_id), depa.getDepartmentByID(dep));
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return std;
	}
	
	@Override
	public boolean updateStudent(Student student) {
		//id, fname, lname, gender, dob, credits, addr_id, dept_id);
				try(PreparedStatement sstmt = conn.prepareStatement("update student set first_name = ?, last_name = ?,"
						+ " gender = ?, date_of_birth =?, address_id=?, dept_id=? where student_id = ?");) {
					
					sstmt.setInt(9, student.getId());
					
					sstmt.setString(1, student.getFirstName());
					sstmt.setString(2, student.getLastName());
					sstmt.setDate(3, student.getDob());
					sstmt.setInt(4, student.getCredits());
					sstmt.setInt(5, student.getAddress().getId());
					// grab the id for the address	//	int addrId = student.getAddress().getId();
//					AddressDAOImp addr = new AddressDAOImp();
//					addr.updateAddress(student.getAddress().getId());
					
					
					// grab the id for the department //	int deptId = student.getDept().getId();
					sstmt.setInt(6, student.getDept().getId());
//					DepartmentDAO depa = new DepartmentDAOImp();
//					depa.updateDepartment(student.getDept());				
					//student.getDept().updateDepartmentDA
					
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

	@Override
	public boolean addStudent(Student student) {
		
		// insert into student values(id, fname, lname, gender, dob, credits, addr_id, dept_id);
		
		// grab the id for the department
		int deptId = student.getDept().getId();
		
		// grab the id for the address
		int addrId = student.getAddress().getId();
		
		// add in your values to statement
		
		
		return false;
	}



	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
