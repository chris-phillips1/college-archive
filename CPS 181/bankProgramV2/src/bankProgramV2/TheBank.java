package bankProgramV2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TheBank {
	
	File fil1 = new File("accounts.txt");
	Scanner sc = new Scanner(fil1);
	ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	
	String name;
	double balance;
	String userID;
	String pass;
	int accNumber;
	
	public TheBank() throws IOException
	{	
		
	}
	
	public void open()
	{
		BankAccount p;
		System.out.println("Bank Started");
		while(sc.hasNext())
		{
			name = sc.next();
			balance = sc.nextDouble();
			userID = sc.next();
			pass = sc.next();
			accNumber = sc.nextInt();
			
			if(accNumber >= 12000 && accNumber < 57000)
				p = new SavingsAccount(name, balance, userID, pass, accNumber);
			else 
				p = new CheckingAccount(name, balance, userID, pass, accNumber);
			
			accounts.add(p);
		}
	}
	
	public void newAccount(BankAccount ba)
	{
		accounts.add(ba);
	}
	
	public BankAccount getAccount(int testAccountNumber)
	{
		BankAccount retAccount = new CheckingAccount("null", -1, "null", "null", 0);
		
		for(BankAccount a: accounts)
		{
			if(a.getAccNumber() == testAccountNumber)
			{
				retAccount = a;
			}
		}
		
		return retAccount;
	}
	
	public void close() throws IOException, InterruptedException
	{
		PrintWriter accDoc = new PrintWriter("accounts.txt");
		System.out.println("Closing the bank...");
		
		for(BankAccount a : accounts)
		{
			accDoc.print(a.getName() + " ");
			accDoc.print(a.getBalance() + " ");
			accDoc.print(a.getUserID() + " ");
			accDoc.print(a.getPass() + " ");
			accDoc.println(a.getAccNumber());
		}
		Thread.sleep(500);
		System.out.println("Done!");
		accDoc.close();
		
	}
	
	
}
