package com.icodejava.research.nlp;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.icodejava.blog.published.utilities.FileUtilities;

import java.io.IOException;

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
    	String url="http://www.samakalinsahitya.com/index.php?show=category&cat_id=24&page="; //1:0-11,2:0-6,3:0-28,4:0-9,5:0-23,8:0-2,10:0-3,11:0-4,13:0-10,16:0-3,17:0-1,18:0-1,20:0-1,24:0-2
        for(int i=0;i<=2;i++) {
        	extractLinks(url+i);
        	//extractLinks(url+i+"/");
        	
        	
        	Thread.sleep(2000);
        }
        
    }

	private static void extractLinks(String url) throws IOException {
		System.out.println("Fetching URL - " +  url);
		//Document doc = Jsoup.connect(url).get();
		Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        
        //Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36").get();
        //System.out.println(doc.text()); //REMOVE
        Elements links = doc.select("a[href]");

        //print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(link.attr("abs:href"), trim(link.text(), 35));
        }
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
        FileUtilities.writeUTF8FileAppend("C:/Users/paudyals/Desktop/NLP/URLs_Auto3.txt", String.format(msg, args));
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