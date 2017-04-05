package com.icodejava.research.nlp.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.ParseURL;
import com.icodejava.research.nlp.database.ArticlesDB;
import com.icodejava.research.nlp.database.Tables;
import com.icodejava.research.nlp.database.WebsitesDB;
import com.icodejava.research.nlp.database.WordsUnreferencedDB;

public class UnreferencedWordsService {
	
	public static void main (String args []) {
		processUnreferencedWords(10000);
		
		//processWordsFromFile("src/com/icodejava/research/nlp/sources/other/misc_words.txt");
	}



	public static void processUnreferencedWords(int articleLimit) {
		List<Integer> unprocessedArticlesID =  ArticlesDB.selectArticlesUnProcessedWords(articleLimit);

		int processing = 0;
		for(Integer id: unprocessedArticlesID) {
			
			System.out.println("Processing record " + processing++ + " out of " + unprocessedArticlesID.size());
			String articleText = ArticlesDB.selectArticleTextByID(id);
			
			//tokenize 
			List<String> words = NPTokenizer.tokenizeWords(articleText);
			
			//then store the words to Database
			for(String word:words) {
				
				if(word.indexOf('â') < 0 && word.indexOf('à') < 0) {
					WordsUnreferencedDB.insertWord(word);
				}
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
	
	/**
	 * This method loads independent words from a file system
	 */
	private static void processWordsFromFile(String file) {
		BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String word;

				while ((word = reader.readLine()) != null) {

					word=NPTokenizer.cleanWordToken(word);
					WordsUnreferencedDB.insertWord(word);
				}

				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
