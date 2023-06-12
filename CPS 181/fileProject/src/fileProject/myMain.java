package fileProject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {

		File f1 = new File("myFile.txt");
		Scanner scan1 = new Scanner(f1);
		PrintWriter prw = new PrintWriter("outfile.txt");
		String name;
		String[] names = new String[20];
		int logSize = 0;
		int age;
		
		while (scan1.hasNext()) {

			names[logSize] = scan1.next();
			age = scan1.nextInt();
			names[logSize] = names[logSize] + " is " + age + " years old";
			System.out.println("name = " + names[logSize]);
			prw.println(names[logSize]);
			logSize++;
		}
/*		myMethod(logSize, names);
		for(int num = 0; num < logSize; num++){
			System.out.println(names[num]);
		}
		System.out.println("In the main logSize = " + logSize);
*/		prw.close();
	}
	
/*	public static void myMethod(int logSize, String names[] ){ 
		
		//logSize = 2;
		System.out.println("In the method logSize = " + logSize);
		names[2] = "Beth";
	}
*/
}
