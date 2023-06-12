package filetest1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myClass {

	public static void main(String[] args) throws IOException {
		
		File in = new File("infile.txt");
		Scanner scan1 = new Scanner(in);
		PrintWriter out = new PrintWriter(new FileWriter("outfile.txt"));
		int anInteger;
		
		header();
		while(scan1.hasNext()){
			
			anInteger = scan1.nextInt();
			System.out.println(anInteger);
			out.println(anInteger);
	
		}
		
		out.close();

	}
	
	public static void header(){
		System.out.println("Written by Chris P.");
	}
}
