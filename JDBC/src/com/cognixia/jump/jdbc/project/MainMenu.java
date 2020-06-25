package com.cognixia.jump.jdbc.project;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.jdbc.dao.Department;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;

public class MainMenu {

	public static void main(String[] args) {
		
		// Where to put Scanner open and close is tricky (I may need to use a while loop instead of recalling method)
		// I'll start by opening and closing inside main and passing it to runScenario
		// I don't know if Scanner is OK like this but it's working
		Scanner input = new Scanner(System.in);
		runScenario(input);
		
		System.out.println("Would you like to go back to main menu? Y or N");
		String answer = input.next();
		
		if(answer.equalsIgnoreCase("Y")) {
			main(args);
		}else {
			System.out.println("Thank you. Goodbye.");
			input.close();
			System.exit(0);
		}
		
	}
	
	public static void runScenario(Scanner input) {
		
		// display main menu and ask for option:
		// What would you like to do? Select option below:
			//1)Get all students  2) Find a student 3) Add a new student 4) Update a student 5) Remove a student

		// use a switch case to account for their inputs of 1-5
		// run methods below based on options 1-5
		
		System.out.println("What would you like to do? Select option below:");
		System.out.println("1) Get all students \t2) Find a student \t3) Add a new student\t 4) Update a student\t5) Remove a student");
		String choice = input.nextLine();
		
		switch(choice) {
			case "1":
				displayGetAllStudents(input);
				break;
			case "2":
				displayFindAStudent(input);
				break;
			case "3":
				displayAddAStudent(input);
				break;
			case "4":
				displayUpdateAStudent(input);
				break;
			case "5":
				displayDeleteAStudent(input);
				break;
		}
	}
	
	// Larkin
	public static void displayGetAllStudents(Scanner input){
		
		// need to use StudentDAO - TK
		//List<Student> students = getAllStudents();
		
		StudentDAOImp studentDao = new StudentDAOImp();
		List<Student> students = studentDao.getAllStudents();
		System.out.println("All Students:\n");

		for (Student s : students) {
			System.out.println(s);
		}

		System.out.println("\n");
	}

	// Larkin
	public static void displayFindAStudent(Scanner input){
		
		System.out.println("Enter ID to find student:");

		// don't call this student, call id or studentId -TK
		// best to take input as a string and then parse to an integer-
		// it needs to be an integer to use getStudentById
		//String student = input.nextLine();
		String studentIdStr = input.nextLine();
		int studentId = Integer.parseInt(studentIdStr);
		// you have to have this line of code to use any methods from student DAO
		StudentDAOImp studentDao = new StudentDAOImp();
		Student displayStudent = studentDao.getStudentById(studentId);
		

		System.out.println("Information on " + displayStudent + ":\n");			
		System.out.println(displayStudent);
	
	}

	// me
	public static void displayAddAStudent(Scanner input){
		
		// To add a student, first need to create a new Address object
		// could be split into methods further (displayNewAddress, displayChooseDepartment, etc.)
		Address newAddress = displayAddAnAddress(input);
		
		// Also need to assign the new student to a department
		Department studentDept = displayChooseDepartment(input);
		
		// now can take rest of input for new student
		System.out.println("Enter student first name:");
		String firstName = input.nextLine().trim();
		System.out.println("Enter student last name:");
		String lastName = input.nextLine().trim();
		System.out.println("Enter student gender (M or F):");
		String gender = input.nextLine();
		System.out.println("Enter student date of birth (YYYY-MM-DD):");
		String dobStr = input.nextLine().trim();
		Date dob = Date.valueOf(dobStr);
		System.out.println("Enter number of student credits:");
		String credStr = input.nextLine().trim();
		int credits = Integer.parseInt(credStr);
		
		Student newStudent = new Student(0, firstName, lastName, gender, dob, credits, newAddress, studentDept);
		
		StudentDAOImp studentDao = new StudentDAOImp();
		boolean added = studentDao.addStudent(newStudent);
		if(added) {
			System.out.println("Student was added to DB w/ inserted ID: " + newStudent);
		}else {
			System.out.println("Error: student was not added");
		}
		
	}  

