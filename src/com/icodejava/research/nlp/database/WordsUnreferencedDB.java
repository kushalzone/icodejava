package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icodejava.research.nlp.NPTokenizer;
import com.icodejava.research.nlp.domain.Grammar;
import com.icodejava.research.nlp.domain.Word;

public class WordsUnreferencedDB extends DBUtility {
	
	
	public static final String DATABASE_URL = "jdbc:sqlite:C:/Users/paudyals/Desktop/NLP/nlpdb/npl3.db";//shadowed from parent
	
	
	public static void main (String args []) throws ClassNotFoundException {
		//createNewDatabase(DATABASE_URL);
		//createWordsUnreferencedTable();
		//insertWord("test");
		//updateWord(4, "Tested");
		//selectAllRecords();
		//deleteAllRecords(DATABASE_URL, Tables.WORDS_UNREFERENCED);
		//deleteRecordsByID(4);
		
		
		//selectRandomRecords(1000);
		//selectRecordsBetweenIds(0, 50000);
		//selectRecordCountByLength();
		
		selectWordWithLengthGreaterThan(20);
		//selectWordWithLengthEqualTo(3);
		
		//cleanWords();
		//cleanStrangeWords();
		
		//selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word like '%à%'");
		//selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word = '÷'");
		
		//selectWithQuery("SELECT COUNT (DISTINCT WORD) FROM " + Tables.WORDS_UNREFERENCED);
		
		//selectCompoundWords("लाई");
		
	}
	
	public static void createWordsUnreferencedTable() {
		/**
		 * CREATE TABLE `WORDS_UNREFERENCED` (
				`ID`	integer PRIMARY KEY AUTOINCREMENT,
				`WORD`	text NOT NULL,
				`WORD_ROMANIZED`	text,
				`VERIFIED`	CHAR(1),
				`PART_OF_SPEECH`	TEXT,
				`CLASSIFICATION_1`	TEXT,
				`CLASSIFICATION_2`	TEXT,
				`CLASSIFICATION_3`	TEXT,
				`CLASSIFICATION_4`	TEXT,
				`CLASSIFICATION_5`	TEXT
			);
		 */
		
		String sql = "CREATE TABLE IF NOT EXISTS "+ Tables.WORDS_UNREFERENCED +" (\n" 
					 + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
					 + " WORD text NOT NULL,\n" 
					 + " WORD_ROMANIZED text,\n" 
					 + " VERIFIED CHAR(1),\n" 
					 + " PART_OF_SPEECH	TEXT,\n"
					 + " CLASSIFICATION_1	TEXT,\n"
					 + " CLASSIFICATION_2	TEXT,\n"
					 + " CLASSIFICATION_3	TEXT,\n"
					 + " CLASSIFICATION_4	TEXT,\n"
					 + " CLASSIFICATION_5	TEXT"
				 
				 + ");";

		createNewTable(DATABASE_URL,sql);
	}
	
	
	
