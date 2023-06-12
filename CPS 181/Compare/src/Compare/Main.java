package Compare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File fi = new File("infile.txt");
		Scanner sc = new Scanner(fi);
		List<Person> arr = new ArrayList<Person>();
		String n;
		int a, i;
		Person p;
		while(sc.hasNext())
		{
			i = sc.nextInt();
			n = sc.next();
			a = sc.nextInt();
			p = new Person(n,a,i);
			arr.add(p);
		}
		
		for(Person pp : arr)
			System.out.println(pp.toString());
		System.out.print("\n");
		
		Collections.sort(arr);
		
		for(Person pp: arr)
		{
			System.out.println(pp.toString() + " " + pp.hashCode2() + pp.hashCode());
		}
		
	}

}
