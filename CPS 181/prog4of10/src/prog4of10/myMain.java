package prog4of10;

import java.util.ArrayList;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {

		String original = "";
		String reverse = "";
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter strings (exit value is an empty string): ");

		while (scan1.hasNext()) {

			String input = scan1.nextLine();
			if (input != "") {
				original = input;
				System.out.println(original);
				int length = original.length();

				for (int i = length - 1; i >= 0; i--) {
					reverse = reverse + original.charAt(i);
					System.out.print(reverse);
					reverse = "";
				}
			}

		}

	}

}
