package concatenate;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * The CalcConcat Program allows the user to 
 * either add or subtract two doubles or
 * concatenate two strings.
 * 
 * @authors Chris, Becky, Katie, Zjosh
 * @version 1.0
 * @date February 7th, 2017 
 */


public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CalcConcat calc = new CalcConcat(); //initializes a CalcConcat instance
		String input1;
		String input2;
		String input3;
		int decision = 0;
		
		while (decision != -1) { //While loop allows us to ask for input continuously 
			System.out.println("Enter input 1");
			input1 = scan.next();
			System.out.println("Enter input 2");
			input2 = scan.next();
			System.out.println("Enter a sign");
			input3 = scan.next();

			//Calls the addOrConcat method and can catch an InputMismatchException, IllegalArgumentException, or InvalidCalcExcpetion
			try {
				String answer = calc.addOrConcat(input1, input2, input3);
				System.out.println("The answer for " + input1 + input3 + input2 + " is " + answer);
			} catch (InputMismatchException ime) {
				System.out.println(ime.toString());
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.toString());
			} catch (InvalidCalcException ice) {
				System.out.println(ice.toString());
			}

			System.out.println("Enter -1 to stop or any other input to continue");
			decision = scan.nextInt();
		}

	}

}

