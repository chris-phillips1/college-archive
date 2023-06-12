package prog7of10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
		PrintWriter out = new PrintWriter("outfile.txt");
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Enter Numbers (Press Control + D when done): " );
		
		final int physize = 1000;
		int[] anarray = new int[physize];
		int logsize = 0;
		int min = 9999;
		int max = 0;
		int count = 0;
		int sum = 0;
		int num;
		
		while (scan2.hasNext()) {

			num = scan2.nextInt();
			anarray[logsize] = num;
			logsize++;
			min = calculateMin(num, min);
			max = calculateMax(num, max);
			count++;
			sum += num;
		}
		
		System.out.println("Sum: " + sum);
		System.out.println("Count: " + count);
		System.out.println("Average: " + (sum/count));
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		
		out.println("Sum: " + sum);
		out.println("Count: " + count);
		out.println("Average: " + (sum/count));
		out.println("Min: " + min);
		out.println("Max: " + max);
		
		out.close();
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
	
}
