package BInarySearchTreeFinal;

public class BSTree implements BSTInterface {

	static int leafCount;
	BSTNode root;
	BSTNode testNode;
	int workAround = 0;
	
	BSTree()
	{
		root = null;
	}
	
	public void insert(String s) {
		BSTNode newNode = new BSTNode(s);
		
		if(root == null) {
			root = newNode;
		} 
		else {
			root = addInOrder(root, newNode);
		}
	}

	public String search(String key)
	{
		boolean result = searchFor(key, root);
		
		if(result)
			return ("The term " + key.toUpperCase() + " was found in the tree.");
		else
			return ("The term " + key.toUpperCase() + " was not found in the tree.");
	}
	
	public boolean searchFor(String key, BSTNode testNode) {
		
		return testNode != null && (testNode.word.equals(key) || 
				searchFor(key, testNode.leftTree) ||
				searchFor(key, testNode.rightTree));
	}

	public void print() {
		if(root == null)
			System.out.println("The tree is empty.");
		else
			printInOrder(root);
	}
	
	public void printInOrder(BSTNode tree) {
		if(tree != null) {
			printInOrder(tree.leftTree);
			System.out.println(tree.word);
			printInOrder(tree.rightTree);
		}
		return;
	}
	
	public BSTNode addInOrder(BSTNode focus, BSTNode newNode) {
		
		int compareValue = 0;
		
		if(focus == null)
			focus = newNode;
		
		if(focus != null) {
			compareValue = newNode.word.compareTo(focus.word);
		}
		if(compareValue < 0) {
			focus.leftTree = addInOrder(focus.leftTree, newNode);
		} else if(compareValue > 0) {
			focus.rightTree = addInOrder(focus.rightTree, newNode);
		}
		return (focus);
	}
	
	public String leaves() {
		
		testNode = root;
		
		return countLeaves(testNode) + " leaves";
	}
	
	public int countLeaves(BSTNode testNode)
	{
	    if(testNode==null) {
	        return 0;
	    }
	    if(testNode.leftTree == null && testNode.rightTree == null) {
	        return 1;
	    }
	    return countLeaves(testNode.leftTree) + countLeaves(testNode.rightTree);
	}

}
