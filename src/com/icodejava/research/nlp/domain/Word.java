package com.icodejava.research.nlp.domain;
/**
 * Represents single word object. Word could be atomic or molecular as long as it is a single word.
 */
public class Word {

	private int id;
	private String word;
	private String value_romanized;
	private String verified;
	private String isCompoundWord;
	
	private boolean modified;

	
	//Marked for Split
	//Marked for Delete
	//Marked as Single Word
	//Marked as Compound Word
	public Word(int id, String word, String verified) {
		this.id = id;
		this.word = word;
		this.verified = verified;
		this.modified = false;
	}

	public int getId() {
		return id;
	}

	public String getIsCompoundWord() {
		return isCompoundWord;
	}

	public String getValue_romanized() {
		return value_romanized;
	}

	public String getVerified() {
		return verified;
	}

	public String getWord() {
		return word;
	}

	public boolean isModified() {
		return modified;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsCompoundWord(String isCompoundWord) {
		this.isCompoundWord = isCompoundWord;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public void setValue_romanized(String value_romanized) {
		this.value_romanized = value_romanized;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public String toString() {
		return word;
	}


}