	public static void selectAllRecords() {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" ORDER BY WORD ASC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD") + "\t" + rs.getString("VERIFIED"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Word> selectRecordsNotMarkedAsCompound() {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE IS_COMPOUND_WORD IS NULL OR IS_COMPOUND_WORD='N' ORDER BY WORD ASC";

		List<Word> words = new ArrayList<Word>();
		
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				//System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD") + "\t" + rs.getString("VERIFIED"));
				words.add(new Word(rs.getInt("ID"), rs.getString("WORD"), rs.getString("VERIFIED") ));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	public static List<Word> selectRecordsNotMarkedAsCompound(int limit) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE IS_COMPOUND_WORD IS NULL OR IS_COMPOUND_WORD='N' ORDER BY WORD ASC LIMIT " + limit ;

		List<Word> words = new ArrayList<Word>();
		
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				//System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD") + "\t" + rs.getString("VERIFIED"));
				words.add(new Word(rs.getInt("ID"), rs.getString("WORD"), rs.getString("VERIFIED") ));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	public static List<Word> selectWithQuery(String sql) {

		List<Word> words = new ArrayList<Word>();
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD"));
				words.add(new Word(rs.getInt("ID"), rs.getString("WORD"), rs.getString("VERIFIED") ));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	public static void selectRecordCountByLength() {
		String sql = "SELECT Length(WORD), count (*) FROM " +  Tables.WORDS_UNREFERENCED +" GROUP BY LENGTH(WORD) ORDER BY 1 DESC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getString(1)+" " + rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void selectWordWithLengthGreaterThan(int length) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" where LENGTH(WORD) > " + length + " ORDER BY 1 DESC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getString(1)+" " + rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void selectWordWithLengthLessThan(int length) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" where LENGTH(WORD) < " + length + " ORDER BY 1 DESC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getString(1)+" " + rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void selectWordWithLengthEqualTo(int length) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" where LENGTH(WORD) = " + length + " ORDER BY 1 DESC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getString(1)+" " + rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Word> selectCompoundWords(String endsWith) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" where WORD LIKE '%"+endsWith+"'";

		List<Word> words = new ArrayList<Word>();
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			

			Word word = null;
			while (rs.next()) {
				word = new Word(rs.getInt(1), rs.getString(2), rs.getString(3));
				//System.out.println(rs.getString(1)+" " + rs.getString(2));
				words.add(word);
			}
			System.out.println("Found: " +  words.size() + " Records ending in --> " + endsWith);
			//System.out.println(words); //THIS IS CAUSING MEMORY ISSUES
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	/**
	 * Fetches a list of words ending with endsWith that are not already tagged as compound words
	 * @param endsWith
	 * @return
	 */
	public static List<Word> selectCompoundWordsNotTagged(String endsWith) {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" where IS_COMPOUND_WORD IS NULL AND WORD LIKE '%"+endsWith+"'";

		List<Word> words = new ArrayList<Word>();
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			

			Word word = null;
			while (rs.next()) {
				word = new Word(rs.getInt(1), rs.getString(2), rs.getString(3));
				//System.out.println(rs.getString(1)+" " + rs.getString(2));
				words.add(word);
			}
			if(words.size() > 0) {
				System.out.println("Found: " +  words.size() + " Records ending in --> " + endsWith);
			}
			//System.out.println(words); //THIS IS CAUSING MEMORY ISSUES
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	
	public static int selectCompoundWordsTaggedCount() {
		String sql = "SELECT count(*) FROM " +  Tables.WORDS_UNREFERENCED +" where IS_COMPOUND_WORD=\"Y\"";
		
		int count = 0;

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;
	}
	
	public static void selectRandomRecords(int limit) {
		
		 //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE ID IN (SELECT ID FROM " + Tables.WORDS_UNREFERENCED +" ORDER BY RANDOM()  LIMIT " + limit + ") ORDER BY WORD ASC";

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD") + "\t" + rs.getString("VERIFIED"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Word> selectRecordsBetweenIds(int start, int end) {
		List<Word> words = new ArrayList<Word>();
		
		 //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE ID BETWEEN " + start + " AND " + end;

		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				words.add(new Word(rs.getInt("ID"), rs.getString("WORD"), rs.getString("VERIFIED") ));
				//System.out.println(rs.getInt("ID") + "\t" + rs.getString("WORD") + "\t" + rs.getString("VERIFIED"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	public static List<Word> selectWordsNotRomanized(int limit) {
		//String sql = "SELECT ID, WORD, VERIFIED FROM " +  Tables.WORDS_UNREFERENCED +" WHERE WORD_ROMANIZED IS NULL ORDER BY WORD ASC LIMIT " + limit +";" ;
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE ID IN (SELECT ID FROM " + Tables.WORDS_UNREFERENCED +" WHERE WORD_ROMANIZED IS NULL ORDER BY RANDOM()  LIMIT " + limit + ") ORDER BY WORD ASC";
		System.out.println(sql);

		List<Word> words = new ArrayList<Word>();
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			

			Word word = null;
			while (rs.next()) {
				word = new Word(rs.getInt(1), rs.getString(2), rs.getString(3));
				words.add(word);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return words;
	}
	
	public static void deleteRecordsByID(int id) {

		String sql = "DELETE FROM "+ Tables.WORDS_UNREFERENCED +" WHERE ID=" + id;

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.executeUpdate();

			System.out.println("Successfully Deleted record: " + id);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	public static void deleteRecordsByDomain(String word) {

		String sql = "DELETE FROM "+ Tables.WORDS_UNREFERENCED +" WHERE ID=\"" + word +"\"";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int count = pstmt.executeUpdate();

			System.out.println("Successfully Deleted records: " +  count);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
	
	public static boolean alreadyExists(String word) {
		boolean alreadyExists = false;
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED +" WHERE WORD=\"" + word + "\"";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				//System.out.println("Word Already Exists in "+ Tables.WORDS_UNREFERENCED);
				alreadyExists = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return alreadyExists;
	}
	
	public static int insertWord(String word) {
		int rowID = -1;
		if (alreadyExists(word)) {
			return rowID;
		}

		String sql = "INSERT INTO "+ Tables.WORDS_UNREFERENCED + " (WORD) VALUES(?)";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, word);
			pstmt.executeUpdate();

			// Get Article ID back
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select seq from sqlite_sequence where name=\"" + Tables.WORDS_UNREFERENCED + "\"");

			System.out.println("Inserted Record ID: " + (rowID = rs.getInt(1)) + " WORD: " + word);

		} catch (Exception e) {
			//GRACEFUL
			e.printStackTrace();
		}

		return rowID;
	}
	
	public static void updateWord(int id, String wordNewValue) {

		String sql = "UPDATE " + Tables.WORDS_UNREFERENCED + " SET WORD=? WHERE ID=?";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, wordNewValue);
			pstmt.setInt(2, id);
			int result = pstmt.executeUpdate();

			if(result > 0) {
				//System.out.println("Successfully updated word");
			} else {
				System.out.println("Could not update the word. Make sure the ID exists or there are no other issues");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void updateWordTagAsCompound(int id, String isCompoundWord) {

		String sql = "UPDATE " + Tables.WORDS_UNREFERENCED + " SET IS_COMPOUND_WORD=? WHERE ID=?";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, isCompoundWord);
			pstmt.setInt(2, id);
			int result = pstmt.executeUpdate();

			if(result > 0) {
				//System.out.println("Successfully updated word");
			} else {
				System.out.println("Could not update the word. Make sure the ID exists or there are no other issues");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void updateWords(List<Word> words) {
		if(words == null || words.isEmpty()) {
			return;
		}
		
		int count=0;
		for(Word word:words) {
			
			if(count%1000 == 0 ) {
				System.out.println("UPDATED: "+ count + " of " + words.size());
			}
			
			
			if(word.getWord().trim().length() == 0 || word.getWord().trim().length() > 24) { //deleting empty and longer sentences
				deleteRecordsByID(word.getId());
			} else if (word.isModified()){
				updateWord(word.getId(), word.getWord());
			} else {
				//System.out.println("Not Modified " + word );
			}
			
			count++;
		}
		
		

	}
	
	public static void updateWordsMarkAsCompound(List<Word> words) {
		
		if(words == null || words.isEmpty()) {
			return;
		}
		
		int count=0;
		for(Word word:words) {
			
			if(count%1000 == 0 ) {
				System.out.println("UPDATED: "+ count + " of " + words.size());
			}
			if("Y".equalsIgnoreCase(word.getIsCompoundWord())) {
				updateWordTagAsCompound(word.getId(), "Y");
				System.out.println("Found compound word " + word);
			}
			
			count++;
		}
		
	}
	
	public static void cleanWords() {
		List<Word> words = selectRecordsBetweenIds(0, 1000000);
		//List<Word> words = selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word like '%à%'");
		
		int count = 0;
		for(Word word:words) {
			String current = word.getWord();
			//System.out.print(word + "\t");
			word.setWord(NPTokenizer.cleanWordToken(word.getWord()));
			
			String cleaned = word.getWord();
			
			//System.out.println(word.getId() + " Dirty: " + current + " Cleaned: " + cleaned);
			
			if(!current.equalsIgnoreCase(cleaned)) {
				word.setModified(true);
			}
			
			if(count%1000 == 0 ) {
				System.out.println("CLEANED: "+ count + " of " + words.size());
			}
			
			count++;
			
			//System.out.print(word + "\n");
		}
		
		updateWords(words);
		
		
		
		cleanStrangeWords();
		
		
		
	}
	
	public static void cleanStrangeWords() {
		List<Word> words = selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word like '%à%'");
		
		for (Word word: words){
			System.out.println(word);
			deleteRecordsByID(word.getId());
		}
		
		words = selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED + " where length(word)=1");
		
		for(Word word: words) {
			if(!Grammar.LETTERS.contains("word")) {
				deleteRecordsByID(word.getId());
				System.out.println("Deleted " + word);
			}
		}
		
	}


}
