package banks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * This program essentially runs a bank with
 * two different lines, first in, first out and
 * last in, first out. 
 * 
 * @author Chris Phillips
 * @version 1.0
 * @date April 11, 2017
 */

public class Main {

	//Create a global Stack and a global Queue of Customers
	static Queue<Customer> line1 = new LinkedList<Customer>();
	static Stack<Customer> line2 = new Stack<Customer>();
	
	public static void main(String[] args) throws InterruptedException {
		
		//Set mySelection to equal the results of a menu
		String mySelection = menu();
		
		while(!mySelection.equals("0")) //Runs the menu as long as the input isn't 0
		{
			switch(mySelection) {
			case "a":
			case "A":
				addCustomerToLine();
				Thread.sleep(1000);
				break;
			case "b":
			case "B":
				nextCustomer();
				Thread.sleep(1000);
				break;
			case "c":
			case "C":
				numberOfCustomers();
				Thread.sleep(1000);
				break;
			case "d":
			case "D":
				whichLineIsLonger();
				Thread.sleep(1000);
				break;
			case "e":
			case "E":
				waitOnACustomer();
				Thread.sleep(1000);
				break;
			case "f":
			case "F":
				moveFromOneToTwo();
				Thread.sleep(1000);
				break;
			case "g":
			case "G":
				moveFromTwoToOne();
				Thread.sleep(1000);
				break;
			default:
				System.out.println("Try Again.");
				break;
			}
		mySelection = menu();
		}	
	}
	
	/* This function moves the first customer in line 2 to
	 * end of line 1. 
	 * @return void
	 */
	public static void moveFromTwoToOne()
	{
		if(!line2.isEmpty())
		{
			Customer c = line2.pop();
			line1.add(c);
		}
	}
	
	/* This function moves the first customer in line 1 to
	 * end of line 2. 
	 * @return void
	 */
	public static void moveFromOneToTwo()
	{
		if(!line1.isEmpty())
		{
			Customer c = line1.poll();
			line2.push(c);
		}
	}
	
	/* This function prompts the user for the line
	 * that they would like to be waited on, then
	 * "waits" on that customer.
	 * @return void
	 */
	public static void waitOnACustomer()
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println("Enter 1 for Line 1 or 2 for Line 2");
		choice = scan.nextInt();
		
		
		if(choice == 1 && !line1.isEmpty())
			System.out.println("You waited on " + (line1.poll().getName()) + ", they are gone now...");
		else if(choice == 2 && !line2.isEmpty())
			System.out.println("You waited on " + (line2.pop().getName()) + ", they are gone now...");
		else
			System.out.println("Try Again.");
		
	}
	/* This function checks the size of line1 and
	 * compares it against line2 and prints out which
	 * one is longer.
	 * @return void
	 */
	public static void whichLineIsLonger()
	{
		if(line1.size() < line2.size())
			System.out.println("Line 2 is longer.");
		else if(line1.size() > line2.size())
			System.out.println("Line 1 is longer.");
		else
			System.out.println("Line 1 has the same length as Line 2.");
	}
	
	/* This function prompts the user to select a line
	 * and based on that, prints out that line's length 
	 * @return void
	 */
	public static void numberOfCustomers()
	{
		Scanner scan = new Scanner(System.in);
		Queue<Customer> testLine1 = line1;
		Stack<Customer> testLine2 = line2;
		int retval = 0, choice;
		
		System.out.println("Enter 1 for Line 1 or 2 for Line 2");
		choice = scan.nextInt();
		
		if(choice == 1)
			retval = line1.size();
		else if(choice == 2)
			retval = line2.size();
		
		System.out.println(retval + " Customers in Line " + choice);
	}
	
	/* This function prompts the user for a line
	 * and based on that peeks at the first customer
	 * in the line.
	 * @return void
	 */
	public static void nextCustomer()
	{
		Scanner scan = new Scanner(System.in);
		int lineChoice;
		String waiting = "No one";
		
		System.out.println("Enter 1 for Line 1 or 2 for Line 2");
		lineChoice = scan.nextInt();
		
		if(lineChoice == 1 && !line1.isEmpty())
			waiting = line1.peek().getName();
		else if(lineChoice == 2 && !line2.isEmpty())
			waiting = line2.peek().getName();
		
		System.out.println(waiting + " is waiting");
	}
	
	/* This function prompts the user for a line to
	 * add the customer to. Then asks for the name
	 * and the account number and creates a new customer
	 * and adds that customer to the selected line.
	 * @return void
	 */
	public static void addCustomerToLine()
	{
		Scanner scan = new Scanner(System.in);
		int lineChoice;
		String name;
		String acctNumber;
		
		
		System.out.println("Enter 1 for Line 1 or 2 for Line 2");
		lineChoice = scan.nextInt();
	
		System.out.println("Enter the name: ");
		name = scan.next();
		name += " " + scan.next();
		
		System.out.println("Enter the bank account number: ");
		acctNumber = scan.next();
		
		Customer c = new Customer(name, acctNumber);
		
		if(lineChoice == 1)
			line1.add(c);
		else if(lineChoice == 2)
			line2.push(c);
			
		System.out.println(c.toString());
		System.out.println(name + " added to Line " + lineChoice);
	}
	
	/* This function prints out a list of all the options
	 * that the user has for the bank.
	 * @return String This prints out whichever option
	 * the user types
	 */
	public static String menu()
	{
		String selection = null;
		
		System.out.println("Enter A to add a customer to a line");
		System.out.println("Enter B to look at which customer is next");
		System.out.println("Enter C to find the number of customer for each line");
		System.out.println("Enter D to find which line is longer");
		System.out.println("Enter E to wait on a customer");
		System.out.println("Enter F to move customer from line 1 to line 2");
		System.out.println("Enter G to move customer from line 2 to line 1");
		
		Scanner userInput = new Scanner(System.in);
		selection = userInput.next();
		
		return selection;
	}

}
