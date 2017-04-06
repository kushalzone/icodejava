package com.icodejava.research.nlp.domain;

public class Sentence {
	int id;
	String value;
	boolean verified;
	boolean linkedWordExtracted;
	boolean modified;

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

}
