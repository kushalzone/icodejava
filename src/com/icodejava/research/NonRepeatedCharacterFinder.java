package com.icodejava.research;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kushal Paudyal 
 * Created on: 01/05/2017 
 * Last Modified on: 01/05/2017
 *
 * This class demonstrates how we can find first non repeated character
 * in any given String. We will test for Case Sensitivity as well
 */

public class NonRepeatedCharacterFinder {

	final static boolean CASE_SENSITIVE = true;

	public static void main(String args[]) {
		String str = "Java Blog for junior Students";

		findFirstNonRepeatedCharacter(str, CASE_SENSITIVE);
		findFirstNonRepeatedCharacter(str, !CASE_SENSITIVE);

		str = "Apple is pretty yummy and healthy";

		findFirstNonRepeatedCharacter(str, CASE_SENSITIVE);
		findFirstNonRepeatedCharacter(str, !CASE_SENSITIVE);
		

		str = "ABba";
		findFirstNonRepeatedCharacter(str, CASE_SENSITIVE);
		findFirstNonRepeatedCharacter(str, !CASE_SENSITIVE);
	}

	private static void findFirstNonRepeatedCharacter(String originalString, boolean caseSensitive) {
		String str = originalString;

		if (str == null) {
			System.out.println("Not a valid string");
		}

		if (!caseSensitive) {
			str = str.toLowerCase();
		}

		Map<Character, Integer> countMap = new HashMap<Character, Integer>();

		// loop through the characters and find the character count
		for (int i = 0; i < str.length(); i++) {
			Character character = new Character(str.charAt(i));
			if (countMap.containsKey(character)) {
				Integer charCount = countMap.get(character);
				countMap.put(character, ++charCount);
			} else {
				countMap.put(character, 1);
			}
		}

		// Now find the first character that has a count of 1. Please note, we have iterated through the String characters. 
		// You can alternately iterate through the keys and find the corresponding values.
		boolean found = false;
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (countMap.get(currentChar) == 1) {
				System.out.println("FOUND. The first non repeated character in the String " + currentChar
						+ " (Case Sensitive: " + caseSensitive + ", String: " + originalString + ")");
				found = true;
				break;
			}

		}

		if (!found) {
			System.out.println("NOT FOUND: Non repeated character not found in the String." + " (Case Sensitive: "
					+ caseSensitive + ", String: " + originalString + ")");
		}

	}

}
