package com.icodejava.research.ready;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 2/8/2017
 * Last Modified on: 2/8/2017
 * 
 * Recursive way of finding the GCD (Greatest Common Divisor)
 */
public class GCDRecursive {
	
	 public static int gcd(int a, int b) {
		 System.out.println("First Number: " + a + " Second Number: " + b);
	        if (a < 0 || b < 0) {
	            throw new IllegalArgumentException("No GCD of negative integers");
	        }
	        return b == 0 ? a : gcd(b, a % b);
	    }
	 
	 public static void main(String args []) {
		 System.out.println("Greatest Common Divisor of 35 and 21 is " + gcd(35,21));
	 }

}
