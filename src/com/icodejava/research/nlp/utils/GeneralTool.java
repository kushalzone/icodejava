package com.icodejava.research.nlp.utils;

public class GeneralTool {
	public static void main(String args []) {
		
		String url = "http://tokyonepal.com/category/nepal-news/page/"; //1-387
    	
        for(int i=1;i<=387;i++) {
        	System.out.println("Fetching URL - "+url+i+"/");
        }
		
	}

}
