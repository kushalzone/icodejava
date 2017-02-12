package com.icodejava.blog.published;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 1/9/2017
 * Last Modified on: 1/9/2017
 *
 */
public class StringRotationFinder {
	private static final boolean CASE_SENSITIVE = true;
	public static void main (String args []) {
		String firstString = "This is a beautiful house";
		String secondString ="a beautiful housethis is ";
		
		isRotated(firstString, secondString, CASE_SENSITIVE);
		isRotated(firstString, secondString, !CASE_SENSITIVE);
		
	}

	/**
	 * The rotated string is a subset of non-repeated String + non-repeated String
	 * 
	 * e.g. "abc abd" is original string and "c abda" which is a part of "abc abdabc abd"
	 * 
	 * This method uses a case sensitivity flag.
	 */
	private static void isRotated(String firstString, String secondString, boolean isCaseSensitive) {
		System.out.println("First String: " + firstString);
		System.out.println("Second String: " + secondString);
		boolean isRotated = false;
		if (firstString != null && secondString != null && firstString.length() == secondString.length()) {
			
			String concatenatedString = firstString + firstString;
			
			//Assuming the cases are not sensitive
			if(!isCaseSensitive) {
				concatenatedString = concatenatedString.toLowerCase();
				secondString = secondString.toLowerCase();
			}
			
			if (concatenatedString.contains(secondString)) {
				isRotated = true;
			}
		}
		
			
		System.out.println("First String and Second Strings are roated forms of is other (Case Sensitive: " +  isCaseSensitive +")? "  + isRotated );
		
	}

}
