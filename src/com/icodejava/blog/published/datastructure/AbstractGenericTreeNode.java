package com.icodejava.blog.published.datastructure;

/**
 * 
 * @author Kushal Paudyal www.icodejava.com Created On - Dec 5, 2016 Last
 *         Modified On - Dec 5, 2016
 * 
 *         This class shows a basic implementation of Generic Tree concept in
 *         Java.
 */
public abstract class AbstractGenericTreeNode {

	private AbstractGenericTreeNode [] children;

	public AbstractGenericTreeNode [] getChildren() {
		return children;
	}

	public void setChildren(AbstractGenericTreeNode [] children) {
		this.children = children;
	}
	
	public int getNumberOfChildren () {
		
		return children != null ? children.length : 0;
	}
	
}
