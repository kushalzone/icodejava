package com.icodejava.research.nlp.domain;
/**
 * Represents Tags to be used in Natural Language Processing for Nepali Language
 * @author Kushal Paudyal
 * www.icodejava.com | www.inepal.org
 *
 */
public enum Tags {
	LOCATION("LOCATION", "Thaun", "ठाउँ"), 
	PERSON("PERSON", "Byakti", "व्यक्ति"),
	MONTH("MONTH", "Mahina", "महिना");

	private String tagNepali;

	private String tagRomanized;
	private String tagEnglish;

	private Tags(String tagNepali, String tagRomanized, String tagEnglish) {
		this.tagNepali = tagNepali;
		this.tagRomanized = tagRomanized;
		this.tagEnglish = tagEnglish;
	}

	public String getTagNepali() {
		return tagNepali;
	}

	public String getTagRomanized() {
		return tagRomanized;
	}

	public String getTagEnglish() {
		return tagEnglish;
	}

}
