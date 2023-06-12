package prog1of10;

import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {
		
		Scanner scan1 = new Scanner(System.in);
		String input1;
		System.out.print("Enter 'banana', 'peanut', or 'sandwich':");
		input1 = scan1.nextLine();
		
		if(input1.equals("banana") || input1.equals("peanut") || input1.equals("sandwich")){
			System.out.println("yep");
		}
		else
			System.out.println("You must enter 'banana', 'peanut', or 'sandwich'.");
		
	}

}
