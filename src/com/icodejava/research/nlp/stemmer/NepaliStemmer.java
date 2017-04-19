package com.icodejava.research.nlp.stemmer;

import com.icodejava.research.nlp.domain.CompoundWordEnding;

/**
 * 
 * @author Kushal Paudyal
 * www.sanjaal.com | www.inepal.org | www.icodejava.com
 * 
 * Stemmer Class for Nepali Word
 * 
 * Stemming is a process of finding the root word from a compound word. 
 * e.g. "स्थानलगायत" is stemmed to "स्थान"
 *
 */
public class NepaliStemmer {
	
    public static String getNepaliRootWord(String compoundWord) {
    	for (CompoundWordEnding dir : CompoundWordEnding.values()) {
    		 String cwe = dir.getNepaliWordEnding();
    		 
    		 if(compoundWord.endsWith(cwe) && compoundWord.length() > cwe.length()) {
    		  compoundWord = compoundWord.replaceAll(cwe, "");
    		 }
    		  
    		}
    	
    	compoundWord = compoundWord.trim();
    	
    	return compoundWord;
    }

}
