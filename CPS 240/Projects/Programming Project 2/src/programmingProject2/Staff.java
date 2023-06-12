package programmingProject2;

public class Staff extends Employee{

	private String title;
	
	public Staff(String n, String a, String pn, String ea, int on, int sal, String dh, String t)
	{
		super(n, a, pn, ea, on, sal, dh);
		title = t;
	}
	
	public String toString()
	{
		String retval = "Staff: " + super.getName() + "\nSalary: $" + super.getSalary() +
				"\nDate Hired: " + super.getDateHired() + "\n\nOffice: " + super.getOfficeNumber() +
				"\nAddress: " + super.getAddress() + "\nPhone Number: " + super.getPhoneNumber() +
				"\nEmail: " + super.getEmailAddress();
		
		return retval;
	}
}
