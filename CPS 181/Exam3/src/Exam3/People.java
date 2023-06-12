package Exam3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class People {

	public People()
	{
		
	}
	
	public People(File myFile) throws IOException
	{
		Scanner scan1 = new Scanner(myFile);
		
		while(scan1.hasNext())
		{
			System.out.println(scan1.next());
		}
	}
}
