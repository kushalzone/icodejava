package com.icodejava.research.nlp.domain;
/**
 * @author Kushal Paudyal
 * www.sanjaal.com | www.inepal.org | www.icodejava.com
 * 
 * This class represents a basic sentence.
 */
import java.util.ArrayList;
import java.util.List;

public class Sentence {
	int id;
	boolean linkedWordExtracted;
	boolean modified;
	String value;
	String valueRomanizedISO;
	String valueRomanizedStandard;

	boolean verified;
	int wordCount;
	List<Word> words;
	
	public Sentence(int id, String value) {
		this.id = id;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	public String getValueRomanizedISO() {
		return valueRomanizedISO;
	}
	
	public String getValueRomanizedStandard() {
		return valueRomanizedStandard;
	}

	public int getWordCount() {
		return wordCount;
	}

	public List<Word> getWords() {
		if (words == null) {
			words = new ArrayList<Word>();
		}
		return words;
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
		this.value = value;
		this.modified = true;
	}

	public void setValueRomanizedISO(String valueRomanizedISO) {
		this.valueRomanizedISO = valueRomanizedISO;
	}

	public void setValueRomanizedStandard(String valueRomanizedStandard) {
		this.valueRomanizedStandard = valueRomanizedStandard;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

}
