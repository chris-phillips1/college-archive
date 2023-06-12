package ClassInAClass;

public class myMain {

	public static void main(String[] args) {
		Class1 cl1 = new Class1(12, 120.0, "Tom");
		System.out.println("Back from Constructor: " + cl1.age);
		System.out.println(cl1.cl2.name);
	}

}
