package inFixtoPostFix;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {

		File fil1 = new File("infoFile.txt");
		Scanner scan1 = new Scanner(fil1);

		Queue Q1 = new Queue();
		Queue Q2 = new Queue();
		Stack S1 = new Stack();

		final int phySize = 20;
		int logSize = 0;
		char[] myArray = new char[phySize];

		while (scan1.hasNextLine())  
		{
			String in = scan1.nextLine();

			for (int i = 0; i < in.length(); i++) 
			{
				myArray[i] = in.charAt(i);
				Q1.addToRear(myArray[i]);
			}
			
			while ( !(Q1.isEmpty()) ) 
			{
				if ( ((int) Q1.front() > 96 && (int) Q1.front() < 123) || ((int) Q1.front() > 64 && (int) Q1.front() < 91) ) 
				{
//					System.out.println(Q1.front());
//					System.out.println("Test 1");
					Q2.addToRear(Q1.front());
//					System.out.println("Test 2: " + Q2.front());
					Q1.removeFront();
//					System.out.println("Test 3");
//					System.out.println(Q2.front());
//					System.out.println(Q2.removeFront());
//					System.out.println(Q2.front());
				}
				else if(Q1.front() == '+' || Q1.front() == '-' || Q1.front() == '*' || Q1.front() == '/')
				{
					S1.push(Q1.front());
					Q1.removeFront();
				}
				else
					Q1.removeFront();
			}
			
			Q2.addToRear(S1.top());
			S1.pop();
			Q2.addToRear(S1.top());
			System.out.println(Q2.front());
			Q2.removeFront();
			System.out.println(Q2.front());
			Q2.removeFront();
			System.out.println(Q2.front());
			Q2.removeFront();
			System.out.println(Q2.front());
			Q2.removeFront();
			System.out.println(Q2.front());
		}
	}

}
