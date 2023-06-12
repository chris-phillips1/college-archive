package prog1;

import java.util.Scanner;

public class aPersonClass {
	
	Scanner scan2 = new Scanner(System.in);
	private String name;
	private String ID;
	private int pin;
	private double amount;
	
	public aPersonClass(String name2, String iD2, int pin2, double amount2) {
		
		this.name = name2;
		this.ID =iD2;
		this.pin = pin2;
		this.amount = amount2;
		System.out.println(name + " " + pin);
		
	}
	
	public double findAmount(){
		
		int testPin;
		double retAmount = 0.0;
		System.out.println("What is your pin?");
		testPin = scan2.nextInt();
		if(testPin == pin)
			retAmount = amount;
		else
			retAmount = -999.99;
		
		return(retAmount);
		
		
	}
	
	
	
}
