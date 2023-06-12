package Exam1;

import java.util.Scanner;

public class MyMain {

	public static void main(String[] args) {
		
		myStack ms = new myStack();
		
		int mySelection;
		mySelection = menu();
		Scanner scan2 = new Scanner(System.in);
		double pushDouble;
		
		while(mySelection != 6){
			
			switch(mySelection){
			case 1:
				System.out.print("Enter the double you would like to push: ");
				pushDouble = scan2.nextDouble();
				ms.push(pushDouble);
				break;
			case 2:
				System.out.println(ms.pop());
				break;
			case 3:
				System.out.println(ms.top());
				break;
			case 4:
				System.out.println(ms.isEmpty());
				break;
			case 5:
				System.out.println(ms.isFull());
				break;
			default:
				System.out.println("Error");
				break;
			}
			mySelection = menu();
		}
	}
	
	public static int menu()
	{
		Scanner scan1 = new Scanner(System.in);
		int selection;
		
		System.out.println("What would you like to do?");
		System.out.println("1) Push");
		System.out.println("2) Pop");
		System.out.println("3) Top");
		System.out.println("4) isEmpty");
		System.out.println("5) isFull");
		System.out.println("6) Close Program");
		
		selection = scan1.nextInt();
		
		return selection;
		
	}

}
