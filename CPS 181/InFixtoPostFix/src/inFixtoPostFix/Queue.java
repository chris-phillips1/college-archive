package inFixtoPostFix;

public class Queue {

	final int phySize = 20;
	char[] myQueue = new char[phySize];
	private int logSize = 0;
	private int front = 0;
	
	Queue()
	{
		
	}
	
	public void addToRear(char c)
	{
		myQueue[logSize] = c;
		logSize++;
	}
	
	public char removeFront()
	{	
		char retval = myQueue[front];
		front++;
		return retval;
	}
	
	public char front()
	{
		return myQueue[front];
	}
	
	public boolean isEmpty()
	{
		if(logSize <= front)
			return true;
		else
			return false;
	}
	
	public boolean isFull()
	{
		if(logSize >= phySize)
			return true;
		else
			return false;
	}
}
