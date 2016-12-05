package com.icodejava.blog.published.datastructure;
/**
 * @author Kushal Paudyal
 * Created on - 11/28/2016
 * Last modified on - 11/28/2016
 * This class shows a basic example of defining a SinglyLinkedList in Java
 * Also provides a overridden constructor both of which are optional.
 */
public class SinglyLinkedList {
	
	private Object value;
	private SinglyLinkedList nextObject;
	
	public SinglyLinkedList(Object value) {
		this.value = value;
		this.nextObject = null;
	}
	
	public SinglyLinkedList(Object value, SinglyLinkedList nextObject) {
		this.value = value;
		this.nextObject = nextObject;
	}
	
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SinglyLinkedList getNextObject() {
		return nextObject;
	}
	public void setNextObject(SinglyLinkedList nextObject) {
		this.nextObject = nextObject;
	}

}
