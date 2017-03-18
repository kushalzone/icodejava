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
		
		//selectWordWithLengthGreaterThan(24);
		//selectWordWithLengthEqualTo(3);
		
		//cleanWords();
		//cleanStrangeWords();
		
		//selectWithQuery("SELECT * FROM " + Tables.WORDS_UNREFERENCED +  " where word like '%à%'");
		
		selectWithQuery("SELECT COUNT (DISTINCT WORD) FROM " + Tables.WORDS_UNREFERENCED);
		
	}
	
	public static void createWordsUnreferencedTable() {
		String sql = "CREATE TABLE IF NOT EXISTS "+ Tables.WORDS_UNREFERENCED +" (\n" + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
				 + " WORD text NOT NULL,\n" 
				 + " WORD_ROMANIZED text,\n" 
				 + " VERIFIED CHAR(1)\n" + ");";

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

			//System.out.println("Inserted Record ID: " + (rowID = rs.getInt(1)));

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
				System.out.println("Successfully updated word");
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
		
		for(Word word:words) {
			
			if(word.getWord().trim().length() == 0) {
				deleteRecordsByID(word.getId());
			} else if (word.isModified()){
				updateWord(word.getId(), word.getWord());
			} else {
				System.out.println("Not Modified " + word );
			}
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
}
