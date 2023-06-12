package LinkedList;

import java.util.Scanner;

public class MyMain {

	public static void main(String[] args)
	{
		LinkedList myList = new LinkedList();
		String fn, ln;
		int age;
		int selection = menu();
		int searchSelection;
		
		
		while(selection != 5)
		{
			switch(selection){
			case 1:
				Scanner scan = new Scanner(System.in);
				System.out.print("Enter your first name: ");
				fn = scan.next();
				System.out.print("Enter your last name: ");
				ln = scan.next();
				System.out.print("Enter your age: ");
				age = scan.nextInt();
				myList.addToFront(new Node(fn, ln, age));
				break;
			case 2:
				Scanner scan1 = new Scanner(System.in);
				System.out.print("Enter your first name: ");
				fn = scan1.next();
				System.out.print("Enter your last name: ");
				ln = scan1.next();
				System.out.print("Enter your age: ");
				age = scan1.nextInt();
				myList.addToRear(new Node(fn, ln, age));
				break;
			case 3:
				Scanner scan2 = new Scanner(System.in);
				System.out.print("Enter a position: ");
				int pos = scan2.nextInt();
				System.out.print("Enter your first name: ");
				fn = scan2.next();
				System.out.print("Enter your last name: ");
				ln = scan2.next();
				System.out.print("Enter your age: ");
				age = scan2.nextInt();
				myList.addToPos(pos, new Node(fn, ln, age));
				break;
			case 4:
				searchSelection = searchMenu();
				switch(searchSelection){
				case 1:
					myList.fnSearch();
					break;
				case 2:
					myList.lnSearch();
					break;
				case 3:
					myList.ageSearch();
					break;
				default:
					System.out.println("Error");
					break;
				}
				break;
			default:
				System.out.println("Error");
				break;
			}
			selection = menu();
		}
	}
		
	public static int menu()
	{
		int selection;
		Scanner scan = new Scanner(System.in);
		System.out.println("------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("1) Add to front");
		System.out.println("2) Add to rear");
		System.out.println("3) Add to position");
		System.out.println("4) Search");
		System.out.println("5) Close Program");
		selection = scan.nextInt();
		return selection;
	}
	
	public static int searchMenu()
	{
		int selection;
		Scanner scan = new Scanner(System.in);
		System.out.println("------------------------------");
		System.out.println("What would you like to search by?");
		System.out.println("1) First Name");
		System.out.println("2) Last Name");
		System.out.println("3) Age");
		selection = scan.nextInt();
		return selection;
		
	}

}
