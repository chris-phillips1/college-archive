package groceryStore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static double theDouble;
	
	public static void main(String[] args) 
	{
		Employee jane = new Employee("111-11-1111", "Doe" , "Jane");
		Cashier joe = new Cashier("222-22-2222", "Smoe", "Joe");
		
		
		int selection = selectionMenu();
		
		while(selection != 3)
		{
			switch(selection) {
			case 1:
				jane.printSalary(askDoubleWeeks());
				break;
			case 2:
				int testInt = askIntWeeks();
				
				if(testInt == -999)
					joe.printSalary(theDouble);
				else
					joe.printSalary(testInt);
				break;
			default:
				System.out.println("Error.");
				break;
			
			}
			selection = selectionMenu();
		}
		
		System.out.println("Exiting: Have a Nice Day!");
		
	}
	
	public static double askDoubleWeeks()
	{
		double numWeeks;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of weeks in fractional increments:");
		numWeeks = scan.nextDouble();
		return numWeeks;
	}
	
	public static int askIntWeeks()
	{
		int numWeeks = -999;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of weeks in whole number increments:");
		
		if(scan.hasNextInt())
			numWeeks = scan.nextInt();
		else
			theDouble = scan.nextDouble();
		
		return numWeeks;
	}

	public static int selectionMenu()
	{
		int selection;
		Scanner scan = new Scanner(System.in);
		System.out.println("Who's salary would you like to print?");
		System.out.println("1) Jane Doe, Employee");
		System.out.println("2) Joe Smoe, Cashier");
		System.out.println("3) Exit");
		selection = scan.nextInt();
		
		return selection;
	}
	
}
