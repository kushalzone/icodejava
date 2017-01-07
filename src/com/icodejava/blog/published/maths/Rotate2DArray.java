package com.icodejava.blog.published.maths;

import java.util.Arrays;

/**
 * 
 * @author Kushal Paudyal 
 * Created on: 01/05/2017 
 * Last Modified on: 01/05/2017
 *
 * This class demonstrates how to rotate a mxn array 90 degrees using extra buffer
   e.g. 
     1  2  3 4 5
	 6  7  8 9 10
	 11 12 13 14 15
	 
	 should appear as
	 
	 11 6 1
	 12 7 2
	 13 8 3
	 14 9 4
	 15 10 5
	 
	 for a 90 degrees clockwise rotation
	 
	 and 
	 
	 5 10 15
	 4 9 14
	 3 8 13
	 2 7 12
	 1 6 11
	 
	 for a 90 degrees counterclockwise rotation
 */
public class Rotate2DArray {

	public static void main(String args[]) throws Exception {

		// define two dimensional array
		Integer[][] myArray = new Integer[][] { 
				{ 1, 2, 3, 4, 5 }, 
				{ 6, 7, 8, 9, 10 }, 
				{ 11, 12, 13, 14, 15 } 
			};

		System.out.println("SOURCE ARRAY: \n" + Arrays.deepToString(myArray));

		Integer[][] rotated = rotateMatrixClockwise(myArray);

		System.out.println("ROTATED ARRAY 90 degrees clockwise:");
		System.out.println(Arrays.deepToString(rotated));
		
		// define two dimensional array
		Integer[][] myArray1 = new Integer[][] { 
				{ 1, 2, 3, 4, 5 }, 
				{ 6, 7, 8, 9, 10 }, 
				{ 11, 12, 13, 14, 15 } 
			};

		System.out.println("SOURCE ARRAY: \n" + Arrays.deepToString(myArray1));

		Integer[][] rotated1 = rotateMatrixCounterClockwise(myArray1);

		System.out.println("ROTATED ARRAY 90 degrees counter clockwise:");
		System.out.println(Arrays.deepToString(rotated1));

	}

	/**
	 * This method rotates the matrix 90 degrees clockwise by using extra
	 * buffer.
	 */
	public static Integer[][] rotateMatrixClockwise(Integer[][] matrix) {
		Integer[][] rotated = new Integer[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix[0].length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {

				
				rotated[i][j] = matrix[matrix.length - j - 1][i];
				
			}
		}

		return rotated;
	}
	
	/**
	 * This method rotates the matrix 90 degrees counter clockwise by using extra
	 * buffer.
	 */
	public static Integer[][] rotateMatrixCounterClockwise(Integer[][] matrix) {
		Integer[][] rotated = new Integer[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix[0].length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				
				rotated[i][j] = matrix[j][matrix[0].length - i - 1];
			}
		}

		return rotated;
	}

}
