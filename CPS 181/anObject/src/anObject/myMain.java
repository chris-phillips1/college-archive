package anObject;

import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {
		
		AClass[] anArray = new AClass[10];
		AClass obj = new AClass("Sue", "sue1", 1234, 2000.00);
		double GottenAmt; 
		
		if(menu() == 1)
			System.out.println(GottenAmt = obj.getTheAmt());
		else
			System.out.println("Enter the number 1.");
//		System.out.println(obj.getName());
//		System.out.println(obj.getUserID());
//		System.out.println(obj.getPin());
		
	}
	
	public static int menu(){
		Scanner kb = new Scanner(System.in);
		int retval = 0;
		System.out.println("Select your choice");
		System.out.println("1) Check Balance");
		retval = kb.nextInt();
		return(retval);
	}

}
