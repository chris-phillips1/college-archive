package except1;

import java.util.Scanner;

public class Except1 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int x, y, div;
		System.out.print("Enter a value for x: ");
		x = kb.nextInt();
		System.out.print("Enter a vaue for y: ");
		y = kb.nextInt();
		div = checkDivision(x, y);
		System.out.println(x + " / " + y + " = " + div);

	}
	
	public static int checkDivision(int a, int b)
	{
		int retval;
		try
		{
			retval = a/b;
		}
		catch(ArithmeticException e)
		{
			System.out.println("-------------------------");
			System.out.println("You have messed up");
//			System.out.println(e.getMessage());
			System.out.println(e.toString());
			retval = -1;
		}
		
		return retval;
	}

}
