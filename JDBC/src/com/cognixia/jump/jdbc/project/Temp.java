package com.cognixia.jump.jdbc.project;

import com.cognixia.jump.jdbc.dao.Department;
import com.cognixia.jump.jdbc.dao.DepartmentDAO;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;

public class Temp {

	public static void main(String[] args) {
		
		// take in info for student from console
		String fName = "Orquidia";
		String lName = "Moreno";
		// gender, dob, credits
		
		// ask student which department they're in
		DepartmentDAO deptDao = new DepartmentDAOImp();
		
		for(Department dept : deptDao.getAllDepartments()) {
			System.out.println(dept);
		}
		
		// console input to get id, then pass in
		Department dept = deptDao.getDepartmentById(10000);
		
		// more console input
		int id  = 0;
		String street = "this street";
		String city = "new city";
		String state = "ST";
		String zip = "56789";
		
		Address address = new Address(id, street, city, state, zip);
		
		//Student student = new Student(id, fName, lName, gender, dob, credits, address, dept);
		
//		Split up work:
//			1)	Set up address DAO – one or two people (split up methods)
//			2)	Split up StudentDAO (Do we need more methods) methods between 2 or 3 people
//			3)	Work on menu – one or two people

		

	}

}
