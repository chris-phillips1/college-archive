package programmingProject2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int selection = selectionMenu();

		while(selection != 3)
		{
			switch(selection) {
			
			case 1:
				System.out.println(createStudent().toString());
				break;
			case 2:
				System.out.println(createEmployee().toString());
				break;
			default:
				System.out.println("Error.");
				break;
			}
			selection = selectionMenu();
		}
	}
	
	public static Student createStudent()
	{
		String name, address, phoneNumber, emailAddress, status;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name: ");
		name = scan.nextLine();
		System.out.println("Enter address: ");
		address = scan.nextLine();
		System.out.println("Enter phone number: ");
		phoneNumber = scan.nextLine();
		System.out.println("Enter email: ");
		emailAddress = scan.nextLine();
		System.out.println("Enter student's status: ");
		status = scan.nextLine();
		Student theStudent  = new Student(name, address, phoneNumber, emailAddress, status);
		return theStudent;
	}
	
	public static Employee createEmployee()
	{
		String name, address, phoneNumber, emailAddress, dateHired; //Universal Strings
		int officeNumber, salary; //Universal ints
		
		String officeHours, rank; //Faculty Strings
		String title; //Staff Strings
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name: ");
		name = scan.nextLine();
		System.out.println("Enter address: ");
		address = scan.nextLine();
		System.out.println("Enter phone number: ");
		phoneNumber = scan.nextLine();
		System.out.println("Enter email: ");
		emailAddress = scan.nextLine();
		System.out.println("Enter date hired(dd/mm/yy): ");
		dateHired = scan.nextLine();
		System.out.println("Enter office number: ");
		officeNumber = scan.nextInt();
		System.out.println("Enter salary: ");
		salary = scan.nextInt();
		
		int selection = employeeSelectionMenu();
		Employee choice = null;
		
		switch(selection) {
		case 1: 
			officeHours = getOfficeHours();
			rank = getRank();
			Faculty f = new Faculty(name, address, phoneNumber, emailAddress, officeNumber, salary, dateHired, officeHours, rank);
			choice = f;
			break;
		case 2: 
			title = getTitle();
			Staff s = new Staff(name, address, phoneNumber, emailAddress, officeNumber, salary, dateHired, title);
			choice = s;
			break;
		default:
			System.out.println("Error.");
			}
		
		return choice;
	}
	
	public static String getOfficeHours()
	{
		String retval;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter office hours: ");
		retval = scan.nextLine();
		return retval;
	}
	
	public static String getRank()
	{
		String retval;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter rank: ");
		retval = scan.nextLine();
		return retval;
	}
	
	public static String getTitle()
	{
		String retval;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter title: ");
		retval = scan.nextLine();
		return retval;
	}
	
	public static int employeeSelectionMenu()
	{
		int selection;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter");
		System.out.println("1) To create a faculty member");
		System.out.println("2) To create a staff member");
		selection = scan.nextInt();
		
		return selection;
	}
	
	public static int selectionMenu()
	{
		int selection;
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter");
		System.out.println("1) To create a student");
		System.out.println("2) To create an employee");
		System.out.println("3) Exit");
		selection = scan1.nextInt();
		
		return selection;
	}

}
