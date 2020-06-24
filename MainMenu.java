
public class MainMenu {

	public static void main(String[] args) {
		
		// Where to put Scanner open and close is tricky (I may need to use a while loop instead of recalling method)
		// I'll start by opening and closing inside main and passing it to runScenario
		runScenario();
		System.out.println("Would you like to go back to main menu? Y or N");
		if(Y) {
			main(args);
		}

	}
	
	public static void runScenario(Scanner input) {
		
		// display main menu and ask for option:
		// What would you like to do? Select option below:
			//1)Get all students  2) Find a student 3) Add a new student 4) Update a student 5) Remove a student

		// use a switch case to account for their inputs of 1-5
		// run methods below based on options 1-5
	}
	
	public static void displayGetAllStudents(Scanner input){}

	public static void displayFindAStudent(Scanner input){}

	public static void displayAddAStudent(Scanner input){
		
		System.out.println("Enter student address");
	}  

	public static void displayUpdateAStudent(Scanner input){} 

	public static void displayDeleteAStudent(Scanner input){}

}
