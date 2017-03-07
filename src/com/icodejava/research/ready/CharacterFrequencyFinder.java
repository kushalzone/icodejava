package com.icodejava.research.ready;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kushal Paudyal 
 * Created on: 01/14/2017 
 * Last Modified on: 01/14/2017
 *
 * Given a String prints the frequency of the characters appearing in it.
 * This class can be useful in operations such as Hoffman Encoding implementation.
 */
public class CharacterFrequencyFinder {
	
	public static void main (String args [])  {
		String text = "There are times high and low, there are times steady. TRUE";
		System.out.println("INPUT STRING: \n" + text + "\n===================");
		printMap(getCharacterFrequency(text));
	}
	
	private static void printMap(Map<Character, Integer> map) {
		assert map !=null && map.keySet().size() > 0;
		
		System.out.println("CHAR\tFREQUENCY");
		for (Character character:map.keySet()){
			System.out.println(character + " \t\t" + map.get(character));
		}
	}

	/**
	 * Takes text as an input
	 * Returns a HashMap of character and their count of occurrence in the input text
	 */
	private static Map<Character, Integer> getCharacterFrequency(String text) {
		
		Map<Character,Integer> frequencyMap = new HashMap<Character,Integer>();
		
		for (Character character : text.toCharArray()) {
			Integer prev = frequencyMap.get(character);
			if (prev == null) {
				frequencyMap.put(character, 1);
			}  else {
				frequencyMap.put(character, prev+1);
			}
		}
		
		return frequencyMap;
	}

}
