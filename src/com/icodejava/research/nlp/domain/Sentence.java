package com.icodejava.research.nlp.domain;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	int id;
	String value;
	boolean verified;
	boolean linkedWordExtracted;
	boolean modified;
	List<Word> words;

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public boolean isLinkedWordExtracted() {
		return linkedWordExtracted;
	}

	public boolean isModified() {
		return modified;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLinkedWordExtracted(boolean linkedWordExtracted) {
		this.linkedWordExtracted = linkedWordExtracted;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public void setValue(String value) {
		
		//if(this.value != null && value != null && !this.value.equalsIgnoreCase(value)) {
		
			this.value = value;
			this.modified = true;
		//}
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public List<Word> getWords() {
		if(words==null) {
			words = new ArrayList<Word>();
		}
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

}
