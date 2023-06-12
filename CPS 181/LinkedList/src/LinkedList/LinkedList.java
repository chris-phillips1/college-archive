package LinkedList;

import java.util.Scanner;

public class LinkedList implements LList {

	private Node head;
	private Node tail;
	private static int total = 0;
	
	public void LinkedList()
	{
		head = null;
		tail = null;
	}
	
	public void addToFront(Node n)
	{
		if(head == null)
		{
			head = n;
			total++;
		}
		else
		{
			n.forward = head;
			head = n;
			total++;
		}
		
	}

	public void addToRear(Node n)
	{
		Node currentNode = head;
		
		while(currentNode.forward != null)
			currentNode = currentNode.forward;
		
		currentNode.forward = n;
		total++;
		
			
	}

	public void addToPos(int thePos, Node n)
	{
		Node currentNode = head;
		
		if( thePos > total)
			this.addToRear(n);
		
		for(int i = 1; i < thePos - 1; i++)
		{
			currentNode = currentNode.forward;
		}
		n.forward = currentNode.forward;
		currentNode.forward = n;
	}
	
	public void fnSearch()
	{
		String fname;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your first name: ");
		fname = scan.next();
		Node testNode = head;
		
		while(testNode != null)
		{
			if(fname.equals(testNode.firstName)) {
				System.out.println("Node found: " + testNode.firstName + " " + testNode.lastName + " " + testNode.age);
				testNode = testNode.forward;
			}
		}
	}
	
	public void lnSearch()
	{
		String lname;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your last name: ");
		lname = scan.next();
		Node testNode2 = head;
		
		while(testNode2 != null)
		{
			if(lname.equals(testNode2.lastName)) {
				System.out.println("Node found: " + testNode2.firstName + " " + testNode2.lastName + " " + testNode2.age);
				testNode2 = testNode2.forward;
			}
			else
				testNode2 = testNode2.forward;
		}
	}
	
	public void ageSearch()
	{
		int age;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your age: ");
		age = scan.nextInt();
		Node testNode3 = head;
		
		while(testNode3 != null)
		{
			if(age == testNode3.age) {
				System.out.println("Node found: " + testNode3.firstName + " " + testNode3.lastName + " " + testNode3.age);
				testNode3 = testNode3.forward;
			}
			else
				testNode3 = testNode3.forward;
		}
		return;
		
	}
	
	public String toString()
	{
		Node nNode = head;
		String retval = "Test";
		if(nNode == null) {
			while(nNode.forward != null)
			{
				retval += nNode.firstName + " " + nNode.lastName + " " + nNode.age + "\n";
				nNode = nNode.forward;
			}
		}
		if(nNode == null)
			retval += "No nodes.";
		return retval;
	}
	
//	public int menu()
//	{
//		Scanner scan = new Scanner(System.in);
//		System.out.println("What would you like to search by?");
//		System.out.println("1) First Name");
//		System.out.println("2) Last Name");
//		System.out.println("3) Age");
//		System.out.println("4) Close Program");
//		return scan.nextInt();
//	}
	
}
