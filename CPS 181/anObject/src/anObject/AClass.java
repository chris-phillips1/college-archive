package anObject;

import java.util.Scanner;

public class AClass {
	
	private String name;
	private String userID;
	private int pin;
	private double amt;
	
public AClass(String string, String string2, int i, double d) {
		this.name = string;
		this.userID = string2;
		this.pin = i;
		this.amt = d;
	}
	//	public double getAmt() {
//		return amt;
//	}
//	public void setAmt(double amt) {
//		this.amt = amt;
//	}
//	public String getUserID() {
//		return userID;
//	}
//	public void setUserID(String userID) {
//		this.userID = userID;
//	}
	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	private int getPin() {
//		return pin;
//	}
//	public void setPin(int pin) {
//		this.pin = pin;
//	}
	public double getTheAmt() {
		Scanner kb = new Scanner(System.in);
		
		double retval = 0.0;
		String testID;
		int testPin;
		
		System.out.print("userID: ");
		testID = kb.next();
		
		if(testID.equals(userID)){
			
			System.out.print("PIN: ");
			testPin = kb.nextInt();
			
			if(testPin == pin){
				retval = amt;
			}
		}
		
		return retval;
	}
}
