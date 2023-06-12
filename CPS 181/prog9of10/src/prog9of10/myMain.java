package prog9of10;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {

		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter Numbers (Press Control + D when done): " );
		PrintWriter out = new PrintWriter("outfile.txt");
		
		int physize = 1000;
		int[] intArray = new int[physize];
		int logsize = 0;
		
		while(scan1.hasNext()){
			int num = scan1.nextInt();
			intArray[logsize] = num;
			logsize++;
		}
		System.out.println(calculate(intArray, logsize));
		out.println(calculate(intArray,logsize));
		out.close();
	}
	
	public static String calculate(int[] anArray, int logsize){
		int sum = 0;
		int max = -999;
		int min = 999;
		
		for(int i = 0; i < logsize; i++){
			sum += anArray[i];
			
			if(anArray[i] > max)
				max = anArray[i];
			else if(anArray[i] < min)
				min = anArray[i];
		}
		
		int average = (sum/logsize);
		
		return "\n" + "Sum: " + sum + "\n" + "Average: " + average + "\n" + "Max: " + max
				+ "\n" + "Min: " + min;
		
	}
}
