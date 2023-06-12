package BInarySearchTreeFinal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BSTMain {

	public static void main(String[] args) throws IOException {

		int wordCount = 0;
		String inString;
		PrintWriter output = new PrintWriter("outfile.txt");
		File inputFile = new File("infile.txt");
		Scanner scan = new Scanner(inputFile);
		BSTree tree = new BSTree();
		
		while(scan.hasNext())
		{
			inString = scan.nextLine();
			tree.insert(inString);
			wordCount++;
		}
		
		output.println(tree.leaves());
		output.println(tree.search("obfuscate"));
		
//		tree.print();
//		System.out.println(wordCount + " words");
		
		output.close();
	}

}
