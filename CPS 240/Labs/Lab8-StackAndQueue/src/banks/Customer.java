package banks;

public class Customer {

	private String name;
	private String accountNumber;
	
	public Customer(String name, String accountNumber)
	{
		this.name = name;
		this.accountNumber = accountNumber;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getAccountNumber()
	{
		return this.accountNumber;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	
	public String toString()
	{
		return "Name: " + this.name + "\nAccount Number: " + this.accountNumber;
	}
	
}
