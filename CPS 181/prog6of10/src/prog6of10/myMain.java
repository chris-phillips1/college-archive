package prog6of10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) throws IOException {
		
		File fil1 = new File("in.txt");
		Scanner scan1 = new Scanner(fil1);
		PrintWriter odd = new PrintWriter("odd.txt");
		PrintWriter even = new PrintWriter("even.txt");
		
		while(scan1.hasNext()){
			int num = scan1.nextInt();
			if(num % 2 == 0){
				even.println(num);
			}
			else
				odd.println(num);
		}
		even.close();
		odd.close();
	}

}
