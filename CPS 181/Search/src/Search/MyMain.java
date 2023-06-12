package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MyMain {

	public static void main(String[] args) {
	
		ArrayList<Integer> ints = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		Search s = new Search();
		int start, end, key, input, random;
		
		for(int i = 0; i < 10000; i++)
		{
			random = (int)(Math.random() * 100000.0) + 1;
			ints.add(random);
		}
		
		Collections.sort(ints);
		
		System.out.print("Enter the number you'd like to search for: ");
		input = scan.nextInt();
		
		start = 0;
		end = ints.size();
		s.search(ints, start, end, input);
		
	}
	

}
