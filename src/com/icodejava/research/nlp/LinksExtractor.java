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
        String url="http://hamrakura.com/news-pages.php?_Id=16&per=200&p=";
    	
        for(int i=1;i<=15;i++) {
        	extractLinks(url+i+"/");
        	
        	Thread.sleep(5000);
        }
        
    }

	private static void extractLinks(String url) throws IOException {
		System.out.println("Fetching URL - " +  url);

        Document doc = Jsoup.connect(url).get();
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
        FileUtilities.writeUTF8FileAppend("C:/Users/paudyals/Desktop/NLP/URLs_Auto2.txt", String.format(msg, args));
    	//System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}