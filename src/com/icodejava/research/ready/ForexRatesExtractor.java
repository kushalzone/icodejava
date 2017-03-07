package com.icodejava.research.ready;

/**
 * @author Kushal Paudyal
 * Created on: 12/17/2016
 * Last Modified on: 12/17/2016
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForexRatesExtractor {
	
	public static void main(String args[]) throws Exception {

		extractExchangeRates(new Date()); //i.e. Today
		
		
		Date startDate = new GregorianCalendar(2016, Calendar.DECEMBER, 16).getTime();
		Date endDate = new GregorianCalendar(2016, Calendar.DECEMBER, 17).getTime();
		//extractExchangeRatesBetween(startDate, endDate);
		

	}

	private static void extractExchangeRatesBetween(Date startDate, Date endDate) throws IOException {
		
		if (startDate == null || endDate == null) {
			return;
		}
		
		Calendar startCalendar = Calendar.getInstance();
	    startCalendar.setTime(startDate);
	    Calendar endCalendar = Calendar.getInstance();
	    endCalendar.setTime(endDate);

	    for(; startCalendar.compareTo(endCalendar)<=0;
	          startCalendar.add(Calendar.DATE, 1)) {
	    	extractExchangeRates(startCalendar.getTime());
	    }
		
	}

	private static void extractExchangeRates(Date date) throws IOException {
		
		if (date == null) {
			return;
		}
		
		System.out.println("Extracing Exchange Rates For - " + date);
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    StringTokenizer tokenizer = null;
	    
	    List <ExchangeRate> rates = new ArrayList<ExchangeRate> ();
		
		/** Load Document From URL **/
		Document doc = Jsoup.connect("https://www.nrb.org.np/fxmexchangerate.php?YY="+year+"&MM="+month+"&DD="+day+"&B1=Go").get();
		
		// Get Indian Rupee. Rastra Bank is printing this separate from other currencies.
		Element table = doc.select("table").get(5);

		Elements rows = table.select("tr");
		for (int i = 1; i < rows.size(); i++) { // first row is the col names so
												// skip it.
			Element row = rows.get(i);
			Elements cols = row.select("font");

			if (!cols.html().contains("Currency")) {
				String html = cols.html();
				html = html.replaceFirst("<span.*nbsp;", "");
				
				tokenizer =new StringTokenizer(html);
				
				int count = 0;
				ExchangeRate rate = new ExchangeRate();
				while (tokenizer.hasMoreTokens()) {
					System.out.print(tokenizer.nextToken() + " ");
					
					if(count == 0) {
						
					}
				}
				
				System.out.println();
			}

		}

		//Get other currencies
		table = doc.select("table").get(6);
		rows = table.select("tr");
		for (int i = 1; i < rows.size(); i++) { // first row is the col names so
												// skip it.
			Element row = rows.get(i);
			Elements cols = row.select("font");

			if (!cols.html().contains("Currency")) {
				String html = cols.html();
				html = html.replaceFirst("<span.*nbsp;", "");
				
				tokenizer =new StringTokenizer(html);
				
				while (tokenizer.hasMoreTokens()) {
					System.out.print(tokenizer.nextToken() + " ");
				}
				
				System.out.println();
			}

		}
	}
}
