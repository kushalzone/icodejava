package com.icodejava.research.ready;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kushal Paudyal 
 * Created on: 01/29/2017 
 * Last Modified on: 01/29/2017
 *
 * Find how many times a word appears in a given text
 * Algorithmic Complexity -> O(n) where n is the number of words.
 */
public class WordFrequencyFinder {
	private static Map frequencyMap;

	public static void main(String args[]) {
		String bigText = "Well, when Johnny was first starting out, he was signed to a personal services "
				+ "contract with this big-band leader. And as his career got better and better, he wanted "
				+ "to get out of it. But the band leader wouldn't let him. Now, Johnny is my father's godson. "
				+ "So my father went to see this bandleader and offered him $10,000 to let Johnny go, but the "
				+ "bandleader said no. So the next day, my father went back, only this time with Luca Brasi. "
				+ "Within an hour, he had a signed release for a certified check of $1000.";
		String searchText = "Johnny";
		int frequency = findWordFrequency(bigText, searchText);
		
		System.out.println("The word \'" + searchText + "\' appears " +  frequency + " times in the text provided");
		
		
		/** 
		 * The following is an optimized way of doing the search for repetitive queries
		 * It uses extra memory space
		 **/

		Map<String, Integer> frequencyMap = setupFrequencyMap(bigText) ;
		frequency = findWordFrequency(bigText, searchText);
		System.out.println("The word \'" + searchText + "\' appears " +  frequency + " times in the text provided");
		
	}


	private static int findWordFrequency(String bigText, String searchText) {
		String[] words = getStringArray(bigText);

		if (words == null || words.length == 0) {
			return 0;
		}

		int frequency = 0;
		for (String word : words) {
			if (word.equalsIgnoreCase(searchText)) {
				frequency++;
			}
		}

		return frequency;
	}
	
	/**
	 * This method splits a given text and returns a word array
	 */
	public static String[] getStringArray(String text) {
		String[] words = text.split("\\s+");
		
		//replace non-word characters
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}

		//System.out.println(Arrays.toString(words));
		return words;
	}
	

	private static Map<String, Integer> setupFrequencyMap(String text) {
		String [] words = getStringArray(text);
		
		frequencyMap = getFrequencyMap();
		for (String word: words) {
			word =word.trim().toLowerCase();
			if(!frequencyMap.containsKey(word)) {
				frequencyMap.put(word, 1);
			} else {
				frequencyMap.put(word, new Integer((Integer)frequencyMap.get(word) + 1));
			}
		}
		
		System.out.println(frequencyMap);
		
		return frequencyMap;
		
	}
	
	//returns a singleton map
	private static Map<String, Integer> getFrequencyMap() {
		if (frequencyMap == null) {
			frequencyMap = new HashMap<String, Integer>();
		}

		return frequencyMap;

	}
	

}
