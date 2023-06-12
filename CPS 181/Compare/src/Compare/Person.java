package Compare;

public class Person {

	private String name;
	private int age;
	private int index;
	
	Person(String n, int a, int i)
	{
		this.name = n;
		this.age = a;
		this.index = i;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String n)
	{
		this.name = n;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setAge()
	{
		this.age = age;
	}
	
	public String toString()
	{
		return name + " " + age;
	}
	
	public boolean equals(Person o)
	{
		boolean retval = false;
		if(o == this)
			retval = true;
		else
			retval = false;
		return retval;
	}
	
	public int hashCode2()
	{
		int rethash = 17;
		int newValue = 0;
		rethash = 31*(rethash*this.age + index);
		if(name != null)
			newValue = name.hashCode();
		rethash = rethash + newValue;
		return rethash;
	}
}
