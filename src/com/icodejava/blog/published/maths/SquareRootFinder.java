package com.icodejava.blog.published.maths;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Mar 11, 2014
 * Last Modified On - Mar 11, 2014
 */
public class SquareRootFinder {

	public static void main(String args[]) {

		System.out.println("Square Root of 25 is: " + squareRoot(25));
		System.out.println("Square Root of 81 is: " + squareRoot(81));
		System.out.println("Square Root of -100 is: " + squareRoot(-100));
		System.out.println("Square Root of 1 is: " + squareRoot(1));
		System.out.println("Square Root of 0 is: " + squareRoot(0));

	}

	/**
	 * This method use binary search to find the square root.
	 * 
	 * @param number
	 * @return square root of the number
	 */
	private static double squareRoot(double number) {
		double squareRoot = 0;
		double startValue = 0;
		double endValue = number;
		double precision = 0.00001;

		if (number < 0) {
			squareRoot = -1;
		} else if (number == 0 || number == 1) {
			squareRoot = number;
		} else {

			// we will use binary search to narrow down.
			while (endValue - startValue > precision) {
				double midValue = (startValue + endValue) / 2;
				squareRoot = midValue * midValue;

				if (squareRoot == number) {
					return squareRoot;
				} else if (squareRoot > number) {
					endValue = midValue;
				} else {
					startValue = midValue;
				}
			}

			// if a match is not found
			squareRoot = (startValue + endValue) / 2;

		}
		return squareRoot;
	}

}
