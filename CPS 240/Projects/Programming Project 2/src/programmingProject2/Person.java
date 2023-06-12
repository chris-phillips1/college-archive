package programmingProject2;

public class Person {
	
	private String name;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	
	public Person(String n, String a, String pn, String ea)
	{
		name = n;
		address = a;
		phoneNumber = pn;
		emailAddress = ea;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
}
