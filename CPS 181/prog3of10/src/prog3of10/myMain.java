package prog3of10;

import java.util.ArrayList;
import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {

		ArrayList<Integer> inputs = new ArrayList<Integer>();
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter numbers (Press Control + D when you're done): ");

		while (scan1.hasNext())
			inputs.add(scan1.nextInt());

		int sum = 0;
		for (int a : inputs)
			sum += a;

		System.out.println("Sum: " + sum);
		System.out.println("Nuber of Values: " + inputs.size());
		System.out.println("Average: " + sum / inputs.size());

		int highest = inputs.get(0);

		for (int i = 0; i < inputs.size(); i++) {

			if (inputs.get(i) > highest) {

				highest = inputs.get(i);
			}
		}

		System.out.println("Highest Value: " + highest);

		int lowest = inputs.get(0);

		for (int i = 0; i < inputs.size(); i++) {

			if (inputs.get(i) < lowest) {

				lowest = inputs.get(i);
			}

		}

		System.out.println("Lowest Value: " + lowest);

	}

}
