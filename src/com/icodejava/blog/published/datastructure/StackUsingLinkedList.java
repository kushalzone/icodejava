package com.icodejava.blog.published.datastructure;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 2/8/2017
 * Last Modified on: 2/8/2017
 *
 * This class shows a simple implementation of Stack using LinkedList
 */
import java.util.LinkedList;

public class StackUsingLinkedList {

	private LinkedList<Object> list = new LinkedList<Object>();

	public void push(Object item) {
		list.addFirst(item);
		System.out.println("Stacked: " + item);
	}

	public Object pop() {
		System.out.println("Destacked: " + list.getFirst());
		return list.removeFirst();
	}

	public Object peek() {
		return list.getFirst();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Testing the stack. The added objects should be returned in reverse order
	 */
	public static void main (String args[]) {
		StackUsingLinkedList stack = new StackUsingLinkedList();
		System.out.println("===STATCK-PUSH===");
		stack.push("One");
		stack.push("Two");
		stack.push("Three");
		
		System.out.println("\n===STACK-POP===");
		while (!stack.isEmpty()) {
			stack.pop();
		}
	}
}
