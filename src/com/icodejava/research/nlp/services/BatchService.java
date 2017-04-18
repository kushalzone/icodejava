package com.icodejava.research.nlp.services;
/**
 * 
 * @author Kushal Paudyal
 * 
 * This class contains methods that will potentially take longer time. 
 * 
 * Suitable to run this service (such as over night)
 *
 */
public class BatchService {
	
	public static void main(String args []) throws InterruptedException {
		
		//Process Unreferenced Words - Extrac Words from Articles
		
		WordsUnreferencedService.cleanWordsInDatabase(0, 1000000);
		
		//cleaning words can always leave duplicates, so remove duplicates. 
		WordsUnreferencedService.removeDuplication();
		
		//Tag Compound Words
		WordsUnreferencedService.tagCompoundWords(500000);
		
		
		//Process Unreferenced Sentences - Extract Sentences From Articles
		
		//clean sentences
		
		//recalculate word count
		
		//Extract Word Linkage
		
		
		
		
		//Process Article Titles
		
	}

}
