package tryCatch;

public class MyMain {

	public static void main(String[] args)
	{
		
		int num1 = 10;
		int num2 = 0;
		int total;
		total = goesInto(num1, num2);
		System.out.println(num1 + " / " + num2 + " is " + total);
		
		
		
	}
	
	public static int goesInto(int x, int y)
	{
		int retval = -1;
		try {
			
		retval = x / y;
		
		}
		catch(ArithmeticException e)
		{
			System.out.println("Arithmetic Exception: Divide by zero");
		}

		System.out.println("Continue with life");
		return retval;
	}
}
