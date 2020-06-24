package com.cognixia.jump.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;

public class DepartmentDAOImp implements DepartmentDAO{

	// Connection attribute to make access to DB easier
	private Connection conn = ConnectionManagerProperties.getConnection();
	
	@Override
	public List<Department> getAllDepartments() {
		
		List<Department> deptList = new ArrayList<>();
		
		// find all Departments
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from department");) {
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				
				// add to List
				Department dept = new Department(id, name, phone);
				deptList.add(dept);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptList;
	}

	@Override
	public Department getDepartmentById(int id) {
		
		Department dept = null;
		// write query with spaces for params and pass to a PS
		try(PreparedStatement pstmt = conn.prepareStatement("select * from department where dept_id = ?");) {
			
			// set the params
			pstmt.setInt(1, id);
			// run the query w/ the PS
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int deptId = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				
				dept = new Department(deptId, name, phone);
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public Department getDepartmentByName(String name) {
		
		Department dept = null;
		// write query with spaces for params and pass to a PS
		try(PreparedStatement pstmt = conn.prepareStatement("select * from department where dept_name = ?");) {
			
			// set the params
			pstmt.setString(1, name);
			// run the query w/ the PS
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String deptName = rs.getString(2);
				String phone = rs.getString(3);
				
				dept = new Department(id, deptName, phone);
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public boolean addDepartment(Department dept) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into department values(?,?,?)");
			pstmt.setInt(1, dept.getId());
			pstmt.setString(2, dept.getName());
			pstmt.setString(3, dept.getPhone());
			
			int insert = pstmt.executeUpdate();
			if(insert > 0) {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteDepartment(int id) {
		
		try(PreparedStatement pstmt = conn.prepareStatement("delete from department where dept_id = ?");){
			
			pstmt.setInt(1, id);
			// should return num rows deleted
			int deleted = pstmt.executeUpdate();
			if(deleted > 0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDepartment(Department dept) {
		
		//update department set dept_name = ?, dept_phone = ? where dept_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("update department set dept_name = ?, dept_phone = ? where dept_id = ?");){
			
			pstmt.setString(1, dept.getName());
			pstmt.setString(2, dept.getPhone());
			pstmt.setInt(3, dept.getId());
			
			int update = pstmt.executeUpdate();
			
			if(update > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDepartmentName(int id, String name) {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update department set dept_name = ? where dept_id = ?");) {

			pstmt.setString(1, name);
			pstmt.setInt(2, id);

			int update = pstmt.executeUpdate();

			if (update > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
