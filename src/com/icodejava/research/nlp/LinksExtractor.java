package com.icodejava.research.nlp;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.icodejava.blog.published.utilities.FileUtilities;

/**
 * Example program to list links from a URL.
 */
public class LinksExtractor {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
    	
        //String url = "http://ujyaaloonline.com/news/category/29/literature-art/page/";
        //String url="http://hamrakura.com/news-pages.php?_Id=16&per=200&p=";
    	//String url="http://www.onlinekhabar.com/content/news/page/";//HAS ISSUES
    	//String url="http://www.majheri.com/node?page=";//HAS ISSUES
    	//String url="http://setopati.com/raajneeti/page/";
    	//String url="http://www.ratopati.com/%E0%A4%A6%E0%A5%87%E0%A4%B6/page/1/"; //HAS ISSUES
    	//String url = "http://www.mysansar.com/category/news-views/page/"; //1-87
    	//String url = "http://www.mysansar.com/category/saturday-fiction/page/";//1-9
    	//String url = "http://tokyonepal.com/category/nepal-news/page/"; //1-387
    	//String url = "http://nepalipost.com/index/?catid=1&php=1&p="; // 1-769
    	//String url="http://www.samakalinsahitya.com/index.php?show=category&cat_id=24&page="; //1:0-11,2:0-6,3:0-28,4:0-9,5:0-23,8:0-2,10:0-3,11:0-4,13:0-10,16:0-3,17:0-1,18:0-1,20:0-1,24:0-2
        
    	/*String url="http://www.newsnrn.com/category/america/page/";//1-73 HAS ISSUES
    	 * */
    	//String url="http://www.nagariknews.com/category/21?page="; //1-203
    	//String url = "http://www.nagariknews.com/category/22?page="; //1-94
    	//String url = "http://www.nagariknews.com/category/24?page="; //1-228
    	String url="http://www.dainiknepal.com/section/latest-news/page/";//1-3114
    	/*
    	 * TODO:Extract Following
    	 *
	    	 http://www.dainiknepal.com/section/views/page/90; 
	    	 http://www.dainiknepal.com/section/kala/page/265
	    	 http://www.dainiknepal.com/section/diaspora/page/143
	    	 http://www.dainiknepal.com/section/market/page/412
	    	 http://www.dainiknepal.com/section/sports/page/161
	    	 http://www.dainiknepal.com/section/rochak/page/88
	    	 http://www.dainiknepal.com/section/health/page/49

			http://www.nagariknews.com/category/25?page=31
			http://www.nagariknews.com/category/26?page=71
			http://www.nagariknews.com/category/27?page=46
			http://www.nagariknews.com/category/28?page=13
			http://www.nagariknews.com/category/33?page=8
			http://www.nagariknews.com/category/81?page=55
			http://www.nagariknews.com/category/82?page=4
			http://www.nagariknews.com/category/37?page=3
    	 */
    	//SINGLE PAGE
    	for(int i=50;i<=3114;i++) {
        	extractLinks(url+i);
        	//extractLinks(url+i+"/");
        	Thread.sleep(4000);
        }
    	
    	//MULTIPLE PAGES
    	//extractLinksFromFile("src/com/icodejava/research/nlp/sources/temp/temp.txt");
        
    }
    
    /*
     * This method reads a list links from a file, loads those pages and extracts links from those pages.
     */
    private static void extractLinksFromFile(String fileName) throws IOException, InterruptedException {
    	List<String> pages = FileUtilities.readUTF8FileAsList(fileName);
    	
    	for(String page: pages) {
    		try{
    		extractLinks(page);
    		Thread.sleep(3000);
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	//System.out.println(pages);
    	
    }

	private static boolean extractLinks(String url) throws IOException, InterruptedException {
		boolean successful = false;
		System.out.println("Fetching URL - " +  url);
		
		Document doc = readHTMLDocument(url);
		
		if(doc == null) {
			//Try again
			doc = readHTMLDocument(url);
		} 
		
		if(doc != null) {
        
	        Elements links = doc.select("a[href]");
	
	        for (Element link : links) {
	            print(link.attr("abs:href"), trim(link.text(), 35));
	        }
	        
	        successful = true;
		}
        
        return successful;
	}

	private static Document readHTMLDocument(String url) throws InterruptedException {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
		} catch (Exception e) {

			System.out.println("Error Occurred while trying to read: " + url +" WILL TRY ONE MORE TIME IN 10s.");
			Thread.sleep(10000);

		}
		return doc;
	}

	private static void extractImportURLs(String url) throws IOException {
		
		System.out.println("Fetching URL - " +  url);
        Document doc = Jsoup.connect(url).get();

		Elements imports = doc.select("link[href]");
        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }
	}

	private static void extractMediaLinks(String url) throws IOException {
		System.out.println("Fetching URL - " +  url);

        Document doc = Jsoup.connect(url).get();
        
		Elements media = doc.select("[src]");
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }
	}

    private static synchronized void print(String msg, Object... args) {
    	try{
        FileUtilities.writeUTF8FileAppend("C:/Users/paudyals/Desktop/NLP/URLs_dainiknepal.txt", String.format(msg, args));
    	//System.out.println(String.format(msg, args));
    	} catch(Exception e) {
    		System.err.println("Some Link Extraction Failed");
    		//Be graceful, don't fail
    	}
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}