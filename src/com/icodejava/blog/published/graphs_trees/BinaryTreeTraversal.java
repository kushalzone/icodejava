package com.icodejava.blog.published.graphs_trees;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Mar 3, 2014
 * Last Modified On - Mar 3, 2014
 */
public class BinaryTreeTraversal {
	public static void main(String args[]) {

		
		BinaryTreeNode root = createBinaryTree();

		System.out.println("IN ORDER TREE TRAVERSAL");
		// In Order Tree Traversal - LEFT - ROOT - RIGHT.
		traverseInOrder(root);

		System.out.println("\n\nPRE ORDER TREE TRAVERSAL");

		// Pre-Order Tree Traversal - ROOT - LEFT - RIGHT.
		traversePreOrder(root);

		System.out.println("\n\nPOST ORDER TREE TRAVERSAL");

		// Post Order Tree Traversal - LEFT - RIGHT - ROOT.
		traversePostOrder(root);
	}

	private static BinaryTreeNode createBinaryTree() {
		// Creating a tree with following data. Root node being 15.
		// 15,5,3,12,13,10,6,7,16,20,18,23.
		BinaryTreeNode root = new BinaryTreeNode(15);
		addNode(root, 5);
		addNode(root, 3);
		addNode(root, 12);
		addNode(root, 13);
		addNode(root, 10);
		addNode(root, 6);
		addNode(root, 7);
		addNode(root, 16);
		addNode(root, 20);
		addNode(root, 18);
		addNode(root, 23);
		return root;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static BinaryTreeNode addNode(BinaryTreeNode root, Comparable value) {
		//if root is null
		if(root == null) {
			return new BinaryTreeNode(value);
		}
		
		//if the value is smaller than a node, 
		if(value.compareTo(root.value) < 0) {
			root.left = addNode(root.left, value);
		} else {
			root.right = addNode(root.right, value);
		}
		
		return root;
	}
	
	/**
	 * IN-ORDER Traversal - Recursively Traverses LEFT-ROOT-RIGHT
	 * In order traversal results sorted order traverse.
	 */
	public static void traverseInOrder(BinaryTreeNode theRootNode) {

		if (theRootNode != null) {
			// left
			traverseInOrder(theRootNode.left);
			// root
			System.out.print(theRootNode.value + ",");
			// right
			traverseInOrder(theRootNode.right);
		}
	}
    
	/**
	 * PRE-ORDER Traversal - Recursively Traverses ROOT-LEFT-RIGHT
	 */
	public static void traversePreOrder(BinaryTreeNode theRootNode) {
		
		if (theRootNode != null) {
			//root
			System.out.print(theRootNode.value + ",");

			//left
			traversePreOrder(theRootNode.left);

			//right
			traversePreOrder(theRootNode.right);
		}
	}
	
	/**
	 * POST ORDER Traversal - Recursively traverses LEFT-RIGHT-ROOT
	 */
	public static void traversePostOrder(BinaryTreeNode theRootNode) {
		
		if (theRootNode != null) {
			//left
			traversePostOrder(theRootNode.left);

			//right
			traversePostOrder(theRootNode.right);
			
			//root
			System.out.print(theRootNode.value + ",");

		}
	}

}

/**
 * Binary Tree Node representation.
 * - Node has a value, a left node and a right node.
 * - Single node, when created, has left and right node as null.
 */
@SuppressWarnings("rawtypes")
class BinaryTreeNode {
	Comparable value;
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	public BinaryTreeNode(Comparable value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
}
