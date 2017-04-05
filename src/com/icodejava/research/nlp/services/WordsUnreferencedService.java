package com.icodejava.research.nlp.services;

import java.util.List;

import com.icodejava.research.nlp.database.WordsUnreferencedDB;
import com.icodejava.research.nlp.domain.Grammar;
import com.icodejava.research.nlp.domain.Word;
import com.icodejava.research.nlp.utils.DevanagariUnicodeToRomanEnglish;

public class WordsUnreferencedService {

	public static final int HOW_MANY_WORDS_TO_ROMANIZE = 1000;
	public static void main(String args[]) {
		//printCompoundWords();
		
	   romanizeAndSaveWords(HOW_MANY_WORDS_TO_ROMANIZE);

	}

	private static void romanizeAndSaveWords(int limit) {
		List<Word> words = WordsUnreferencedDB.selectWordsNotRomanized(limit);
		
		for(Word word: words) {
			word.setValue_romanized(DevanagariUnicodeToRomanEnglish.convertUnicodeNepaliToRomanizedEnglish(word.getWord()));
		}
		
		
	}

	public static void printCompoundWords() {

		for (String endsWith : Grammar.COMPOUND_WORD_ENDING) {
			WordsUnreferencedDB.selectCompoundWords(endsWith);
		}

	}

}
