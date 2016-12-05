package com.icodejava.blog.published.sorting;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 25, 2014
 * Last Modified On - Feb 25, 2014
 */
public class SelectionSort {

	public static void main(String args[]) {
		int[] numberArray = { 5, 4, 7, 3, 3, 9, 0, 1, 2, 8, 6, -5 };

		sort(numberArray);

	}

	public static void sort(int[] numberArray) {
		System.out.println("Input Array: " + Arrays.toString(numberArray));
		for (int i = 0; i < numberArray.length-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < numberArray.length; j++) {
				if (numberArray[j] < numberArray[minIndex]) {
					minIndex = j;
				}

			}

			if(minIndex != i) {
				swap(numberArray, minIndex, i);
			}

		}

		System.out.println("Sorted Array: " + Arrays.toString(numberArray));
	}

	/**
	 * Swaps data from an array.
	 */
	private static void swap(int[] array, int firstIndex, int secondIndex) {
		if (array == null || firstIndex < 0 || firstIndex > array.length
				|| secondIndex < 0 || secondIndex > array.length) {
			return;
		}
		int temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;

	}
}
