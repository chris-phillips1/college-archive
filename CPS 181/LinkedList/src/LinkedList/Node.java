package LinkedList;

public class Node {
	
	String firstName;
	String lastName;
	int age;
	Node forward;
	Node back;
	
	public Node(String fn, String ln, int a)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.age = a;
	}
	
}
