package Nodes;

public class LinkedList implements LList {

	private ANode head;
	private ANode tail;

	public void LinkedList() {
		head = null;
		tail = null;
	}

	@Override
	public void add2Rear(ANode temp) 
	{
		if(head == null)
		{
			head = temp;
			tail = temp;
		}
		else
		{
			tail.forward = temp;
			tail = temp;
		}
	}

	@Override
	public ANode removeFirst() {
		ANode temp = null;
		if(head != null)
		{
			temp = head;
			head = head.forward;
			temp.forward = null;
		}
		return temp;
	}

	@Override
	public void add2Front(ANode temp) {
		if(head == null)
		{
			head = temp;
			tail = temp;
		}
		else
			temp.forward = head;
			head = temp;
	}

	public boolean isEmpty() {
		boolean retval = false;
		if (head == null && tail == null)
			retval = true;
		if(head == null && tail != null)
			System.out.println("Error");
		return (retval);
	}

	public String toString() {
		String retval = "";
		ANode temp = head;
		while (temp != null) {
			retval = retval + temp.name + " ";
			temp = temp.forward;
		}
		return (retval);
	}


}

