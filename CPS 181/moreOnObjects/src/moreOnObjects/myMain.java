package moreOnObjects;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
		final int physize = 100;
		String aName;
		int logsize = 0;
		
		File fil1 = new File("nameFile.txt");
		Scanner scan1 = new Scanner(fil1);
		aPersonClass[] anArray = new aPersonClass[physize];
		
		
		while(scan1.hasNext()){ 
			
			aName = scan1.next();
			anArray[logsize] = new aPersonClass(aName);
			System.out.println(logsize + " " + anArray[logsize].getName());
			logsize++;
			
		}
		
		System.out.println(menu());

	}
	
	public static int menu(){
		return -1;
	}

}
