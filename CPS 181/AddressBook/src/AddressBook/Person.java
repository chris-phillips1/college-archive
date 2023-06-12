package AddressBook;

public class Person {

	private String firstName;
	private String lastName;
	private int idNumber;
	static int count = 1001;
	
	public Person(String fn, String ln)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.idNumber = count;
		count++;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public int getIdNumber()
	{
		return this.idNumber;
	}
	
	public String toString()
	{
		return "First Name: " + this.firstName + "\nLast Name: " + this.lastName + "\nID Number: " + this.idNumber;
	}
}
