package com.icodejava.research.nlp.services;

import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.database.ArticlesDB;
import com.icodejava.research.nlp.database.SentencesUnreferencedDB;
import com.icodejava.research.nlp.domain.Article;
import com.icodejava.research.nlp.domain.Sentence;

public class SentenceUnreferencedService {
	
	public static void main (String args []) {
		
		//extractUnreferencedSentencesFromArticles(20000);
		cleanSentences(2000000);
	}

	private static void extractUnreferencedSentencesFromArticles(int articleLimit) {
		List<Integer> unprocessedArticlesID =  ArticlesDB.selectArticlesUnProcessedSentences(articleLimit);

		int processing = 0;
		for(Integer id: unprocessedArticlesID) {
			
			try{ 
			
			System.out.println("Processing article " + processing++ + " out of " + unprocessedArticlesID.size());
			String articleText = ArticlesDB.selectArticleTextByID(id);
			
			//tokenize 
			List<String> sentences = NPTokenizer.tokenizeSentence(articleText, NPTokenizer.Terminator.NP);
			
			//then store the sentence to Database
			for(String sentence:sentences) {
				
				SentencesUnreferencedDB.insertSentence(sentence);
			}
			
			
			//WordsLinkedUnreferencedDB.getRowCount(WordsLinkedUnreferencedDB.DATABASE_URL, Tables.WORDS_UNREFERENCED);
			
			//then mark article as processed.
			ArticlesDB.updateArticleMarkAsProcessedForUnreferenceSentences(id);
			
			} catch (Exception e) {
				e.printStackTrace(); //gracefuL
			}
			
		}
		
		//do a second round of cleaning
		//WordsUnreferencedDB.cleanStrangeWords();
		
		//finally print report
		//ReportingService.printUnreferencedSentenceReport();
		
	}
	
	
	private static void cleanSentences(int limit) {
		//Load Titles that are not marked as clean
		List<Sentence> sentences = SentencesUnreferencedDB.selectRecordsWithLimit(limit);
		
		for(Sentence sentence : sentences) {
			
			//System.out.println("Original: " + sentence.getValue());
			
			//clean sentences
			sentence.setValue(NPTokenizer.cleanSentence(sentence.getValue()));
			
			//System.out.println("Cleaned: " + sentence.getValue());

		}
		
		//update sentence
		SentencesUnreferencedDB.updateSentences(sentences);
		
		
	}
	

}
