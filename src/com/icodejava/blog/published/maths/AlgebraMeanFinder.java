package com.icodejava.blog.published.maths;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 21, 2014
 * Last Modified On - Feb 21, 2014
 * 
 * This class can be used to find the mean value in an array
 */
public class AlgebraMeanFinder {
	
	public static void main(String args []) {
		int [] numbers = {5,5,7,2,3,9,8,9,2,6,7,8,1};
		/**
		 * Finding mean number of a valid integer array
		 */
		findMeanValue(numbers);
		
		/**
		 * Testing for null array
		 */
		findMeanValue(null);
		
		/**
		 * Passing an empty array
		 */
		findMeanValue(new int[] {});
	}

	/**
	 * @param numbers - array of numbers whose mean has to be found
	 * Mean is a simple average value of all numbers.
	 * This method is capable of handling boundary conditions.
	 */
	public static double findMeanValue(int[] numbers) {

		double sum = 0;
		if (numbers == null || numbers.length < 1) {
			System.out.println("\nInvalid Input. Returning 0. Input was:" + Arrays.toString(numbers));
			return sum;
		}

		for (int index = 0; index < numbers.length; index++) {
			sum += numbers[index];
		}

		double mean = (sum / numbers.length);
		System.out.println("\nInput Array is: " + Arrays.toString(numbers));
		System.out.println("Sum:" + sum + " Count:" + numbers.length + " Mean Value is: " + mean);

		return mean;

	}

}
