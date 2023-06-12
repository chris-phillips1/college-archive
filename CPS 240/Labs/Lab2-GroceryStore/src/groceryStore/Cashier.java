package groceryStore;

public class Cashier extends Employee {

	private int cashierLane;
	
	public Cashier(String SSN, String lastName, String firstName)
	{
		super(SSN, lastName, firstName);
		this.setDollarsPerHour(12);
		this.setHoursPerWeek(30);
		
	}
	
	public void printSalary(double numWeeks)
	{
		System.out.println("Error: Cashier's can't work fractional weeks");
	}
	
	public void printSalary(int numWeeks)
	{
		System.out.println(super.getFullName() + " is owed $" + (int)(numWeeks * super.getHoursPerWeek() * super.getDollarsPerHour())); 
	}
	
	public String toString()
	{
		String retval = "Cashier: " + super.getFullName() + "\nSSN: " + super.getSSN() + "\nHours: " + (int)super.getHoursPerWeek() +
						" hrs\nWages: $" + (int)super.getDollarsPerHour() + "/hour" + "\nLane: " + cashierLane;
		return retval;
	}
	
}
