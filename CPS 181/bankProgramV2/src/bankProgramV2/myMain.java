package bankProgramV2;

import java.io.IOException;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException, InterruptedException {

		TheBank bank1 = new TheBank();
		bank1.open();
		
		int selection = menu();
		
		while (selection != 4) {
			
			switch (selection) {
			case 1:
				addAccount(bank1);
				break;
			case 2:
				checkBalance(bank1);
				break;
			case 3:
				depositFunds(bank1);
				break;
			default:
				System.out.println("Error");
				break;
			}
			selection = menu();
		}
		
		bank1.close();
	}
	
	private static void depositFunds(TheBank bank)
	{
		Scanner input = new Scanner(System.in);
		int myAccountNumber;
		double addAmt;
		
		System.out.print("Enter your Account Number: ");
		myAccountNumber = input.nextInt();
		BankAccount myAcc = findAccountNumber(myAccountNumber, bank);
		if(myAcc.getBalance() >= 1)
		{
			System.out.print("Enter the amount you'd like to deposit: ");
			addAmt = input.nextDouble();
			myAcc.addToBalance(addAmt);
			System.out.println("$" + addAmt + " added to the account");
		}
		else
			System.out.println("Not a Valid Account Number");
	}
	
	private static void addAccount(TheBank bank) {
		Scanner newAcc = new Scanner(System.in);
		String AccType;
		System.out.println("What type of account would you like to create?");
		AccType = newAcc.nextLine();
		
		if(AccType.equals("Savings"))
		{
			String name;
			double balance;
			String userID;
			String pass;
			
			System.out.print("Enter the name: ");
			name = newAcc.nextLine();
			System.out.print("Enter the balance: ");
			balance = newAcc.nextDouble();
			System.out.print("Enter the userID: ");
			userID = newAcc.next();
			System.out.print("Enter the pass: ");
			pass = newAcc.next();
			
			
			BankAccount theAcc = new SavingsAccount(name, balance, userID, pass);
			bank.newAccount(theAcc);
		}
		else if(AccType.equals("Checking"))
		{
			String name;
			double balance;
			String userID;
			String pass;
			
			System.out.print("Enter the name: ");
			name = newAcc.nextLine();
			System.out.print("Enter the balance: ");
			balance = newAcc.nextDouble();
			System.out.print("Enter the userID: ");
			userID = newAcc.next();
			System.out.print("Enter the pass: ");
			pass = newAcc.next();
			
			
			BankAccount theAcc = new CheckingAccount(name, balance, userID, pass);
			bank.newAccount(theAcc);
		}
		else
			System.out.println("Not a valid account type");
		
	}
	
	private static void checkBalance(TheBank bank) {
		Scanner inputID = new Scanner(System.in);
		int myAccountNumber;
		
		System.out.print("Enter your Account Number: ");
		myAccountNumber = inputID.nextInt();
		BankAccount myAcc = findAccountNumber(myAccountNumber, bank);
		if(myAcc.getBalance() >= 0)
			System.out.println("Your balance is $" + myAcc.getBalance());
		else
			System.out.println("Not a Valid Account Number");
		
	}

	private static BankAccount findAccountNumber(int testAccountNumber, TheBank bank) { 
		BankAccount myBankAccount = bank.getAccount(testAccountNumber);
		return myBankAccount;
	}

	public static int menu() {
		Scanner menuPick = new Scanner(System.in);
		int selection;
		System.out.println("----------------------------------");
		System.out.println("What do you want to do?");
		System.out.println("1) Add an Account");
		System.out.println("2) Check Balance");
		System.out.println("3) Deposit Funds");
		System.out.println("4) Close Bank");

		selection = menuPick.nextInt();

		return selection;
	}

}
