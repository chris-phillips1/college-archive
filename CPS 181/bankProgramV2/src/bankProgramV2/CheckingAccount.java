package bankProgramV2;

public class CheckingAccount extends BankAccount {

	static int count = 57000;
	
	public CheckingAccount(String name, double balance, String userID, String pass) {
		super(name, balance, userID, pass, count);
		count++;
	}
	
	public CheckingAccount(String name, double balance, String userID, String pass, int accNumber) {
		super(name, balance, userID, pass, accNumber);
		count = accNumber+1;
	}
}
