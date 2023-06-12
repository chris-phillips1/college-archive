/* Name: Chris Phillips
 * Course: CPS 470
 * Assignment: Homework 4
 * Description: The purpose of this code is to demonstrate how multi-threading can be used in a modern program.
 *              Multi-threading helps to speed up the total execution time of a program and allows for various
 *              tasks to completed at the same time by executing all threads in parallel.
 */
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.Scanner;

class hw4
{
   public static void main (String [] args) throws FileNotFoundException, InterruptedException
   {
	ArrayList<Integer> nums = processFiles();
	int avg, min, max;

	CalculateAverage avgThread = new CalculateAverage(nums);
	CalculateMinimum minThread = new CalculateMinimum(nums);
	CalculateMaximum maxThread = new CalculateMaximum(nums);

	avgThread.start();
	avgThread.join();
	avg = avgThread.getAverage();

	minThread.start();
	minThread.join();
	min = minThread.getMin();

	maxThread.start();
	maxThread.join();
	max = maxThread.getMax();

	System.out.println("The average value is " + avg);
	System.out.println("The minimum value is " + min);
	System.out.println("The maximum value is " + max);
   }


   public static ArrayList<Integer> processFiles() throws FileNotFoundException
   {
		ArrayList<Integer> processedFile = new ArrayList<Integer>();

		File file = new File("input.txt");
		Scanner scan = new Scanner(file);

		String currentLine;
		while(scan.hasNext())
		{
			currentLine = scan.nextLine();

			if(currentLine.contains(" ")) {
				String[] items = currentLine.split(" ");
				for(String item : items)
				{
					processedFile.add(Integer.parseInt(item));
				}
			} else {
				processedFile.add(Integer.parseInt(currentLine));
			}
		}
		scan.close();

		return processedFile;
   }
}

class CalculateAverage extends Thread
{
	private ArrayList<Integer> nums;
	private volatile int average;

	public CalculateAverage(ArrayList<Integer> nums)
	{
		this.nums = nums;
	}

   public void run()
   {
	   int sum = 0;
	   for(int num : nums)
	   {
			sum += num;
	   }

	   average = sum / nums.size();
   }

   public int getAverage()
   {
		return average;
   }
}

class CalculateMinimum extends Thread
{
	private ArrayList<Integer> nums;
	private volatile int minimum;

	public CalculateMinimum(ArrayList<Integer> nums)
	{
		this.nums = nums;
	}

	public void run()
	{
		int minNum = nums.get(0);

		for(int currentNum : nums)
		{
			if(currentNum < minNum)
			{
				minNum = currentNum;
			}
		}

		minimum = minNum;
	}

	public int getMin()
	{
		return minimum;
	}
}

class CalculateMaximum extends Thread
{
	private ArrayList<Integer> nums;
	private volatile int maximum;

	public CalculateMaximum(ArrayList<Integer> nums)
	{
		this.nums = nums;
	}

	public void run()
	{
		int maxNum = nums.get(0);

		for(int currentNum : nums)
		{
			if(currentNum > maxNum)
			{
				maxNum = currentNum;
			}
		}

		maximum = maxNum;
	}

	public int getMax()
	{
		return maximum;
	}
}