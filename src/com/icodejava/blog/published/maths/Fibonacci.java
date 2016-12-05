package com.icodejava.blog.published.maths;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Mar 11, 2014
 * Last Modified On - Mar 11, 2014
 */
public class Fibonacci {

	static final int NUMBER_OF_FIBONACCI_SEQUENCE = 10;

	public static void main(String args[]) {

		System.out.print("RECURSIVELY GENERATED:");
		generateFibonacciRecursive(NUMBER_OF_FIBONACCI_SEQUENCE);

		System.out.print("\nITERATIVELY GENERATED:");
		generateFibonacciIterative(NUMBER_OF_FIBONACCI_SEQUENCE);
	}

	/**
	 * Loops n number of times to generate Fibonacci Sequence.
	 * Each nth Fibonacci Number is generated iteratively.
	 */
	private static void generateFibonacciIterative(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(fibIterative(i) + ",");
		}

	}

	/**
	 * Loops n number of times to generate Fibonacci Sequence.
	 * Each nth Fibonacci Number is generated recursively.
	 */
	private static void generateFibonacciRecursive(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(fibRecursive(i) + ",");
		}

	}

	/**
	 * Uses recursion to generate nth Fibonacci number
	 */
	private static int fibRecursive(int n) {
		int result = -1;
		if (n == 0 || n == 1) {
			result = 1;
		} else if (n > 1) {
			result = fibRecursive(n - 1) + fibRecursive(n - 2);

		}

		return result;
	}

	/**
	 * User iteration to generate nth Fibonacci number
	 */
	private static int fibIterative(int n) {
		if (n < 0) {
			return -1;
		}

		if (n == 0) {
			return 1;
		}

		int a = 1;
		int b = 1;

		for (int i = 2; i <= n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}

		return b;
	}

}
