package com.icodejava.blog.published.maths;

import java.util.Arrays;

/**
 * 
 * @author Kushal Paudyal 
 * Created on: 01/05/2017 
 * Last Modified on: 01/05/2017
 *
 * This class demonstrates how to rotate a NxN array 90 degrees using no extra buffer.
   e.g. 
     [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]]
	 
	 should appear as
	 
	 [[5, 10, 15, 20, 25], [4, 9, 14, 19, 24], [3, 8, 13, 18, 23], [2, 7, 12, 17, 22], [1, 6, 11, 16, 21]]
	 for a 90 degrees counter clockwise rotation

	 
	 [[21, 16, 11, 6, 1], [22, 17, 12, 7, 2], [23, 18, 13, 8, 3], [24, 19, 14, 9, 4], [25, 20, 15, 10, 5]]
	 for a 90 degrees clockwise rotation
	 
 */

public class Rotate2DArrayInPlace {
		
public static void main (String args [] ) throws Exception {
		
		Integer [] [] myNxNArray = new Integer [][] {
			{1, 2, 3, 4, 5},
			{6, 7, 8, 9, 10},
			{11, 12, 13, 14, 15},
			{16,17,18,19,20},
			{21,22,23,24,25}
		};
		
		System.out.println("SOURCE ARRAY: \n" + Arrays.deepToString(myNxNArray));
		Integer [][] rotatedInPlace = rotateMatrixInPlaceCounterClockwise(myNxNArray);
		
		System.out.println("ROTATED IN PLACE - 90 degrees COUNTER CLOCKWISE");
		System.out.println(Arrays.deepToString(rotatedInPlace));
		
		//The array referenced in above definition myNxNArray is changed so you will need to redefine
		Integer [] [] myNxNArray1 = new Integer [][] {
			{1, 2, 3, 4, 5},
			{6, 7, 8, 9, 10},
			{11, 12, 13, 14, 15},
			{16,17,18,19,20},
			{21,22,23,24,25}
		};
		
		Integer [][] rotatedInPlaceClockwise = rotateMatrixInPlaceClockwise(myNxNArray1);
		
		System.out.println("ROTATED IN PLACE - 90 degrees CLOCKWISE");
		System.out.println(Arrays.deepToString(rotatedInPlaceClockwise));
		
	}
	
	/**
	 * This method rotates the matrix 90 degrees counter clockwise without using extra buffer..
	 */
	public static Integer[][] rotateMatrixInPlaceCounterClockwise(Integer[][] matrix) throws Exception {
		
		if(matrix.length == 0 || matrix.length != matrix[0].length) {
			throw new Exception ("Invalid Input");
		}

		int n = matrix[0].length;
		int tmp;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				tmp = matrix[i][j];
				matrix[i][j] = matrix[j][n - i - 1];
				matrix[j][n - i - 1] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = tmp;
			}

		}
		return matrix;
	}
	
	/**
	 * This method rotates the matrix 90 degrees counter clockwise without using extra buffer..
	 */
	public static Integer[][] rotateMatrixInPlaceClockwise(Integer[][] matrix) throws Exception {
		
		if(matrix.length == 0 || matrix.length != matrix[0].length) {
			throw new Exception ("Invalid Input");
		}

		int n = matrix.length;
		int top;
		for (int i = 0; i < n / 2; i++) {
			
			for (int j = i; j < n - i - 1; j++) {
				
				top = matrix[i][j];
				matrix[i][j] = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				matrix[j] [n - i - 1] = top;
			
			}

		}
		return matrix;
	}

}
