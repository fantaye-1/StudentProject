import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		
		// Where to put Scanner open and close is tricky (I may need to use a while loop instead of recalling method)
		// I'll start by opening and closing inside main and passing it to runScenario
		Scanner input = new Scanner(System.in);
		runScenario(input);
		
		System.out.println("Would you like to go back to main menu? Y or N");
		String answer = input.next();
		
		if(answer == 'Y') {
			input.close();
			main(args);
		}else {
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
		String choice = input.next();
		
		switch(choice) {
			case "1":
				System.out.println("1 chosen");
				break;
			case "2":
				System.out.println("2 chosen");
				break;
			case "3":
				System.out.println("3 chosen");
				break;
			case "4":
				System.out.println("4 chosen");
				break;
			case "5":
				System.out.println("5 chosen");
				break;
		}
	}
	
	public static void displayGetAllStudents(Scanner input){}

	public static void displayFindAStudent(Scanner input){}

	public static void displayAddAStudent(Scanner input){
		
		System.out.println("Enter student address");
	}  

	public static void displayUpdateAStudent(Scanner input){} 

	public static void displayDeleteAStudent(Scanner input){}

}
