package com.icodejava.research.nlp.domain;

public class Word {

	private int id;
	private String word;
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
