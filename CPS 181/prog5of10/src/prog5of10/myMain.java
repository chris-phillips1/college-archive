package prog5of10;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
	File fil1 = new File("infile.txt");
	Scanner scan1 = new Scanner(fil1);
	final int physize = 100;
	int[] anArray = new int[physize];
	int num = 0;
	int logsize = 0;
	int count = 0;
	int min = 11;
	int max = 0;
	double sum = 0.0;
	
	while(scan1.hasNext()){
		
		num = scan1.nextInt();
		anArray[logsize] = num;
		logsize++;
		count++;
		min = calculateMin(num, min);
		max = calculateMax(num, max);
		sum = calculateSum(num, sum);
		
	}
	System.out.println("Sum: " + sum);
	System.out.println("Number of Entries: " + count);
	System.out.println("Average: " + (int)((sum/count)+0.5));
	System.out.println("Highest value: " + max);
	System.out.println("Lowest valule: " + min);
	

	}
	
	public static int calculateMin(int newNum, int min) {

		if (newNum < min)
			min = newNum;
		return min;

	}

	public static int calculateMax(int newNum, int max) {

		if (newNum > max)
			max = newNum;
		return max;

	}
	
	public static int calculateSum(int newNum, double oldSum){
		return ((int)(oldSum + 0.5) + newNum);
	}

}
