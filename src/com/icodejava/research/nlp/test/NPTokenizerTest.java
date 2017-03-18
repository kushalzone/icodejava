package com.icodejava.research.nlp.test;

import java.util.List;

import com.icodejava.blog.published.utilities.FileUtilities;
import com.icodejava.research.nlp.NPTokenizer;

public class NPTokenizerTest {
	

	public static void main (String args []) {
		
		List<String> lines = FileUtilities.readUTF8FileAsList("src/com/icodejava/research/nlp/test/bad_examples.txt");
		
		//System.out.println(lines.size());
	
		for(String line: lines) {
			String clean = NPTokenizer.cleanWordToken(line+"()[]{}^");
			if(clean.trim().length() > 0) {
				System.out.println(clean.trim());
			}
		}
	}

}
