package prog1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
		File fil1 = new File("file.txt");
		Scanner scan1 = new Scanner(fil1);
		Scanner scan2 = new Scanner(System.in);
		
		
		String name;
		String ID;
		int pin;
		double amount;
		int choice;
		double newAmount;
		
		aPersonClass[] anArray = new aPersonClass[10];
		int logSize = 0;
		
		while(scan1.hasNext()){
			
			name = scan1.next();
			ID = scan1.next();
			pin = scan1.nextInt();
			amount = scan1.nextDouble();
			anArray[logSize] = new aPersonClass(name, ID, pin, amount);
			logSize++;
			
		}
		
		choice = menu(scan2); //priming read
		System.out.println(choice);
		while(choice != 2){
			
			switch(choice){
			
			case 1:
				newAmount = anArray[0].findAmount();
				System.out.println("Your amount is " + newAmount);
				break;
			default: System.out.println("Error");
			break;
			
			}
			
			choice = menu(scan2);
			
		}

	}
	
	public static int menu(Scanner scan2){
		
		int choice;
		System.out.println("Enter the number of your choice");
		System.out.println("1) Find your balance");
		System.out.println("2) Quit the Program");
		
		choice = scan2.nextInt();
		return (choice);
		
	}

}
