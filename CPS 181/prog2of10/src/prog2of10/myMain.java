package prog2of10;

import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {
		
		Scanner scan1 = new Scanner(System.in);
		String input;
		
		System.out.print("Enter a string: ");
		input = scan1.nextLine();
		
		System.out.println(input.length());
		System.out.println(input.substring(2, 5));
		System.out.println(input.toUpperCase());
		System.out.println(input.toLowerCase());
		
		if(input.contains("bologna"))
			System.out.println("True");
		else
			System.out.println("False");
		
		
		if(input.equals("My bologna has a first name."))
			System.out.println("True");
		else
			System.out.println("False");
		

	}

}
