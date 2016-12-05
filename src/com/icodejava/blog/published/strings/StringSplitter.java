package com.icodejava.blog.published.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kushal Paudyal 
 * www.icodejava.com 
 * Created On - Feb 15, 2014 
 * Last Modified On - Feb 15, 2014
 * Published at: http://blog.icodejava.com/?p=71
 */
public class StringSplitter {

	private static final Map<String, String> DICTIONARY = new HashMap<String, String>();

	public static void main(String args[]) {
		DICTIONARY.put("GOOGLE", "GOOGLE");
		DICTIONARY.put("INTERVIEW", "INTERVIEW");
		DICTIONARY.put("QUESTION", "QUESTIONS");

		String stringToSplit = "interviewquestions";

		splitString(DICTIONARY, stringToSplit);

		stringToSplit = "googleinterviewquestions";

		splitString(DICTIONARY, stringToSplit);

		// Let's try on more with null string.
		splitString(DICTIONARY, null);

	}

	private static void splitString(Map<String, String> LOOKUP_DICTIONARY, String stringToSplit) {

		boolean splitSuccessful = false;

		if (stringToSplit != null && stringToSplit.length() > 0) {
			for (int index = 0; index < stringToSplit.length(); ++index) {
				String leftSubstring = stringToSplit.substring(0, index);
				String rightSubString = stringToSplit.substring(index);

				System.out.println("LEFT: " + leftSubstring + " RIGHT: " + rightSubString);

				if (DICTIONARY.containsValue(leftSubstring.toUpperCase()) && DICTIONARY.containsValue(rightSubString.toUpperCase())) {

					System.out.println("MATCH FOUND. SPLIT SUBSTRING: " + leftSubstring + " " + rightSubString + "\n\n");

					splitSuccessful = true;
					break;

				}

			}
		}

		if (!splitSuccessful) {
			System.out.println("Could not split the word! Not found in dictionary\n\n");
		}

	}

}
