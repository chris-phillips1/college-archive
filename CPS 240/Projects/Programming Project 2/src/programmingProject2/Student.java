package programmingProject2;

public class Student extends Person {

	private final String status;
	
	public Student(String n, String a, String pn, String ea, String s)
	{
		super(n,a,pn,ea);
		status = s;
	}
	
	public String toString()
	{
		String retval = "Student: " + super.getName() + "\nStatus: " + this.status + 
				"\nAddress: " + super.getAddress() + "\nPhone Number: " + super.getPhoneNumber() +
				"\nEmail Address: " + super.getEmailAddress();
		
		return retval;
	}
	
}
