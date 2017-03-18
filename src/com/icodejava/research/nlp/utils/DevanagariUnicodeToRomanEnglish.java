package com.icodejava.research.nlp.utils;

import java.util.Arrays;
import java.util.List;

public class DevanagariUnicodeToRomanEnglish {
	
	static List<Character> numbersUnicode = Arrays.asList(new Character[]{'१','२','३','४','५','६','७','८','९','०'});
	static List<Character> numbersEnglish = Arrays.asList(new Character [] {'1','2','3','4','5','6','7','8','9','0'});
	
	public static void main(String args []) {
		convertToEnglish("२२३४५१७६८९०");
	}

	private static void convertToEnglish(String string) {
		String transformed = "";
		for (Character c: string.toCharArray()) {
			if(numbersUnicode.contains(c)) {
				int index = numbersUnicode.indexOf(c);
				transformed+=numbersEnglish.get(index);
				
			} else {
				transformed+=c;
			}
			
		}
		
		System.out.println(transformed);
		
	}
	

}
