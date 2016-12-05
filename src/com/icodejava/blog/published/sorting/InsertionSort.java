package com.icodejava.blog.published.sorting;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 26, 2014
 * Last Modified On - Feb 26, 2014
 */
public class InsertionSort {

	public static void main(String args[]) {
		int[] numberArray = { 5, 4, 7, 3, 3, 9, 0, 1, 2, 8, 6, -5 };

		sort(numberArray);

	}

	public static void sort(int[] numberArray) {

		System.out.println("Input Array: " + Arrays.toString(numberArray));

		for (int i = 1; i < numberArray.length; i++) {
			for (int j = i; j > 0; j--) {
				// Start comparing data with adjacent one and swap if smaller
				if (numberArray[j] < numberArray[j - 1]) {
					swap(numberArray, j, j - 1);
				}
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
