package BinarySearchTree;

import java.util.Scanner;

public class BSTree {
	
	BSTNode root;
	
	BSTree()
	{
		root = null;
	}
	
	public void inOrderPrint()
	{
		if(root == null)
		{
			System.out.println("The tree is empty.");
		}
		else
			printInOrder(root);
	}
	
	private void printInOrder(BSTNode root)
	{
		if(root != null)
		{
			printInOrder(root.leftTree);
			System.out.println(root.word);
			printInOrder(root.rightTree);
		}
	}
	
	public void addNode()
	{
		String input;
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter a word: ");
		input = scan.next();
		System.out.println("Input = " + input);
		BSTNode newNode = new BSTNode(input);
		System.out.println(newNode.word);
		addIt(newNode);
	}
	
	private void addIt(BSTNode nNode)
	{
		if(root == null)
		{
			root = nNode;
		}
		else
		{
			
		}
	}
}
