package groceryStore;

public class Employee {

	private double hoursPerWeek = 25;
	private double dollarsPerHour = 10;
	private String SSN;
	private String lastName;
	private String firstName;
	
	public Employee(String SSN, String lastName, String firstName)
	{
		this.SSN = SSN;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public void setHoursPerWeek(double hoursPerWeek)
	{
		this.hoursPerWeek = hoursPerWeek;
	}
	
	public double getHoursPerWeek()
	{
		return hoursPerWeek;
	}
	
	public void setDollarsPerHour(double dollarsPerHour)
	{
		this.dollarsPerHour = dollarsPerHour;
	}
	
	public double getDollarsPerHour()
	{
		return dollarsPerHour;
	}
	
	public void printSalary(double numWeeks)
	{
		System.out.println(this.getFullName() + " is owed $" + (int)(numWeeks * this.getHoursPerWeek() * this.getDollarsPerHour())); 
	}
	
	public String getFullName()
	{
		return this.firstName + " " + this.lastName;
	}
	
	public String getSSN()
	{
		return SSN;
	}
	
	public String toString()
	{
		String retval = "Employee: " + this.getFullName() + "\nSSN: " + this.SSN + "\nHours: " + (int)this.hoursPerWeek +
						" hrs\nWages: $" + (int)this.getDollarsPerHour() + "/hour";
		return retval;
	}
	
}
