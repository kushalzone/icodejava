package com.icodejava.blog.published.datastructure;
/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 22, 2014
 * Last Modified On - Feb 22, 2014
 */
public class CircularListInsert {
	public static void main(String args[]) {

		CircularList myList = new CircularList(5);
		myList.printAll();
		
		myList = myList.insert(2);
		myList.printAll();
		
		//test for value smaller than the head.
		myList = myList.insert(1);
		myList.printAll();
		
		myList = myList.insert(3);
		myList.printAll();
		
		//Test for duplicate value
		myList = myList.insert(5);
		myList.printAll();
		
		//test for value greater than he tail.
		myList = myList.insert(6);
		myList.printAll();
	}
}

class CircularList {
	CircularList next;
	int value;

	public CircularList(int value) {
		this.next = this;
		this.value = value;
	}

	/**
	 * @param value
	 *            - new value to be inserted in the list.
	 * @return head node in the circular list
	 * 
	 * This method inserts data in ascending order.
	 */
	public CircularList insert(int value) {

		// if the current list is null.
		if (this == null) {
			return new CircularList(value);
		} else if (this.next == this) {
			// if the current list is head and tail i.e. single element
			this.next = new CircularList(value);
			this.next.next = this;

			// return the smallest node as the head
			if (this.value < value) {
				return this;
			} else {
				return this.next;
			}
		} else if (value < this.value) {

			// if the value is smaller than the head
			// find the tail and append.

			CircularList temp = new CircularList(value);
			temp.next = this;

			CircularList current = this.next;
			while (current.next != this) {
				current = current.next;
			}

			current.next = new CircularList(value);
			current.next.next = this;

			return current.next;

		} else if (value >= this.value) {
			CircularList current = this;

			/**
			 * Keep looking for nodes until all the nodes are smaller than the
			 * value to be inserted.
			 */
			while (current.next != this && current.next.value <= value) {
				current = current.next;
			}

			/**
			 * Once we find the node to be inserted, make a copy of the
			 * current's next value. Insert a node as current's next. and Make
			 * current's next's next value as the previous next which has been
			 * copied as temp
			 */
			CircularList temp = current.next;
			current.next = new CircularList(value);
			current.next.next = temp;
			return this;

		}

		return this;
	}

	public void printAll() {

		if (this == null) {
			return;
		}

		CircularList current = this;
		
		do {

			System.out.print(current.value + ",");
			current = current.next;
		} while (current != this);

		System.out.println();
	}
}
