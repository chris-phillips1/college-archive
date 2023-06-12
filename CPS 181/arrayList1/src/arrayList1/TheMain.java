package arrayList1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TheMain {

	public static void main(String[] args) throws IOException {
		
		File fil = new File("infile.txt");
		Scanner scan1 = new Scanner(fil);
		ArrayList<Person> arr = new ArrayList<Person>();
		Person p = new Person("Chris", 18);
		Person q = new Person("Justin", 19);
		Person r = new Person("Jennifer", 18);
		Person s = new Person("Mary", 47);
		String name = "Tom";
		String name2 = "Tom";
		String name3 = scan1.next();
		System.out.println("Name 3 = " + name3);
		
//		arr.add(p);
//		arr.add(q);
//		arr.add(r);
//		arr.add(s);
//		System.out.println(arr.size());
//		System.out.println(arr.toString());
		
		if(name.equals(name2))
			System.out.println("Equal");
		else
			System.out.println("Not Equal");
	}

}
