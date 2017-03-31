package com.icodejava.research.nlp.domain;
/**
 * Represents single word object. Word could be atomic or molecular as long as it is a single word.
 */
public class Word {

	private int id;
	private String word;
	private String value_romanized;
	private String verified;
	private boolean modified;

	public Word(int id, String word, String verified) {
		this.id = id;
		this.word = word;
		this.verified = verified;
		this.modified = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getValue_romanized() {
		return value_romanized;
	}

	public void setValue_romanized(String value_romanized) {
		this.value_romanized = value_romanized;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public String toString() {
		return word;
	}

}
