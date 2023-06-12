package programmingProject2;

public class Employee extends Person {

	private int officeNumber;
	private int salary;
	private String dateHired;
	
	public Employee(String n, String a, String pn, String ea, int on, int sal, String dh)
	{
		super(n, a, pn, ea);
		officeNumber = on;
		salary = sal;
		MyDate md = new MyDate(dh);
		dateHired = md.getDate();
	}
	
	public int getSalary()
	{
		return salary;
	}
	
	public String getDateHired()
	{
		return dateHired;
	}
	
	public int getOfficeNumber()
	{
		return officeNumber;
	}
}
