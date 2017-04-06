package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icodejava.research.nlp.domain.Sentence;

public class SentencesUnreferencedDB extends DBUtility {

	/**
	 * ID
	 * TEXT
	 * VERIFIED
	 * LINKED_WORD_EXTRACT_UNREF
	 */
	public static final String DATABASE_URL = "jdbc:sqlite:C:/Users/paudyals/Desktop/NLP/nlpdb/npl3.db";//SHADOWED FROM PARENT
	
	public static void main(String args []) {
		//createSentencesUnreferencedTable();
		//selectAllRecords();
		selectSentencesWithLengthGreaterThan(50);
	}
	
	public static void createSentencesUnreferencedTable() {
		String sql = "CREATE TABLE IF NOT EXISTS "+ Tables.SENTENCE_UNREFERENCED +" (\n" + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
				 + " SENTENCE text NOT NULL,\n"
				 + " LINKED_WORD_EXTRACT_UNREF CHAR(1),\n"
				 + " VERIFIED CHAR(1)\n" + ");";

		createNewTable(DATABASE_URL,sql);
	}
	

	public static List<Integer> selectSentencesUnProcessedForLinkedWords(int sentenceLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void deleteRecordsByID(int id) {

		String sql = "DELETE FROM "+ Tables.SENTENCE_UNREFERENCED +" WHERE ID=" + id;

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.executeUpdate();

			System.out.println("Successfully Deleted record: " + id);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static List<Sentence> selectAllRecords() {
		List<Sentence> sentences = new ArrayList<Sentence>();
		
		String sql = "SELECT * FROM " +  Tables.SENTENCE_UNREFERENCED +" ORDER BY SENTENCE ASC";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Sentence sentence = new Sentence();
				sentence.setId(rs.getInt("ID"));
				sentence.setValue(rs.getString("SENTENCE"));
				sentence.setVerified("Y".equalsIgnoreCase(rs.getString("VERIFIED")));
				sentence.setLinkedWordExtracted("Y".equalsIgnoreCase(rs.getString("LINKED_WORD_EXTRACT_UNREF")));
				//System.out.println(rs.getInt("ID") + "\t" + rs.getString("SENTENCE") + "\t" + rs.getString("VERIFIED") + "\t" + rs.getString("LINKED_WORD_EXTRACT_UNREF"));
				
				sentences.add(sentence);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return sentences;
	}
	
	public static List<Sentence> selectRecordsWithLimit(int limit) {
		List<Sentence> sentences = new ArrayList<Sentence>();
		
		String sql = "SELECT * FROM " +  Tables.SENTENCE_UNREFERENCED +" ORDER BY SENTENCE ASC "+ " LIMIT " + limit;
		
		System.out.println("sql");

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Sentence sentence = new Sentence();
				sentence.setId(rs.getInt("ID"));
				sentence.setValue(rs.getString("SENTENCE"));
				sentence.setVerified("Y".equalsIgnoreCase(rs.getString("VERIFIED")));
				sentence.setLinkedWordExtracted("Y".equalsIgnoreCase(rs.getString("LINKED_WORD_EXTRACT_UNREF")));
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("SENTENCE") + "\t" + rs.getString("VERIFIED") + "\t" + rs.getString("LINKED_WORD_EXTRACT_UNREF"));
				
				sentences.add(sentence);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Fetched: " + sentences.size() + " records");
		
		return sentences;
	}
	
	public static void selectRecordCountByLength() {
		String sql = "SELECT Length(SENTENCE), count (*) FROM " +  Tables.SENTENCE_UNREFERENCED +" GROUP BY LENGTH(SENTENCE) ORDER BY 1 DESC";

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
	
	
	
	public static boolean alreadyExists(String sentence) {
		boolean alreadyExists = false;
		String sql = "SELECT * FROM " +  Tables.SENTENCE_UNREFERENCED +" WHERE SENTENCE=\"" + sentence + "\"";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				//System.out.println("Sentence Already Exists in "+ Tables.WORDS_UNREFERENCED);
				alreadyExists = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return alreadyExists;
	}
	
	public static int insertSentence(String sentence) {
		int rowID = -1;
		if (alreadyExists(sentence)) {
			return rowID;
		}

		String sql = "INSERT INTO "+ Tables.SENTENCE_UNREFERENCED + " (SENTENCE) VALUES(?)";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, sentence);
			pstmt.executeUpdate();

			// Get Article ID back
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select seq from sqlite_sequence where name=\"" + Tables.SENTENCE_UNREFERENCED + "\"");

			//System.out.println("Inserted Record ID: " + (rowID = rs.getInt(1)));

		} catch (Exception e) {
			//GRACEFUL
			e.printStackTrace();
		}

		return rowID;
	}
	
	
	public static void updateSentence(int id, String sentenceNewValue) {

		String sql = "UPDATE " + Tables.SENTENCE_UNREFERENCED + " SET SENTENCE=? WHERE ID=?";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, sentenceNewValue);
			pstmt.setInt(2, id);
			int result = pstmt.executeUpdate();

			if(result > 0) {
				//System.out.println("Successfully updated sentence");
			} else {
				//System.out.println("Could not update the sentence. Make sure the ID exists or there are no other issues");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void selectSentencesWithLengthGreaterThan(int length) {
		String sql = "SELECT * FROM " +  Tables.SENTENCE_UNREFERENCED +" where LENGTH(SENTENCE) > " + length + " ORDER BY 1 DESC";

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

	/**
	 * This method updates a list of Sentences to the database, if the sentence value was modified.
	 * 
	 * Shorter sentences that carry less to no value are deleted.
	 * @param sentences
	 */
	public static void updateSentences(List<Sentence> sentences) {
		if(sentences == null || sentences.isEmpty()) {
			return;
		}
		
		for(Sentence sentence:sentences) {
			
			/*if(sentence == null || sentence.getValue() == null) {
				continue;
			}*/
			
			if(sentence.getValue().trim().length() < 3) { //not a possible sentence. Ma chhu is 4 character.
				deleteRecordsByID(sentence.getId());
				System.out.println("Deleted Record: " + sentence.getId());
			} else if (sentence.isModified()){
				updateSentence(sentence.getId(), sentence.getValue());
				System.out.println("Updated Record " + sentence.getId());
			} else {
				System.out.println("Not Modified Record " + sentence.getId());
			}
		}
		
	}
	
	
	
	/*
	 * 


	
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

	

	

	
	public static void cleanWords() {
		List<Word> words = selectRecordsBetweenIds(0, 1000000);
		//List<Word> words = selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word like '%à%'");
		for(Word word:words) {
			String current = word.getWord();
			//System.out.print(word + "\t");
			word.setWord(NPTokenizer.cleanWordToken(word.getWord()));
			
			String cleaned = word.getWord();
			
			if(!current.equalsIgnoreCase(cleaned)) {
				word.setModified(true);
			}
			
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
		
	}
	 */
	

}