	// Abrham
	public static void displayUpdateAStudent(Scanner input){
		
		System.out.println("------updating students--------");
		// ask the id
		System.out.println("Enter the Student Id");
		int std_id = input.nextInt();
		// added: new line b/c of issues w/ nextInt() -TK
		input.nextLine();
		// TODO chek the id is between 1000
		// checkStudentId(std_id,input);
		// get the updated student info
		StudentDAOImp student = new StudentDAOImp();
		Student old_std = student.getStudentById(std_id);// use it to desplay
		// TODO ask all the data they want to change
		System.out.println("Enter new student First name:");
		String firstName = input.nextLine();
		//can just add in setters and update old_student -TK
		//old_std.setFirstName(firstName);

		System.out.println("Enter new student Last name:");
		String lastName = input.nextLine();
		//old_std.setLastName(lastName);

		System.out.println("Enter new student Gender:");
		String gender = input.nextLine();
		//old_std.setGender(gender);

		// System.out.println("Enter new student Date of birth:");
		// Date dob =input. ;

		System.out.println("Enter new student credits:");
		int credits = input.nextInt();
		// nextInt() causes issues before nextLine(); adding another line -TK
		input.nextLine();

		System.out.println("-----Enter new student Address:-----");

		// instead, should get whole Address obj and use setter methods to update, then use updateAddress()
		// get id for the address
//		int add_id = old_std.getAddress().getId();
		Address studentAddress = old_std.getAddress();

		// added in setters here -TK
		System.out.println("Enter student Street:");
		String street = input.nextLine();
		studentAddress.setStreet(street);
		System.out.println("Enter student City:");
		String city = input.nextLine();
		studentAddress.setCity(city);
		System.out.println("Enter student State:");
		String state = input.nextLine();
		studentAddress.setState(state);
		System.out.println("Enter student Zip:");
		String zip = input.nextLine();
		studentAddress.setZip(zip);
		// this won't work; use updateAddress from addressDAO - TK (making a new Address but not inserting it, 
		// so wrong id)
//		Address address = new Address(add_id, street, city, state, zip);
		// instead:
		AddressDAOImp addressDAO = new AddressDAOImp();
		boolean updatedAddress = addressDAO.updateAddress(studentAddress);
		if(!updatedAddress) {
			System.out.println("Error updating student address; please try again");
			return;
		}
		
		
		// System.out.println("Enter new student Department:");
		// Department dept = input.nextInt();

		// no need to make two student objects; can just use setters on old_student -TK
		Student new_std = new Student(std_id, firstName, lastName, gender, old_std.getDob(), credits, studentAddress,
				old_std.getDept());

		// TODO ask which one they want to update

		boolean isUpdated = student.updateStudent(new_std);

		if (isUpdated) {
			System.out.println("Sucess fully updated");
			// TODO display the old and new updates.
		} else {
			System.out.println("The info is not updated");
		}		
	} 

	// Abrham
	public static void displayDeleteAStudent(Scanner input){
		
		// get id from user
		System.out.println("------- Deleting Student---------");
		System.out.println("Enter the Student Id: ");

		int std_id = input.nextInt();
		// TODO chek the id is between 1000

		// get the updated student info and call delete method.
		StudentDAOImp student = new StudentDAOImp();
		boolean delt_std = student.deleteStudent(std_id);

		if (delt_std) {
			System.out.println("Sucess fully deleted");

		} else {
			System.out.println("The info is not deleted");
		}	
				
	}
	
	// Abrham's helper method
	public static int checkStudentId(int std_id,Scanner input){
		int id =std_id;
		while(!(id >= 10000 && id <= 1000000) || id == 0) {
			System.out.println("wrong student id");
			id=input.nextInt();
		}
		return std_id;
	}
	
	// helper methods for displayAddAStudent - Tara
	public static Address displayAddAnAddress(Scanner input) {
		
		System.out.println("Enter student address:\n---------");
		System.out.println("Enter street name:\n");
		String street = input.nextLine().trim();
		System.out.println("Enter city name:\n");
		String city = input.nextLine().trim();
		System.out.println("Enter state (2-letter abbreviation):\n");
		String state = input.nextLine().trim();
		System.out.println("Enter zip code:\n");
		String zip = input.nextLine().trim();

		Address newAddress = new Address(0, street, city, state, zip);
		AddressDAOImp addressDao = new AddressDAOImp();
		// need to reassign this variable since addAddress returns inserted ID from DB
		newAddress = addressDao.addAddress(newAddress);
		return newAddress;
	}
	
	// Tara
	public static Department displayChooseDepartment(Scanner input) {
		
		System.out.println("Choose a department id to assign to new student:\n");
		DepartmentDAOImp deptDao = new DepartmentDAOImp();
		// could pretty-print this
		for(Department dept : deptDao.getAllDepartments()) {
			System.out.println(dept);
		}
		System.out.println("(Enter department id:)\n");
		String deptIdStr = input.nextLine().trim();
		int deptId = Integer.parseInt(deptIdStr);
		Department studentDept = deptDao.getDepartmentById(deptId);
		return studentDept;
	}

}
