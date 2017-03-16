package com.icodejava.research.nlp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.icodejava.blog.published.utilities.FileUtilities;
import com.icodejava.research.nlp.database.ArticlesDB;


public class NPTokenizer {
    /*
    private static NPTokenizer tokenizer;
    public static NPTokenizer getInstance() {
        return tokenizer!=null ? tokenizer : (tokenizer=new NPTokenizer());
    }*/

    public static void main(String args[]) throws FileNotFoundException, IOException {

        //String text = new String(FileUtilities.loadFile("C:\\Users\\paudyals\\Desktop\\NLP\\nepali_sambidhan.txt"));
        
        String text = ArticlesDB.selectArticleTextByID(7000);
        
    	//String text = HtmlTextExtractor.extractTextFromWeb("http://swasthyakhabar.com/news-details/3110/2017-02-23");
        

        
        //System.out.println("=====OriginalText=====\n" + text);

//        System.out.println("\n=====TokenizedSentences=====\n");
        tokenizeSentence(text, Terminator.NP);

        //System.out.println("\n=====TokenizedWords=====\n");
        //tokenizeWords(text);


        /*
        extractAdjancentWords(AdjacentWords.THREE_WORDS.getDepth(),
            "This is a test sentence really good one");
*/

        getWordFrequencyMap(text);

    }

    /**
     * This method takes a big text and returns a list of sentences.
     *
     * @param text
     * @param langTerminator
     */
    private static void tokenizeSentence(String text, Terminator langTerminator) {

        List<String> sentences = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(text,
                langTerminator.getSentenceTerminator());

        while (tokenizer.hasMoreElements()) {

            String token = cleanSentence(tokenizer.nextToken());

            if (token.trim().length() > 0) {
            	
                sentences.add(token.trim());
                System.out.println(token.trim());

                // extract the n-level word combination
                //extractAdjancentWords(AdjacentWords.THREE_WORDS.getDepth(), token.trim()); // TODO: Remove or improve
            }
        }
    }

    public static List<String> tokenizeWords(String text) {

        List<String> words = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(text);

        while (tokenizer.hasMoreElements()) {

            String token = cleanWordToken(tokenizer.nextToken());

            if (token.length() > 0) {
                words.add(token);
                //System.out.println(token);
            }
        }

        return words;
    }

    public static String cleanWordToken(String nextToken) {

        nextToken = nextToken.trim();
        nextToken = nextToken.replaceAll(",", "");
        nextToken = nextToken.replaceAll("\\.", "");
        nextToken = nextToken.replaceAll("‘", "");
        nextToken = nextToken.replaceAll("’", "");
        nextToken = nextToken.replaceAll("\\?", "");
        nextToken = nextToken.replaceAll("^[a-zA-Z0-9]*$", "");//remove english characters
        nextToken = nextToken.replaceAll(" ", " ");
        nextToken = nextToken.replaceAll(":", "");
        nextToken = nextToken.replaceAll("\\(", "");
        nextToken = nextToken.replaceAll("\\)", "");
        nextToken = nextToken.replaceAll("\\[", "");
        nextToken = nextToken.replaceAll("\\]", "");
        nextToken = nextToken.replaceAll("\\{", "");
        nextToken = nextToken.replaceAll("\\}", "");
        nextToken = nextToken.replaceAll("\\'", "");
        nextToken = nextToken.replaceAll("\"", "");
        nextToken = nextToken.replaceAll("। ", "");
        nextToken = nextToken.replaceAll("!", "");
        nextToken = nextToken.replaceAll("@", "");
        
        //nextToken.replaceAll("[a-zA-Z0-9?><;,{}[\\]\\-_+=!@#$%\\^&*|']*", "");
        
        
        
        

        return nextToken;
    }
    
    private static String cleanSentence(String sentence) {

        sentence = sentence.trim();
        sentence = sentence.replaceAll("  ", " ").replaceAll("  ", " ");
        //sentence = sentence.replaceAll("^[a-zA-Z0-9]*$", "");//remove english characters
        
        return sentence;
    }

    /**
     * Given a sentence, this method will return a collection of n (i.e. level) adjacent words
     */
    private static void extractAdjancentWords(int level, String sentence) {

        List<String> words = tokenizeWords(sentence);

        int levelTemp = level;
        for (int i = 0; i < words.size(); i++) {

            while (level > 0 && (i + levelTemp - 1) < words.size()) {
                System.out.print(words.get(i + levelTemp - level) + " ");
                level--;
            }
            System.out.println();
            // restore
            level = levelTemp;
        }

    }

    /**
     * Returns a list of all the files that are in a folder (does not go inside subfolder)
     */
    public List<String> getFilesInAFolder(String folderName) {

        List<String> results = new ArrayList<String>();

        File[] files = new File(folderName).listFiles();
        // If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

    /**
     * Create Word Permutations
     */
/*    public List<String> getWordPermuations(String sentence) {
        List<String> words = tokenizeWords(sentence);
    }*/


    public static Map<String,Integer> getWordFrequencyMap(String text) {
            List<String> words = tokenizeWords(text);
            Map<String, Integer> frequencyMap = new HashMap<String, Integer>();

            for (String word : words) {
                if(frequencyMap.get(word) != null) {
                    Integer frequency = frequencyMap.get(word);
                    frequency = frequency + 1;

                    frequencyMap.put(word, frequency);
                } else {
                    frequencyMap.put(word, new Integer(1));
                }
            }

            printSortedMap(frequencyMap);

            return frequencyMap;
    }

    private static void printSortedMap(Map<String, Integer> frequencyMap) {
    	

        Set<String> words =  frequencyMap.keySet();

        System.out.println("Total Words Found: " + words.size());

        List<WordFrequency> wordsList = new ArrayList<WordFrequency>();

        for(String word:words) {
            wordsList.add(new WordFrequency(word, frequencyMap.get(word)));
        }

        //Collections.sort(wordsList, new WordFrequency<T>());

        Collections.sort(wordsList, new Comparator<WordFrequency>(){
            public int compare(WordFrequency w1, WordFrequency w2) {
                return w2.getFrequency().compareTo(w1.getFrequency());  //reverse order
            }
        });

        System.out.println(wordsList);


    }

    private enum Terminator {
        NP("।?!"), EN(".");

        private String sentenceTerminator;

        Terminator(String sentenceTerminator) {

            this.sentenceTerminator = sentenceTerminator;
        }

        public String getSentenceTerminator() {

            return sentenceTerminator;
        }

    };

    private enum AdjacentWords {
        ONE_WORD(1), TWO_WORDS(2), THREE_WORDS(3), FOUR_WORDS(4), FIVE_WORDS(5), SIX_WORDS(6), SEVEN_WORDS(
                7), EIGHT_WORDS(8);

        private int depth;

        AdjacentWords(int depth) {

            this.depth = depth;
        }

        private int getDepth() {

            return depth;
        }
    }


}