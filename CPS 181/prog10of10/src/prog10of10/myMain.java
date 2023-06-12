package prog10of10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException{
		File fil1 = new File("infile.txt");
		Scanner scan1 = new Scanner(fil1);
		PrintWriter out = new PrintWriter("sorted.txt");
		
		int[] myIntArray = toArray(scan1);
		int[] myFinalArray = selectionSort(myIntArray);
		
		for(int i = 0; i < myFinalArray.length ; i++){
			System.out.print(myFinalArray[i] + " ");
			out.print(myFinalArray[i] + " ");
		}

	}
	
	public static int[] toArray(Scanner scan){
		int physize = 15;
		int logsize = 0;
		int[] ints = new int[physize];
		
		while(scan.hasNext()){
			ints[logsize] = scan.nextInt();
			logsize++;
		}
		
		return ints;
			
	}
	
	public static int[] selectionSort(int[] myInts){
		
		for(int i = myInts.length - 1; i > 0; i--){
			
			int element = 0;
			
			for(int j = 1; j <= i; j++){
				
				if(myInts[j] < myInts[element]){
					element = j;
				}
			}
			int temp = myInts[element];
			myInts[element] = myInts[i];
			myInts[i] = temp;
		}
		return myInts;
	}
		
}

