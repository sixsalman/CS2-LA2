package edu.wmich.cs1120.LA2.SKhan;

/**
 * 
 * 18th September 2019
 * CS1120-545 LA2
 * This program initially creates and displays a binary tree with preset values
 * and gives user options to add a node, inquire size and check existence of a
 * node in tree.
 * This class contains the main method.
 * The pseudocode for this lab assignment is in LA2SKhan folder.
 * 
 * @author M. Salman Khan
 *
 */
public class BinaryTree {

	/**
	 * Creates initial tree, prints it along with its size and infinitely loops
	 * showing menu and printing (updated) tree
	 * 
	 * @param args is not used
	 */
	public static void main(String[] args) {
		TreeDataStructure root = new TreeDataStructure("A");
		root.addChild("B", "A");
		root.addChild("C", "A");
		root.addChild("D", "B");
		root.addChild("E", "B");
		root.addChild("F", "C");
		root.addChild("G", "C");
		root.addChild("H", "D");
		root.addChild("I", "D");
		root.addChild("J", "E");
		root.addChild("K", "E");
		root.addChild("L", "F");

		root.printTree();
		System.out.println("There are " + root.size() + " nodes in this tree.\n");

		while (true) {
			printMenu(root);
			root.printTree();
		}

	}

	/**
	 * Displays menu through root node's object
	 * 
	 * @param root receives reference to root node's object
	 */
	private static void printMenu(TreeDataStructure root) {
		root.dispMenu();
	}

}
