package AddressBook;

import java.io.PrintWriter;
import java.util.ArrayList;

public class AddressBook {
	
	ArrayList<Person> book = new ArrayList<Person>();
	
	public AddressBook()
	{
		
	}
	
	public int add(Person myPerson)
	{
		boolean alreadyContains = false;
		
		for(int i = 0; i < book.size(); i++)
		{
			if(book.get(i).getFirstName().equals(myPerson.getFirstName())
					&& book.get(i).getLastName().equals(myPerson.getLastName()))
			{
				alreadyContains = true;
			}
		}
		
		if(!alreadyContains)
		{
			book.add(myPerson);
			return 1;
		}
		else
		{
			System.out.println("That Person already exists.");
			return -1;
		}
	}
	
	public void delete(int idNumber)
	{
		for(int i = 0; i < book.size(); i++)
		{
			if(book.get(i).getIdNumber() == idNumber)
				book.remove(i);
		}
	}
	
	public void firstNameSearch(String firstNameCheck)
	{
		System.out.println("The following people matched your search: ");
		for(Person a : book)
		{
			if(a.getFirstName().equals(firstNameCheck))
					System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getIdNumber());
		}
	}
	
	public void lastNameSearch(String lastNameCheck)
	{
		System.out.println("The following people matched your search: ");
		for(Person a : book)
		{
			if(a.getLastName().equals(lastNameCheck))
				System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getIdNumber());
		}
	}
	
	public void idSearch(int id)
	{
		System.out.println("The following people matched your search: ");
		for(Person a : book)
		{
			if(a.getIdNumber() == id)
				System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getIdNumber());
		}
	}
}
