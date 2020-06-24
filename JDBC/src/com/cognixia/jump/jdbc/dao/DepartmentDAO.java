package com.cognixia.jump.jdbc.dao;

import java.util.List;

public interface DepartmentDAO {

	// better to return these values rather than void and figure out inside method
	public List<Department> getAllDepartments();

	public Department getDepartmentById(int id);

	public Department getDepartmentByName(String name);

	public boolean addDepartment(Department dept);
	
	public boolean deleteDepartment(int id);
	
	public boolean updateDepartment(Department dept);
	
	public boolean updateDepartmentName(int id, String name);
	
	
	
}
