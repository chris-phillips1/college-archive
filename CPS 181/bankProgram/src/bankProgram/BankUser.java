package bankProgram;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class BankUser {

	private String firstName;
	private String middleName;
	private String lastName;
	private String userID;
	private int pinNumber;
	private double balance;
	
	public BankUser(String firstName2, String middleName2, String lastName2, String userID2, int pinNumber2,
			double balance2) {
		
		this.firstName = firstName2;
		this.middleName = middleName2;
		this.lastName = lastName2;
		this.userID = userID2;
		this.pinNumber = pinNumber2;
		this.balance = balance2;
		
	}
		
	public void deposit(PrintWriter log){ //eventually want to be logging this with a timestamp
		Scanner depositScan = new Scanner(System.in);
		
		System.out.println("Enter the amount you'd like to deposit: ");
		double depositNum = depositScan.nextDouble();
		this.balance += depositNum;
		DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		Calendar calObj = Calendar.getInstance();
		log.println("Deposit " + df.format(calObj.getTime()));
	
		
	}
	
	public void withdraw(PrintWriter log){ //eventually want to be logging this with a timestamp
		Scanner withdrawScan = new Scanner(System.in);
		System.out.println("Enter the amount you'd like to withdraw: ");
		double withdrawNum = withdrawScan.nextDouble();
		
		if(withdrawNum <= this.balance){
			this.balance -= withdrawNum;
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Calendar calObj = Calendar.getInstance();
			log.println("Withdrawal " + df.format(calObj.getTime()));
		}
		else
			System.out.println("Not enough funds to do that.");	
	}
	
	public void checkBalance(){
		System.out.println(this.balance);
	}
	
	public boolean validateUserID(String testID){
		boolean retval = false;
		
		if(testID.equals(this.userID)){
			retval = true;
		}
		
		return retval;
		
	}
	
	public boolean validatePinNumber(int testPin){
		boolean retval = false;
		
		if(testPin == this.pinNumber){
			retval = true;
		}
		
		return retval;
	}
	
}
