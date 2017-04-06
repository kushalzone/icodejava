package com.icodejava.research.nlp;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlTextExtractor {
	
	public static void main (String args []) throws IOException {
	}

	public static String extractTextFromWeb(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
		
		return doc.body().text();
	}

}
