package bankProgramV2;

public class SavingsAccount extends BankAccount {
	
	static int count = 12000;
	
	public SavingsAccount(String name, double balance, String userID, String pass) {
		super(name, balance, userID, pass, count);
		count++;
	}
	
	public SavingsAccount(String name, double balance, String userID, String pass, int accNumber) {
		super(name, balance, userID, pass, accNumber);
		count = accNumber+1;
	}
}
