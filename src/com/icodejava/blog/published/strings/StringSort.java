package com.icodejava.blog.published.strings;

import java.util.Arrays;

/**
 * @author Kushal Paudyal 
 * www.icodejava.com 
 * Created On - Feb 14, 2014 
 * Last Modified On - Feb 14, 2014
 */
public class StringSort {
	public static void main(String args[]) {
		System.out.println(sort("Please Sort Me"));
	}

	/**
	 * @param string - String to be sorted
	 * @return sorted string
	 */
	private static String sort(String string) {
		if (string == null) {
			return null;
		}

		char[] characters = string.toCharArray();
		Arrays.sort(characters);
		return new String(characters);

	}

}
