package com.icodejava.blog.published.datastructure;
/**
 * @author Kushal Paudyal
 * Created on 12/5/2016
 * Last Modified on 12/5/2016
 * 
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

	public Comparable getValue() {
		return value;
	}

	public void setValue(Comparable value) {
		this.value = value;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
}