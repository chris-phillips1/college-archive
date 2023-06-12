package inFixtoPostFix;


public class Stack {
	
	final int phySize = 20;
	char[] myArray = new char[phySize];
	int logSize = 0;
	int top = 0;
	
	Stack()
	{
		
	}
	
	Stack(char[] info)
	{
		myArray = info;
	}
	
	Stack(String s)
	{
		int count = 0;
		char myChar;
		
		while(count < s.length())
		{
			myChar = s.charAt(count);
			System.out.println(myChar);
			myArray[count] = myChar;
			top++;
			count++;
		}
	}
	
	public void push(char c)
	{
		myArray[top] = c;
//		System.out.print("'" + myArray[top] + "'" + " at index ");
//		System.out.println(top);
		top++;
	}
	
	public char pop()
	{
		char retval;
		
		retval = myArray[top - 1];
//		System.out.println("Pop: " + myArray[top - 1]);
		top--;
		
		return retval;
	}
	
	public char top()
	{
		if(top >= 1)
		{
//			System.out.println("Top: " + myArray[top - 1]);
			return myArray[top -1];
		}
//		System.out.println("EMPTY STACK");
		return 'N';
			
	}
	
	public boolean isEmpty()
	{
		if(top < 1)
			return true;
		else
			return false;
	}
	
	public boolean isFull()
	{
		if(top >= phySize)
			return true;
		else
			return false;
	}
}
