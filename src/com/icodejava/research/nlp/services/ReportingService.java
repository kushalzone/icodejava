package com.icodejava.research.nlp.services;

import com.icodejava.research.nlp.database.ArticlesDB;
import com.icodejava.research.nlp.database.Tables;
import com.icodejava.research.nlp.database.WebsitesDB;
import com.icodejava.research.nlp.database.WordsUnreferencedDB;

public class ReportingService {
	
	public static void main (String args []) {
		
		WebsitesDB.printRowCountByDomain();
		WebsitesDB.getRowCount(Tables.WEBSITES);
		
		WebsitesDB.printCrawledSitesCount();
		WebsitesDB.printUncrawledSitesCount();
		
		WebsitesDB.printRowCountByDomainUncrawled();
		
		printUnreferencedWordReport();
		printUnreferencedSentenceReport();
		
		printArticleTitleInfo();
	}
	
	
	public static void printUnreferencedWordReport() {
		System.out.println("========================================");
		ArticlesDB.selectArticlesCount();
		ArticlesDB.selectArticlesCountProcessedForUnreferenceWord();
		WordsUnreferencedDB.getRowCount(WordsUnreferencedDB.DATABASE_URL,Tables.WORDS_UNREFERENCED);
		
	}
	
	public static void printUnreferencedSentenceReport() {
		System.out.println("========================================");
		ArticlesDB.selectArticlesCount();
		ArticlesDB.selectArticlesCountProcessedForUnreferenceSentence();
		WordsUnreferencedDB.getRowCount(WordsUnreferencedDB.DATABASE_URL,Tables.WORDS_UNREFERENCED);
		
	}
	
	public static void printArticleTitleInfo() {
		ArticlesDB.selectArticlesWithTitlesCount();
		ArticlesDB.selectArticlesWithoutTitlesCount();
	}

}
