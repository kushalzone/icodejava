package com.icodejava.research.nlp.services;

import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.database.ArticlesDB;
import com.icodejava.research.nlp.database.Tables;
import com.icodejava.research.nlp.database.WordsUnreferencedDB;

public class UnreferencedWordsService {
	
	public static void main (String args []) {
		processUnreferencedWords(10000);
	}

	private static void processUnreferencedWords(int articleLimit) {
		List<Integer> unprocessedArticlesID =  ArticlesDB.selectArticlesUnProcessed(articleLimit);

		int processing = 0;
		for(Integer id: unprocessedArticlesID) {
			
			System.out.println("Processing record " + processing++ + " out of " + unprocessedArticlesID.size());
			String articleText = ArticlesDB.selectArticleTextByID(id);
			
			//tokenize 
			List<String> words = NPTokenizer.tokenizeWords(articleText);
			
			//then store the words to Database
			for(String word:words) {
				WordsUnreferencedDB.insertWord(word);
			}
			
			
			WordsUnreferencedDB.getRowCount(WordsUnreferencedDB.DATABASE_URL, Tables.WORDS_UNREFERENCED);
			
			//then mark article as processed.
			ArticlesDB.updateArticleMarkAsProcessedForUnreferenceWord(id);
			
		}
		
		//do a second round of cleaning
		WordsUnreferencedDB.cleanStrangeWords();
		
		//finally print report
		ReportingService.printUnreferencedWordReport();
		
	}
}
