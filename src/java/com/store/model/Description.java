package com.store.model;

public class Description implements Comparable<Object> {
	
	private int pno;
	private String langCode;
	private String text;
	
	public Description(int pno, String langCode, String text) {
		this.pno = pno;
		this.langCode = langCode;
		this.text = text;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int compareTo(Object o) {
		Description comparingTo = (Description) o;
		int numberForCompare = comparingTo.getPno();
		return this.pno-numberForCompare;
	}

	@Override
	public String toString() {
		return "Description [pno=" + pno + ", langCode=" + langCode + ", text=" + text + "]";
	}
}
