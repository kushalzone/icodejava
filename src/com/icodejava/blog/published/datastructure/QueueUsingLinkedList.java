package com.icodejava.blog.published.datastructure;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 2/8/2017
 * Last Modified on: 2/8/2017
 *
 * This class shows a simple implementation of Queue using LinkedList
 */
import java.util.LinkedList;

public class QueueUsingLinkedList {

	private LinkedList<Object> data = new LinkedList<Object>();

	public void enqueue(Object item) {
		data.addLast(item);
		System.out.println("Added: " + item);
	}

	public Object dequeue() {
		System.out.println("Removed: " + data.getFirst());
		return data.removeFirst();
		
	}

	public Object peek() {
		return data.getFirst();
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Testing the stack. The added objects should be returned in same order
	 */
	public static void main (String args[]) {
		QueueUsingLinkedList queue = new QueueUsingLinkedList();
		System.out.println("===ENQUEUE===");
		queue.enqueue("One");
		queue.enqueue("Two");
		queue.enqueue("Three");
		
		System.out.println("\n===DEQUEUE===");
		while (!queue.isEmpty()) {
			queue.dequeue();
		}
	}
}
