package com.cognixia.jump.jdbc.dao;

import java.util.List;

public interface DepartmentDAO {
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentByID(int id);
	
	public Department getDepartmentByName(String name);
	
	public boolean addDepartment(Department dept);
	
	public boolean deleteDepartment(int id);
	
	public boolean updateDepartment(Department dept);
	
	public boolean updateDepartmentName(int id, String name);

}
