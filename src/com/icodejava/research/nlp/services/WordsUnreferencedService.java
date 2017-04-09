package com.icodejava.research.nlp.services;

import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.database.WordsUnreferencedDB;
import com.icodejava.research.nlp.domain.CompoundWordEnding;
import com.icodejava.research.nlp.domain.Grammar;
import com.icodejava.research.nlp.domain.Word;
import com.icodejava.research.nlp.utils.DevanagariUnicodeToRomanEnglish;

public class WordsUnreferencedService {

	public static final int HOW_MANY_WORDS = 1000000;
	public static void main(String args[]) {
	  //printCompoundWords();
	  //printCompoundWordsNotTagged();
		
	  //romanizeAndSaveWords(HOW_MANY_WORDS_TO_ROMANIZE);
	 //WordsUnreferencedDB.selectCompoundWordsNotTagged("योस्");
	  //WordsUnreferencedDB.selectCompoundWordsNotTagged("मै");
	  tagCompoundWords(HOW_MANY_WORDS);
		
		//tagRootWords(0);

	}
	
	private static void tagRootWords(int limit) {
		//load words
		List<Word> words = WordsUnreferencedDB.selectRecordsBetweenIds(20000,40000);//TODO: call the right thing here
		//run a function to find root word
		
		for(Word word:words) {
			String processed = NPTokenizer.getNepaliRootWord(word.getWord());
			//System.out.println("Original: " + word + " Root: " + );
			
			if(processed.equalsIgnoreCase(word.getWord())) {
				System.out.println("Possible Single Word:" + word);
			}
		}
		
		//update the database
	}

	private static void tagCompoundWords(int limit) {
		List<Word> words = WordsUnreferencedDB.selectRecordsNotMarkedAsCompound(limit);
		
		
		//Tag as Compound Words
		for(Word word: words) {
			String value = word.getWord();
			int length = value.length();
			
			
			 if(endsWithCompoundWord(value))
			
			/*if(value.endsWith("हरुका") ||
					value.endsWith("हरूका") ||
					value.endsWith("हरुको") ||
					value.endsWith("हरूको") ||
					value.endsWith("हरुद्वारा") ||
					value.endsWith("हरुबाट") ||
					value.endsWith("हरुमा") ||
					value.endsWith("हरुलाई") ||
					value.endsWith("हरुले") ||
					value.endsWith("हरूले") ||
					value.endsWith("हरु") ||
					value.endsWith("हरू") ||
					value.endsWith("हरूमा") ||
					value.endsWith("बाट") ||
					value.endsWith("बाटै") ||
					(value.endsWith("मध्य") && length > "मध्य".length())||
					(value.endsWith("मध्ये") && length > "मध्ये".length())||
					(value.endsWith("मार्फत") && length > "मार्फत".length())||
					(value.endsWith("स्थित") && length > "स्थित".length()) ||
					(value.endsWith("सहित") && length > "सहित".length()) ||
					(value.endsWith("समेत") && length > "समेत".length()) ||
					(value.endsWith("भित्र") && length > "भित्र".length()) ||
					(value.endsWith("सँग") && length > "सँग".length()) ||
					(value.endsWith("सँगै") && length > "सँगै".length()) ||
					(value.endsWith("विहिन") && length > "विहिन".length()) ||
					(value.endsWith("संग") && length > "संग".length()) ||
					(value.endsWith("समक्ष") && length > "समक्ष".length()) ||
					(value.endsWith("साथ") && length > "साथ".length()) ||
					(value.endsWith("भित्रै") && length > "भित्रै".length()) ||
					(value.endsWith("माथि") && length > "माथि".length()) ||
					(value.endsWith("माथिको") && length > "माथिको".length()) ||
					(value.endsWith("जस्तै") && length > "जस्तै".length()) ||
					(value.endsWith("झै") && length > "झै".length()) ||
					(value.endsWith("लगायतका") && length > "लगायतका".length()) ||
					(value.endsWith("बीचको") && length > "बीचको".length()) ||
					(value.endsWith("सम्म") && length > "सम्म".length()) ||
					(value.endsWith("बारे") && length > "बारे".length()) ||
					(value.endsWith("लाई") && length > "लाई".length() ) ||
					(value.endsWith("तीर") && length > "तीर".length() ) ||
					(value.endsWith("योस") && length > "योस".length() ) ||
					(value.endsWith("योस्") && length > "योस्".length() )
					
					
					) */
			 {
				word.setIsCompoundWord("Y");
			}
			
		} 
		
		//Then update the database
		WordsUnreferencedDB.updateWordsMarkAsCompound(words);
			
		
	}
	
	

	private static boolean endsWithCompoundWord(String word) {
		boolean shouldProcess = false;
		for(CompoundWordEnding cwe: CompoundWordEnding.values()) {
			if(word.endsWith(cwe.getNepaliWordEnding()) && word.length() > cwe.getNepaliWordEnding().length() && !isExceptionCompoundWordEnding(cwe)) {
				shouldProcess = true;
			}
			
			//Process Exceptions
			if(cwe.getNepaliWordEnding().equalsIgnoreCase(cwe.KA.getNepaliWordEnding())) {
				shouldProcess = false;
			}
		}

		return shouldProcess;
	}

	/**
	 * There shorter compound word endings need to be carefully analyzed due to
	 * the volume of word they impact, so putting an exception right now before
	 * tagging the compound word.
	 * 
	 * @param cwe
	 * @return
	 */
	private static boolean isExceptionCompoundWordEnding(CompoundWordEnding cwe) {
		
		return cwe == cwe.KA || 
				cwe ==cwe.MA ||
				cwe == cwe.KO ||
				cwe == cwe.LE || 
				cwe == cwe.MA ||
				cwe == cwe.KEE;
	}

	public static void romanizeAndSaveWords(int limit) {
		List<Word> words = WordsUnreferencedDB.selectWordsNotRomanized(limit);
		
		for(Word word: words) {
			word.setValue_romanized(DevanagariUnicodeToRomanEnglish.convertUnicodeNepaliToRomanizedEnglish(word.getWord()));
		}
		
		
	}

	/**
	 * Prints all compound words.
	 */
	public static void printCompoundWords() {

		for (CompoundWordEnding endsWith : CompoundWordEnding.values()) {
			WordsUnreferencedDB.selectCompoundWords(endsWith.getNepaliWordEnding());
		}

	}
	
	/**
	 * Prints Compound Words that are not already tagged as Compound Words
	 */
	public static void printCompoundWordsNotTagged() {

		System.out.println("====COMPOUND WORDS NOT TAGGED=====");
		for (CompoundWordEnding endsWith : CompoundWordEnding.values()) {
			WordsUnreferencedDB.selectCompoundWordsNotTagged(endsWith.getNepaliWordEnding());
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
