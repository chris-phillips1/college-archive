package inFix1;

public class Stack {

	int[] arr = new int[10];
	int logSize = 0;
	
	public void push(int i)
	{
		arr[logSize] = i;
		logSize++;
		System.out.println(i + " has been pushed.");
	}
	
	public String toString()
	{
		String retval = "";
		
		for(int i = 0; i < logSize; i++)
		{
			retval += arr[i] + "\n";
		}
		
		return retval;
	}
}
