package com.icodejava.research.nlp.services;

import com.icodejava.research.nlp.database.Tables;
import com.icodejava.research.nlp.database.WebsitesDB;

public class ReportingService {
	
	public static void main (String args []) {
		WebsitesDB.printRowCountByDomain();
		WebsitesDB.getRowCount(Tables.WEBSITES);
	}

}
