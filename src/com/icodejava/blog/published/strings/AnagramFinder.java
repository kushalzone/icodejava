package com.icodejava.blog.published.strings;

import java.util.Arrays;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * Created On -  Feb 14, 2014
 * Last Modified On - Feb 14, 2014
 */
public class AnagramFinder {

	public static void main(String args[]) {
		String firstString = "Dormitory";
		String secondString = "Dirty Room";

		checkForAnagram(firstString, secondString);

		secondString = "Dirty Rooms";

		checkForAnagram(firstString, secondString);

	}

	private static boolean checkForAnagram(String firstString, String secondString) {

		System.out.println("Analyzing the following words for Anagrams: " + firstString + " , " + secondString);

		boolean result;

		if (firstString == null || secondString == null) {
			result = false;
		} else {
			String sortedFirstString = sortIgnoreCase(firstString).trim();
			String sortedSecondString = sortIgnoreCase(secondString).trim();

			System.out.println("Sorted First String: " + sortedFirstString);
			System.out.println("Sorted Second String: " + sortedSecondString);

			result = sortedFirstString.equalsIgnoreCase(sortedSecondString);
		}

		System.out.println("ANAGRAM RESULT - " + result + "\n\n");

		return result;

	}

	/**
	 * @param string
	 *            - String to be sorted
	 * @return sorted string
	 */
	private static String sortIgnoreCase(String string) {
		if (string == null) {
			return null;
		}

		char[] characters = string.toUpperCase().toCharArray();
		Arrays.sort(characters);
		return new String(characters);

	}

}
