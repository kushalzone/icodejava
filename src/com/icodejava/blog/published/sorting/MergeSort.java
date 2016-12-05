package com.icodejava.blog.published.sorting;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 20, 2014
 * Last Modified On - Feb 20, 2014
 *
 * MERGE SORT STEPS
 * If the number of items to sort is 0 or 1, return
 * Sort the first and second half of the array recursively
 * Merge the two sorted halves into a sorted array.
 *
 * COMPLEXITY:
 * Base Case: nlogn
 * Average Case: nlogn
 * Worst Case: nlogn

 */
public class MergeSort {

	public static void main(String args[]) {

		int[] numberArray = { 5, 4, 7, 3, 3, 9, 0, -11, 2, 8, 6 };

		sort(numberArray);
	}

	private static void sort(int[] numberArray) {

		System.out.println("\nINPUT ARRAY:" + Arrays.toString(numberArray));

		int[] tempArray = new int[numberArray.length];

		mergeSort(numberArray, tempArray, 0, numberArray.length - 1);

		System.out.println("SORTED ARRAY:" + Arrays.toString(numberArray));

	}

	private static void mergeSort(int[] data, int[] tempArray, int low, int high) {
		/** If low is smaller than high, array is not sorted, otherwise it is. */
		if (low < high) {

			int middle = (low + high) / 2;

			System.out.println(" Divide Step -  low:" + low + " middle:" + middle + " high:" + high);

			/** Recursively Sort the left side of the array **/
			mergeSort(data, tempArray, low, middle);

			/** Recursively Sort the right side of the array **/
			mergeSort(data, tempArray, middle + 1, high);

			/** Merge the sorted arrays **/
			merge(data, tempArray, low, middle, high);
		}
	}

	/**
	 * 
	 * @param data
	 *            - Original Unsorted or Partially Sorted Array
	 * @param tempArray
	 *            - Temporary Helper Array
	 * @param low
	 *            - Starting Index of Left Array
	 * @param middle
	 * @param high
	 *            - Ending Index of Right Array
	 * 
	 *            This method merges two sorted portions of an array using
	 *            temporary array.
	 */
	private static void merge(int[] data, int[] tempArray, int low, int middle, int high) {

		// Copy both parts into the temporary helper array
		for (int i = low; i <= high; i++) {
			tempArray[i] = data[i];
		}

		int i = low; // Start of Left Array
		int j = middle + 1; // Start of right Array
		int k = low;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (tempArray[i] <= tempArray[j]) {
				data[k] = tempArray[i];
				i++;
			} else {
				data[k] = tempArray[j];
				j++;
			}
			k++;
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			data[k] = tempArray[i];
			k++;
			i++;
		}

		System.out.println("   Merge Step (low:" + low + ",middle:" + middle + ",high:" + high + ") Original "
				+ Arrays.toString(data) + " Temporary" + Arrays.toString(tempArray));

	}

}
