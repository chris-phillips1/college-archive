package Exam1;

public class myStack implements Stack {

	public void push(Double num) 
	{
		arr.add(num);		
	}

	public Double pop() 
	{
		if(!this.isEmpty())
		{
			double retval = arr.remove(arr.size() - 1);
			return retval;
		}
		return -999.9;
	}

	public Double top() 
	{
		if(!this.isEmpty())
		{
			double retval = arr.get(arr.size() - 1);
			return retval;
		}
		return -999.9;
	}

	public boolean isEmpty() 
	{
		boolean retval = arr.isEmpty();
		return retval;
	}

	public boolean isFull() 
	{
		return false;
	}

}
