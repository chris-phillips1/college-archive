package Nodes;

import java.util.Scanner;

public class TheMain {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		LinkedList myList = new LinkedList();
		ANode myNode;
		String name;
		int age;
		int choice = 0;
		
		while (choice != 4) {
			choice = menu(kb);
			switch (choice) {
			case 1:
				name = getTheName(kb);
				myNode = new ANode(name);
				System.out.println(myNode.name);
				myList.add2Front(myNode);
				System.out.println(myList.toString());
				break;
			case 2:
				if (myList.isEmpty())
					System.out.println("The list is currently empty.");
				else
					System.out.println(myList.toString());
				break;
			case 3:
				break;
			default:
				choice = 4;
			}
		}
	}

	public static int menu(Scanner kb) {
		int retval = -1;
		System.out.println("What do you want to do?");
		System.out.println("1) Add a Person to the Front of the List");
		System.out.println("2) Remove a Person from the List");
		System.out.println("3) Find a Person in the List");
		System.out.println("4) Close Program");
		retval = kb.nextInt();
		return (retval);
	}

	public static String getTheName(Scanner kb) {
		System.out.print("Please enter a name: ");
		String retval = "";
		retval = kb.next();
		return (retval);
	}

	public static int getTheAge(Scanner kb) {
		int retval = -4;
		return (retval);
	}

}

