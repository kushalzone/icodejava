package com.icodejava.blog.published.sorting;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 24, 2014
 * Last Modified On - Feb 24, 2014
 * 
 * http://blog.icodejava.com/113/quick-sort-implementation-in-java-working-sample-code-example/
 */
public class QuickSort {

	public static void main(String args[]) {
		int[] items = { 0, 5, -1, 2, 3, 9, 7, 12, 5, 4 };
		sort(items);
	}

	public static void sort(int[] items) {
		if (items == null || items.length <= 0) {
			return;
		}

		System.out.println("Input Array: " + Arrays.toString(items));
		quickSort(items, 0, items.length - 1);
		System.out.println("Sorted Array: " + Arrays.toString(items));

	}
	
	/**
	 * Recursively apply quickSort - one for partition smaller than pivot -
	 * another for partition bigger than pivot
	 */
	public static void quickSort(int items[], int start, int end) {
		if (start >= end) {
			return;
		}

		int pivot = partition(items, start, end);

		if (start < pivot - 1) {
			quickSort(items, start, pivot - 1);
		}

		if (end > pivot) {

			quickSort(items, pivot, end);
		}

		
	}

	/**
	 * Reorganizes the given list so all elements less than the first are before
	 * it and all greater elements are after it.
	 */
	private static int partition(int[] items, int start, int end) {

		int pivot = items[(start + end) / 2];
		while (start <= end) {
			while (items[start] < pivot) {
				start++;
			}
			while (items[end] > pivot) {
				end--;
			}

			if (start <= end) {
				swap(items, start, end);
				start++;
				end--;
			}
		}
		return start;

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
