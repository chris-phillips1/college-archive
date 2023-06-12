package static1;

public class myMain {

	public static void main(String[] args) {
		Person p = new Person();
		Politician po = new Politician();
		Person one = new Politician(); //At run time is a poltician, at compile time it's a person
		p.speech();
		po.speech();
		one.speech(); 
		
		talk(p);
		talk(po);
		talk(one); //runs at compile time
	}
	
	public static void talk(Person per)
	{
		System.out.println("Hi");
	}
	
	public static void talk(Politician per)
	{
		System.out.println("Lie");
	}

}
