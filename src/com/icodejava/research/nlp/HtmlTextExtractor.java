package com.icodejava.research.nlp;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlTextExtractor {
	
	public static void main (String args []) throws IOException {
		//extractTextFromWeb("https://ne.wikipedia.org/wiki/%E0%A4%A8%E0%A5%87%E0%A4%AA%E0%A4%BE%E0%A4%B2");
	}

	public static String extractTextFromWeb(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
		
		return doc.body().text();
	}

}
