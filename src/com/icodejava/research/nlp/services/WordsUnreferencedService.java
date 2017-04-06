package com.icodejava.research.nlp.services;

import java.util.List;

import com.icodejava.research.nlp.database.WordsUnreferencedDB;
import com.icodejava.research.nlp.domain.Grammar;
import com.icodejava.research.nlp.domain.Word;
import com.icodejava.research.nlp.utils.DevanagariUnicodeToRomanEnglish;

public class WordsUnreferencedService {

	public static final int HOW_MANY_WORDS = 1000000;
	public static void main(String args[]) {
	  //printCompoundWords();
	  //printCompoundWordsNotTagged();
		
	  // romanizeAndSaveWords(HOW_MANY_WORDS_TO_ROMANIZE);
		
	  tagCompoundWords(HOW_MANY_WORDS);

	}

	private static void tagCompoundWords(int limit) {
		List<Word> words = WordsUnreferencedDB.selectRecordsNotMarkedAsCompound(limit);
		
		
		//Tag as Compound Words
		for(Word word: words) {
			if(word.getWord().endsWith("हरुका") ||
					word.getWord().endsWith("हरूका") ||
					word.getWord().endsWith("हरुको") ||
					word.getWord().endsWith("हरूको") ||
					word.getWord().endsWith("हरुद्वारा") ||
					word.getWord().endsWith("हरुबाट") ||
					word.getWord().endsWith("हरुमा") ||
					word.getWord().endsWith("हरुलाई") ||
					word.getWord().endsWith("हरुले") ||
					word.getWord().endsWith("हरूले") ||
					word.getWord().endsWith("हरु") ||
					word.getWord().endsWith("हरू") ||
					word.getWord().endsWith("हरूमा") ||
					word.getWord().endsWith("बाट") ||
					word.getWord().endsWith("बाटै") ||
					(word.getWord().endsWith("मध्य") && word.getWord().length() > "मध्य".length())||
					(word.getWord().endsWith("मध्ये") && word.getWord().length() > "मध्ये".length())||
					(word.getWord().endsWith("मार्फत") && word.getWord().length() > "मार्फत".length())||
					(word.getWord().endsWith("स्थित") && word.getWord().length() > "स्थित".length()) ||
					(word.getWord().endsWith("सहित") && word.getWord().length() > "सहित".length()) ||
					(word.getWord().endsWith("समेत") && word.getWord().length() > "समेत".length()) ||
					(word.getWord().endsWith("सँग") && word.getWord().length() > "सँग".length()) ||
					(word.getWord().endsWith("भित्र") && word.getWord().length() > "भित्र".length()) ||
					(word.getWord().endsWith("माथिको") && word.getWord().length() > "माथिको".length()) ||
					(word.getWord().endsWith("जस्तै") && word.getWord().length() > "जस्तै".length()) ||
					(word.getWord().endsWith("झै") && word.getWord().length() > "झै".length()) ||
					(word.getWord().endsWith("लगायतका") && word.getWord().length() > "लगायतका".length()) ||
					(word.getWord().endsWith("बीचको") && word.getWord().length() > "बीचको".length())
					
					) {
				word.setIsCompoundWord("Y");
			}
			
		} 
		
		//Then update the database
		WordsUnreferencedDB.updateWordsMarkAsCompound(words);
			
		
	}

	private static void romanizeAndSaveWords(int limit) {
		List<Word> words = WordsUnreferencedDB.selectWordsNotRomanized(limit);
		
		for(Word word: words) {
			word.setValue_romanized(DevanagariUnicodeToRomanEnglish.convertUnicodeNepaliToRomanizedEnglish(word.getWord()));
		}
		
		
	}

	/**
	 * Prints all compound words.
	 */
	public static void printCompoundWords() {

		for (String endsWith : Grammar.COMPOUND_WORD_ENDING) {
			WordsUnreferencedDB.selectCompoundWords(endsWith);
		}

	}
	
	/**
	 * Prints Compound Words that are not already tagged as Compound Words
	 */
	public static void printCompoundWordsNotTagged() {

		System.out.println("====COMPOUND WORDS NOT TAGGED=====");
		for (String endsWith : Grammar.COMPOUND_WORD_ENDING) {
			WordsUnreferencedDB.selectCompoundWordsNotTagged(endsWith);
		}

	}
	
	/**
	 * Prints word counts that are already tagged as Compound Words
	 */
	public static void printCompoundWordsNotTaggedCount() {

		System.out.println("====COMPOUND WORDS TAGGED=====");
		System.out.println(WordsUnreferencedDB.selectCompoundWordsTaggedCount());

	}

}
