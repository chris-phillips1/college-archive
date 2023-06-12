package programmingProject2;

public class Faculty extends Employee {

	private String officeHours;
	private String rank;
	
	public Faculty(String n, String a, String pn, String ea, int on, int sal, String dh, String oh, String r)
	{
		super(n,a,pn,ea,on,sal, dh);
		officeHours = oh;
		rank = r;
	}
	
	public String toString()
	{
		String retval = "Faculty: " + super.getName() + "\nRank: " +this.rank +
				"\nSalary: $" + super.getSalary() + "\nDate Hired: " + super.getDateHired() +
				"\n\nOffice Hours: " + this.officeHours + "\nOffice: " + super.getOfficeNumber() + 
				"\nAddress: " + super.getAddress() + "\nPhone Number: " + super.getPhoneNumber() + 
				"\nEmail: " + super.getEmailAddress();
		
		return retval;
	}
}
