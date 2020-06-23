package com.cognixia.jump.jdbc.dao;

public class Main {

	public static void main(String[] args) {
		
		DepartmentDAOImp deptDao = new DepartmentDAOImp();
		
		System.out.println("Get all departments");
		
		for(Department dept : deptDao.getAllDepartments()) {
			System.out.println(dept);
		}
		
		// adding a Department manually (could be from user)
//		Department newDept = new Department(0, "Film", "1234567800");
//		boolean added = deptDao.addDepartment(newDept);
//		if(added) {
//			System.out.println("Department was added");
//		}
		
//		System.out.println("Updating a department");
//		Department toUpdate = deptDao.getAllDepartments().get(0);
//		toUpdate.setPhone("1212121212");
//		toUpdate.setName("Account");
//		
//		boolean updated = deptDao.updateDepartment(toUpdate);
//		if(updated) {
//			System.out.println("Update was successful");
//		}
//		
//		updated = deptDao.updateDepartmentName(10019, "Film Studies");
//		if(updated) {
//			System.out.println("Department name was updated");
//		}
		
//		System.out.println("Get Department by id");
//		Department dept = deptDao.getDepartmentById(10019);
//		System.out.println(dept);
//		
//		System.out.println("\nGet Department by name");
//		dept = deptDao.getDepartmentByName("Film Studies");
//		System.out.println(dept);
		
		boolean deleted = deptDao.deleteDepartment(10019);
		if(deleted) {
			System.out.println("Department was deleted");
		}
	}

}
