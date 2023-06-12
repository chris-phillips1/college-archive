package objectTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class myMain {
	
	/* 
	 * @author: Chris Phillips
	 * date 13: september 2016
	 * purpose: object example
	 */

	public static void main(String[] args) {
		
		date();
		
		Record person1 = new Record();
		Record person2 = new Record();
	
		person1.setName("John");
		person1.setAge(26);
		person2.setName("Chris");
		person2.setAge(18);
		
	
		System.out.println(person1.toString());
		System.out.println(person2.toString());
		
		
		

	}
	
	public static void date(){
		
		DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		Calendar calObj = Calendar.getInstance();
		System.out.println(df.format(calObj.getTime()));
		
	}
	
	public static int menu(){
		Scanner keyboard = new Scanner(System.in);
		int choice;
		
		System.out.println("Enter the number of your choice: ");
		System.out.println("1) End the program");
		System.out.println("2) Go to jail, go directly to jail");
		
		choice = keyboard.nextInt();
		return choice;
		
	}

}
