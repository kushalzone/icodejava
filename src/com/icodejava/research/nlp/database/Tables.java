package com.icodejava.research.nlp.database;

public interface Tables {
	
	String ARTICLES="ARTICLES";
	String WEBSITES="WEBSITES";
	String SENTENCES_REFERENCED="SENTENCES";
	String WORDS_REFRENCED="WORDS";
	
	/*
	CREATE TABLE `WORDS_UNREFERENCED` (
		`ID`	integer PRIMARY KEY AUTOINCREMENT,
		`WORD`	text NOT NULL,
		`ROOT_WORD`	TEXT,
		`ROOT_WORD_EXTRACTED`	CHAR(1),
		`IS_COMPOUND_WORD`	TEXT,
		`VERIFIED`	CHAR(1),
		`NEEDS_CLEANING`	TEXT,
		`WORD_ROMANIZED`	text,
		`WORD_ROMANIZED_COMMON`	TEXT,
		`PART_OF_SPEECH`	TEXT,
		`CLASSIFICATION_1`	TEXT,
		`CLASSIFICATION_2`	TEXT,
		`CLASSIFICATION_3`	TEXT,
		`CLASSIFICATION_4`	TEXT,
		`CLASSIFICATION_5`	TEXT
	);
	 */
	String WORDS_UNREFERENCED="WORDS_UNREFERENCED";
	
	
	String SENTENCES_UNREFERENCED="SENTENCE_UNREFERENCED";
	String WORD_FREQUENCY="WORD_FREQUENCY";
	String WEBCRWAL_ACTIVITY="WEBCRAWL_ACTIVITY";
	String WORDS_LINKAGE="WORDS_LINKAGE";
	
	String TAGS="TAGS";
}
