package com.icodejava.research.nlp.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.ParseURL;
import com.icodejava.research.nlp.database.ArticlesDB;
import com.icodejava.research.nlp.database.WebsitesDB;
import com.icodejava.research.nlp.database.WordsUnreferencedDB;

public class DatabseServices {
	
	public static void main (String args []) throws IOException {
		//insertWebLinks();
		
		
	}
	


	public static void insertWebLinks() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("src/com/icodejava/research/nlp/sources/nagariknews.txt"));
		String url;
		
		int count = 0;
		while((url = reader.readLine())!=null ) {
			
			url = url.trim();
			String domain = ParseURL.getDomainName(url);
			
			WebsitesDB.insertWebsite(domain, url);

			//System.out.println(domain + "\t" +url);
		}
		
		
		reader.close();
		
		//Print the report
		ReportingService.main(null);
	}
	


}
