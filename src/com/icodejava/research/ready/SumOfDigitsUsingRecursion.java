package com.icodejava.research.ready;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 2/8/2017
 * Last Modified on: 2/8/2017
 * 
 * Demonstrates a recursive way of doing sums of all digits on a number
 */


public class SumOfDigitsUsingRecursion {
	
	 public static int sumOfDigits(int n) {
	        if (n < 0) {
	            return sumOfDigits(-n);
	        } else if (n < 10) {
	            return n;
	        } else {
	            return sumOfDigits(n / 10) + (n % 10);
	        }
	    }
	 
	 public static void main (String args []) {
		 System.out.println("Sum of Digits in -123459 is " + sumOfDigits(-123459));
		 System.out.println("Sum of Digits in 123459 is " + sumOfDigits(123459));
		 
		 /* 
		 sumOfDigits(-123459)
		  = sumOfDigits(123459)
		  = sumOfDigits(12345) + 9
		  = (sumOfDigits(1234) + 5) + 9
		  = ((sumOfDigits(123) + 4) + 5) + 9
		  = (((sumOfDigits(12) + 3) + 4) + 5) + 9
		  = ((((sumOfDigits(1) + 2) + 3) + 4) + 5) + 9
		  = ((((1+2)+3)+4)+5)+9
		  = (((3+3)+4)+5)+9
		  = ((6+4)+5)+9
		  = (10+5)+9
		  = 15 + 9
		  = 24
		 */
	 }

}
