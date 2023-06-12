package bankProgram;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {

		File bankInfo = new File("userData.txt");
		Scanner fileScan = new Scanner(bankInfo);
		Scanner userInput = new Scanner(System.in);
		PrintWriter log = new PrintWriter("log.txt");

		String firstName;
		String middleName;
		String lastName;
		String userID;
		int pinNumber;
		double balance;
		int selection = 1;

		final int phySize = 100;
		int logSize = 0;

		BankUser[] users = new BankUser[phySize];

		while (fileScan.hasNext()) {

			firstName = fileScan.next();
			middleName = fileScan.next();
			lastName = fileScan.next();
			userID = fileScan.next();
			pinNumber = fileScan.nextInt();
			balance = fileScan.nextDouble();

			users[logSize] = new BankUser(firstName, middleName, lastName, userID, pinNumber, balance);
			logSize++;

		}
 
		boolean check = true;
		while(check){
		
		System.out.print("userID: ");
		Scanner scan3 = new Scanner(System.in);
		String testUserID = scan3.nextLine();

		System.out.print("PIN: ");
		Scanner scan4 = new Scanner(System.in);
		int testPinNumber = scan4.nextInt();
		for (int i = 0; i < logSize; i++) {
			
			if (users[i].validateUserID(testUserID)) {
				
				if (users[i].validatePinNumber(testPinNumber)) {
					
					DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
					Calendar calObj = Calendar.getInstance();
					log.println("Successful Login " + df.format(calObj.getTime()));
					
					check = false;

					selection = operationsMenu(userInput);

					while (selection != 4) {

						switch (selection) {

						case 1:
							users[i].deposit(log);
							break;
						case 2:
							users[i].withdraw(log);
							break;
						case 3:
							users[i].checkBalance();
							break;
						default:
							System.out.println("Error!");
							break;
							
						}
						selection = operationsMenu(userInput);	
					}
					userInput.close();	
				}
			}
		}
		
		if(selection != 4){
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Calendar calObj = Calendar.getInstance();
			log.println("Failed Login " + df.format(calObj.getTime()));
			
		}
	}
	log.close();
}
	
	public static int operationsMenu(Scanner userInput) {

		int mySelection = -999;

		System.out.println("Choose one of the following:");
		System.out.println("1) Deposit");
		System.out.println("2) Withdraw");
		System.out.println("3) Check Balance");
		System.out.println("4) Exit Program");

		mySelection = userInput.nextInt();

		if (mySelection > 4 || mySelection < 1)
			mySelection = 4;

		return (mySelection);
	}

}
