package Nodes;

public class ANode {
	
	String name;
	ANode forward;
	ANode  back;
	
	public ANode(String n)
	{
		this.name = n;
		forward = null;
		back = null;
	}
	
}

