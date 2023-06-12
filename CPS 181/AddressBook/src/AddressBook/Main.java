package AddressBook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File myFile = new File("persons.txt");
		Scanner scan1 = new Scanner(myFile);
		ArrayList<Person> Persons = new ArrayList<Person>();
		
		String fn, ln;
		Person pe;
		int mySelection;
		
		while(scan1.hasNext())
		{
			fn = scan1.next();
			ln = scan1.next();
			pe = new Person(fn, ln);
			Persons.add(pe);
		}
		
		AddressBook ab = new AddressBook();
		
		for(Person p : Persons)
			ab.add(p);
		
		mySelection = menu();
		
		while(mySelection != 5){
			
			switch(mySelection){
			case 1: 
				add(ab);
				break;
			case 2:
				delete(ab);
				break;
			case 3:
				search(ab);
				break;
			case 4:
				compareLastName(ab);
				break;
			default:
				System.out.println("Error");
				break;
			}
			mySelection = menu();
		}
	}
	
	public static void add(AddressBook ab)
	{
		Person p;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter First Name: ");
		String fn = input.nextLine();
		System.out.print("Enter Last Name: ");
		String ln = input.nextLine();
		int testAdd = ab.add(p = new Person(fn, ln));
		if(testAdd > 0)
			System.out.println(fn + " " + ln + " added with a userID of " + p.getIdNumber() );
	}
	
	public static void delete(AddressBook ab)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the ID Number of the account you'd like to delete: ");
		int id = input.nextInt();
		ab.delete(id);
	}
	
	public static void search(AddressBook ab)
	{
		Scanner input = new Scanner(System.in);
		String fn, ln;
		int id;
		System.out.println("What would you like to search by?");
		System.out.println("1) First Name");
		System.out.println("2) Last Name");
		System.out.println("3) ID Number");
		int theSelection = input.nextInt();
		
		switch(theSelection){
		case 1:
			System.out.print("Enter the First Name: ");
			fn = input.next();
			ab.firstNameSearch(fn);
			break;
		case 2:
			System.out.print("Enter the Last Name: ");
			ln = input.next();
			ab.lastNameSearch(ln);
			break;
		case 3:
			System.out.print("Enter the ID Number: ");
			id = input.nextInt();
			ab.idSearch(id);
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	public static void compareLastName(AddressBook ab)
	{
		Scanner input = new Scanner(System.in);
		int id1, id2;
		String ln1 = "";
		String ln2 = "";
		System.out.print("Enter the ID of the first person: ");
		id1 = input.nextInt();
		System.out.print("Enter the ID of the second person: ");
		id2 = input.nextInt();
		
		for(int i = 0; i < ab.book.size(); i++)
		{
			if(ab.book.get(i).getIdNumber() == id1)
				ln1 = ab.book.get(i).getLastName();
			
			if(ab.book.get(i).getIdNumber() == id2)
				ln2 = ab.book.get(i).getLastName();
		}
		
		if(ln1.compareTo(ln2) < 0)
			System.out.println(ln1 + " comes before " + ln2 + ".");
		else if(ln1.compareTo(ln2) > 0)
			System.out.println(ln2 + " comes before " + ln1 + ".");
		else if(ln1.compareTo(ln2) == 0)
			System.out.println(ln1 + " and " + ln2 + " are the same.");
		else
			System.out.println("Error");
		
	}
	
	public static int menu()
	{
		Scanner input = new Scanner(System.in);
		int selection;
		System.out.println("------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("1) Add a Person");
		System.out.println("2) Delete a Person");
		System.out.println("3) Search for a Person");
		System.out.println("4) Compare Last Names");
		System.out.println("5) Close Program");
		
		selection = input.nextInt();
		
		return selection;
	}

}
