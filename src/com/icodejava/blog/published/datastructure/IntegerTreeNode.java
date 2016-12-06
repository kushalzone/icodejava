package com.icodejava.blog.published.datastructure;

/**
 * 
 * @author Kushal Paudyal www.icodejava.com Created On - Dec 5, 2016 Last
 *         Modified On - Dec 5, 2016
 * 
 *         This class shows a basic implementation of Generic Tree concept in
 *         Java.
 */

public class IntegerTreeNode extends AbstractGenericTreeNode {
	private int value;

	public IntegerTreeNode(int value, AbstractGenericTreeNode[] children) {
		super();
		this.value = value;
		this.setChildren(children);
	}

	public int getValue() {
		return value;
	}

}
