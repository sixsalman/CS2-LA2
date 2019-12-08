package edu.wmich.cs1120.LA2.SKhan;

import java.util.Scanner;

/**
 * 
 * This class serves as the blue print for a TreeDataStructure object which
 * represents a node An object's id, left child, right child and parent are
 * stored which are used and manipulated through the methods implemented from
 * INode interface as well as some additionally defined methods
 * 
 * @author M. Salman Khan
 *
 */
public class TreeDataStructure implements INode {

	private Scanner kbdNum = new Scanner(System.in);
	private Scanner kbdTxt = new Scanner(System.in);

	private String id;
	private INode left;
	private INode right;
	private INode parent;

	/**
	 * Creates a TreeDataStructure object and stores the id received as parameter
	 * 
	 * @param id receives id for the node to be created
	 */
	public TreeDataStructure(String id) {
		this.id = id;
	}

	/**
	 * Gives user a menu; asks him/her for choice and validates it; asks for
	 * additional information if required by chosen choice and validates it too;
	 * calls appropriate methods of its object to perform the task chosen by user
	 */
	public void dispMenu() {
		boolean valid = false;
		do {
			System.out.print("Please select from one of the following options:\n" + "1. Add Node\n" + "2. Tree Size\n"
					+ "3. Find Node\n" + "0. Exit\n" + "->");
			switch (kbdNum.nextInt()) {
			case 1:
				boolean idChk = false;
				String nodeToAdd;
				String parentNode;
				do {
					do {
						System.out.println("Please input the node you want to add->");
						nodeToAdd = kbdTxt.nextLine();
						if (nodeToAdd.length() == 1)
							idChk = true;
						if (!idChk)
							System.out.println("Invalid Entry - only a single character entry is considered valid");
					} while (!idChk);
					idChk = false;
					do {
						System.out.println("Please input the parent node of " + nodeToAdd + "->");
						parentNode = kbdTxt.nextLine();
						if (parentNode.length() == 1)
							idChk = true;
						if (!idChk)
							System.out.println("Invalid Entry - only a single character entry is considered valid");
					} while (!idChk);
				} while (!idChk);

				if (find(parentNode) == null) {
					System.out.println("The parent node does not exist.");
				} else {
					valid = addChild(nodeToAdd, parentNode);
					if (valid) {
						System.out.println("Node successfully added!");
					}
				}
				break;
			case 2:
				System.out.println("Please input the root node->");
				String printStart = kbdTxt.nextLine();
				TreeDataStructure startNode = (TreeDataStructure) find(printStart);
				if (startNode != null) {
					System.out.println("There are " + startNode.size() + " nodes in that tree\n" + startNode + " "
							+ (startNode.getLeft() != null ? startNode.getLeft() : "") + " "
							+ (startNode.getRight() != null ? startNode.getRight() : ""));
				} else {
					System.out.println("Node " + printStart + " does not exist.");
					valid = true;
				}
				break;
			case 3:
				System.out.println("Please input the node you want to look for->");
				String nodeToFind = kbdTxt.nextLine();
				TreeDataStructure findRes = (TreeDataStructure) find(nodeToFind);
				if (findRes == null) {
					System.out.println("Node " + nodeToFind + " does not exist.");
				} else {
					System.out.println("Node " + nodeToFind + " found!");
					System.out.println(findRes + " " + (findRes.getLeft() != null ? findRes.getLeft() : "") + " "
							+ (findRes.getRight() != null ? findRes.getRight() : ""));
				}
				break;
			case 0:
				kbdNum.close();
				kbdTxt.close();
				System.exit(0);
			default:
				System.out.println("Invalid Entry - only integers from 0 to 3 are considered valid");
			}
		} while (!valid);

	}

	/**
	 * Description in INode interface
	 */
	@Override
	public boolean addChild(String ID, String parentID) {
		if ((this.id).equals(parentID)) {
			if (left == null) {
				left = new TreeDataStructure(ID);
				((TreeDataStructure) left).setParent(this);
				return true;
			}
			if (right == null) {
				right = new TreeDataStructure(ID);
				((TreeDataStructure) right).setParent(this);
				return true;
			}
			System.out.println("Parent already has 2 children.");
			return false;
		}
		if (left == null) {
			return false;
		}
		boolean added = left.addChild(ID, parentID);
		if (added)
			return true;
		added = right.addChild(ID, parentID);
		if (added)
			return true;
		return false;
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public INode find(String value) {
		if (value.equals(id))
			return this;
		if (left == null)
			return null;
		INode leftRes = left.find(value);
		if (leftRes != null)
			return leftRes;
		if (right == null) {
			return null;
		} else {
			INode rightRes = right.find(value);
			if (rightRes == null)
				return null;
			else if ((rightRes.getId()).equals(value))
				return rightRes;
		}
		return null;
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public INode getParent() {
		return parent;
	}

	/**
	 * Assigns object to parent variable
	 * 
	 * @param parent receives reference to intended parent of the node
	 */
	private void setParent(INode parent) {
		this.parent = parent;
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public int size() {
		if (left == null)
			return 1;
		return left.size() + (right != null ? right.size() : 0) + 1;
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public void printTree() {
		if (right != null)
			System.out.println(id + " " + left + " " + right);
		else if (left != null)
			System.out.println(id + " " + left);
		if (left != null && right != null) {
			left.printTree();
			right.printTree();
		} else if (left != null) {
			left.printTree();
		} else {
			System.out.println(id);
		}
	}

	/**
	 * Description in INode interface
	 */
	@Override
	public String toString() {
		return id;
	}

	/**
	 * Gets left child of a node
	 * 
	 * @return left child node as object
	 */
	public INode getLeft() {
		return left;
	}

	/**
	 * Gets right child of a node
	 * 
	 * @return right child node as object
	 */
	public INode getRight() {
		return right;
	}

}
