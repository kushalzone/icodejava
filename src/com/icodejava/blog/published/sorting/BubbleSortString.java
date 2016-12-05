package com.icodejava.blog.published.sorting;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 18, 2014
 * Last Modified On - Feb 18, 2014
 */
public class BubbleSortString {
	
	public static void main(String args []) {
		String [] unsortedArray = {"M","F","B","E","Z","F","D","Q","R","S","H","A"};
		printArray("Unsorted Array:", unsortedArray);
		
		String [] sortedArray = bubbleSortAscending(unsortedArray);
		
		printArray("Sorted Array (Ascending):", sortedArray);
		
		sortedArray = bubbleSortDescending(unsortedArray);
		
		printArray("Sorted Array (Descending):", sortedArray);
		
		
	}

	
	private static String[] bubbleSortDescending(String[] unsortedArray) {

		for (int index = unsortedArray.length - 1; index > 0; index--) {
			for (int bubbleIndex = 0; bubbleIndex < index; bubbleIndex++) {
				/**
				 * using String's compareTo method to see if one element is
				 * bigger than the other.
				 */
				if (unsortedArray[index].compareTo(unsortedArray[bubbleIndex]) > 0) {
					swap(unsortedArray, index, bubbleIndex);
				}

			}
		}

		return unsortedArray; // which is now sorted
	}

	private static String[] bubbleSortAscending(String[] unsortedArray) {

		for (int index = unsortedArray.length - 1; index > 0; index--) {
			for (int bubbleIndex = 0; bubbleIndex < index; bubbleIndex++) {
				/**
				 * using String's compareTo method to see if one element is
				 * bigger than the other.
				 */
				if (unsortedArray[index].compareTo(unsortedArray[bubbleIndex]) < 0) {
					swap(unsortedArray, index, bubbleIndex);
				}

			}
		}

		return unsortedArray; // which is now sorted
	}

	private static void swap(String[] unsortedArray, int firstIndex, int secondIndex) {
		if(unsortedArray == null 
				|| firstIndex < 0 
				|| firstIndex > unsortedArray.length 
				|| secondIndex < 0  
				|| secondIndex > unsortedArray.length) {
			return;
		}
		String tempString = unsortedArray[firstIndex];
		unsortedArray[firstIndex] = unsortedArray[secondIndex];
		unsortedArray[secondIndex] = tempString;

	}
	
	private static void printArray(String string, String[] unsortedArray) {

		if (unsortedArray != null && unsortedArray.length > 0) {
			System.out.print(string + " ");
			for (int i = 0; i < unsortedArray.length; i++) {
				System.out.print((i > 0 ? "," : "") + unsortedArray[i]);
			}
		}

		System.out.println();

	}
}
