package com.icodejava.blog.published.datastructure;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Mar 9, 2014
 * Last Modified On - Mar 9, 2014
 */
public class LinkedListOperation {
	public static void main(String args[]) {

		LinkedListNode linkedList = new LinkedListNode(4).addNode(5).addNode(2).addNode(-5);

		System.out.println("Final List: " + linkedList +"\n");

		// Deleting nodes in an order different from how they were added.
		linkedList = linkedList.removeNode(5).removeNode(-5).removeNode(2).removeNode(4);

		System.out.println("After Removal, the list is: " + linkedList);

	}
}

@SuppressWarnings({"rawtypes", "unchecked"})
class LinkedListNode {
	private LinkedListNode next;
	private Comparable value = null;

	public LinkedListNode(Comparable value) {
		// create first node
		next = null;
		this.value = value;

		System.out.println("Adding Node: " + value);
	}

	/**
	 * This method removes a node from a linked list.
	 */
	public LinkedListNode removeNode(Comparable nodeValue) {

		System.out.println("Removing Node: " + nodeValue);

		if (this.value.compareTo(nodeValue) == 0) { // head
			return this.next;
		} else { // non head

			LinkedListNode current = this;
			LinkedListNode previous = null;

			while (current.next != null) {
				if (current.value.compareTo(nodeValue) == 0) {
					previous.next = current.next;
					return this;
				}

				previous = current;
				current = current.next;
			}

			if (current.value.compareTo(nodeValue) == 0) { // tail
				previous.next = null;
			}

		}

		return this;

	}

	@Override
	public String toString() {

		String data = "";
		LinkedListNode current = this;
		do {
			data += current.value + ",";
			current = current.next;
		} while (current != null);

		return data;
	}

	/**
	 * Adds the node at the tail, making it an unsorted linked list. This method
	 * returns a head node.
	 */
	public LinkedListNode addNode(Comparable value) {

		// if first node
		if (this.next == null) {
			LinkedListNode node = new LinkedListNode(value);
			this.next = node;
			this.next.next = null;
		} else {
			LinkedListNode current = this.next;
			while (current.next != null) {
				current = current.next;
			}

			LinkedListNode node = new LinkedListNode(value);
			current.next = node;
			current.next.next = null;
		}

		return this; // return head
	}

}


