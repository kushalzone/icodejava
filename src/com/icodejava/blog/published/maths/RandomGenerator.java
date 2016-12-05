package com.icodejava.blog.published.maths;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Mar 8, 2014
 * Last Modified On - Mar 8, 2014
 * 
 * Generates random number 1-9 and 1-7 
 * using function that generates random number 1-5
 */
public class RandomGenerator {

	public static void main(String args[]) {

		generateRandomNineAndPrint();
		generateRandomSevenAndPrint();
	}

	/**
	 * This method calls randomNine() method 1000 times and updates the count of
	 * each number from 1-9 being generated.
	 * 
	 * randomNine() method generates numbers from 1-9 using randomFive() method.
	 */
	private static void generateRandomNineAndPrint() {
		int[] counts = new int[9];
		for (int i = 0; i < 1000; i++) {
			int value = randomNine();
			counts[value - 1]++;
		}

		System.out.println("Count of Generated Random Numbers 1- 9:" + Arrays.toString(counts));
	}

	/**
	 * This method calls randomSeven() method 1000 times and updates the count
	 * of each number from 1-7 being generated.
	 * 
	 * randomNine() method generates numbers from 1-7 using randomFive() method.
	 */
	private static void generateRandomSevenAndPrint() {
		int[] counts = new int[7];
		for (int i = 0; i < 1000; i++) {
			int value = randomSeven();
			counts[value - 1]++;
		}

		System.out.println("Count of Generated Random Numbers 1- 7:" + Arrays.toString(counts));
	}

	/**
	 * Uses randomFive() method that generates 1-5 to generate numbers from 1-9
	 * 
	 * @return
	 */
	public static int randomNine() {
		int random;

		while (true) {
			/**
			 * When you call randomFive() two times, there are a lot of possible
			 * combinations.25 to be exact. (1,1) (1,2) (1,3) ... (5,5)
			 * 
			 * We need to call it two times and use the formula below to make
			 * sure we generate numbers from 1-9. Otherwise, it will not
			 * generate some numbers.
			 */
			random = (randomFive() - 1) * 3 + (randomFive() + 1) * 2;
			return random % 9 + 1;
		}
	}

	/**
	 * Uses randomFive() method that generates 1-5 to generate numbers from 1-7
	 * 
	 * @return
	 */
	public static int randomSeven() {
		int random;

		while (true) {
			/**
			 * When you call randomFive() two times, there are a lot of possible
			 * combinations.25 to be exact. (1,1) (1,2) (1,3) ... (5,5)
			 * 
			 * We need to call it two times and use the formula below to make
			 * sure we generate numbers from 1-7. Otherwise, it will not
			 * generate some numbers.
			 */
			random = (randomFive() - 1) * 3 + (randomFive() + 1) * 2;
			return random % 7 + 1;
		}
	}

	/**
	 * This method generates a random number 1 through 5. It uses Math.random()
	 * method for randomizing.
	 * Note: Math.ceil(0.0) is 1
	 * @return
	 */
	public static int randomFive() {
		return (int) (Math.ceil(Math.random() * 5));
	}

}
