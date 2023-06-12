package prog8of10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
		File fil1 = new File("infile.txt");
		PrintWriter out = new PrintWriter("outfile.txt");
		Scanner scan1 = new Scanner(fil1);
		
		final int physize = 100;
		String[] strings = new String[physize];
		int logsize = 0;
		int asciiNumber;
		String string1;
		String string2;
		char character1;
		
		while(scan1.hasNext()){
			
			string1 = scan1.nextLine();
			strings[logsize] = string1;
			logsize++;
			
		}
		
		for(int i = 0; i < logsize; i++){
			string2 = strings[i];
			
			character1 = string2.charAt(0);
			asciiNumber = (int)character1;

			
			if(asciiNumber > 96 && asciiNumber < 123){
				string2 = string2.toUpperCase();
			}
			else if(asciiNumber > 64 && asciiNumber < 91){
				string2 = string2.toLowerCase();
			}
			
			strings[i] = string2;
			
		}
		
		for(int j = 0; j < logsize; j++){
			out.println(strings[j]);
		}
		
		out.close();
	}

}
