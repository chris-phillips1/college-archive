package whereIsScanner;

import java.util.Scanner;

public class myMain {

	static Scanner kb = new Scanner(System.in);
	static final int number = 5;
	
	public static void main(String[] args) {
		
		int inputVal;
		System.out.println("Enter your number: ");
		inputVal = kb.nextInt();
		System.out.println(inputVal);
		printIt();
		System.out.println(number);

	}
	
	public static void printIt()
	{
		System.out.println("Enter your number: ");
		int anotherVal = kb.nextInt();
		System.out.println(anotherVal);
		System.out.println(number);
	}

}
