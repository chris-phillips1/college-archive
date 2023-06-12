package ClassInAClass;

public class Class1 {
	public int age;
	public double weight;
	public Class2 cl2;
	
	public Class1(int a, double w, String name)
	{
		this.age = a;
		this.weight = w;
		cl2 = new Class2(name);
//		System.out.println(cl2.name);
	}
}
