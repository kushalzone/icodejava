package com.icodejava.blog.published.sorting;

/**
 * @author Kushal Paudyal 
 * www.icodejava.com 
 * Created On - Feb 18, 2014 
 * Last Modified On - Feb 18, 2014
 */
public class BubbleSortInteger {

	public static void main(String args[]) {
		int[] unsortedArray = { 6, 5, 2, 5, 6, 3, 4, 2, 7, 8, 9, 0 };
		printArray("Unsorted Array:", unsortedArray);

		int[] sortedArray = bubbleSortAscending(unsortedArray);

		printArray("Sorted Array (Ascending):", sortedArray);

		sortedArray = bubbleSortDescending(unsortedArray);

		printArray("Sorted Array (Descending):", sortedArray);

	}

	private static int[] bubbleSortDescending(int[] unsortedArray) {

		for (int index = unsortedArray.length - 1; index > 0; index--) {
			for (int bubbleIndex = 0; bubbleIndex < index; bubbleIndex++) {

				if (unsortedArray[index] > unsortedArray[bubbleIndex]) {
					swap(unsortedArray, index, bubbleIndex);
				}

			}
		}

		return unsortedArray; // which is now sorted
	}

	private static int[] bubbleSortAscending(int[] unsortedArray) {

		for (int index = unsortedArray.length - 1; index > 0; index--) {
			for (int bubbleIndex = 0; bubbleIndex < index; bubbleIndex++) {

				if (unsortedArray[index] < unsortedArray[bubbleIndex]) {
					swap(unsortedArray, index, bubbleIndex);
				}

			}
		}

		return unsortedArray; // which is now sorted
	}

	private static void swap(int[] unsortedArray, int firstIndex, int secondIndex) {
		if (unsortedArray == null || firstIndex < 0 || firstIndex > unsortedArray.length 
				|| secondIndex < 0 || secondIndex > unsortedArray.length) {
			return;
		}
		int tempInteger = unsortedArray[firstIndex];
		unsortedArray[firstIndex] = unsortedArray[secondIndex];
		unsortedArray[secondIndex] = tempInteger;

	}

	private static void printArray(String string, int[] array) {

		if (array != null && array.length > 0) {
			System.out.print(string + " ");
			for (int i = 0; i < array.length; i++) {
				System.out.print((i > 0 ? "," : "") + array[i]);
			}
		}

		System.out.println();

	}

}
