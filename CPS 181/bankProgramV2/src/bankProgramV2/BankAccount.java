package bankProgramV2;

public abstract class BankAccount {

	private String name;
	private double balance;
	private String userID;
	private String pass;
	private int accNumber;

	public BankAccount(String name, double balance, String userID, String pass, int accNumber)
	{
		this.name = name;
		this.balance = balance;
		this.userID = userID;
		this.pass = pass;
		this.accNumber = accNumber;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addToBalance(double newAmt)
	{
		this.balance += newAmt;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public String getUserID()
	{
		return this.userID;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	
	public int getAccNumber()
	{
		return this.accNumber;
	}
	
}
