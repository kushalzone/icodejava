package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WordsUnreferencedDB extends DBUtility {
	
	
	public static final String DATABASE_URL = "jdbc:sqlite:C:/Users/paudyals/Desktop/NLP/nlpdb/npl3.db";//shadowed from parent
	
	
	public static void main (String args []) throws ClassNotFoundException {
		//createNewDatabase(DATABASE_URL);
		//createWordsUnreferenedTable();
		//insertWord("test");
		//updateWord(4, "Tested");
		selectAllRecords();
		//deleteAllRecords(DATABASE_URL, Tables.WORDS_UNREFERENCED);
		
	}
	
	public static void createWordsUnreferenedTable() {
		String sql = "CREATE TABLE IF NOT EXISTS "+ Tables.WORDS_UNREFERENCED +" (\n" + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
				 + " WORD text NOT NULL,\n" 
				 + " WORD_ROMANIZED text,\n" 
				 + " VERIFIED CHAR(1)\n" + ");";

		createNewTable(DATABASE_URL,sql);
	}
	
	
	
	public static void selectAllRecords() {
		String sql = "SELECT * FROM " +  Tables.WORDS_UNREFERENCED ;

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
	
	public static void deleteRecordsByID(int id) {

		String sql = "DELETE FROM "+ Tables.WORDS_UNREFERENCED +" WHERE ID=" + id;

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.executeUpdate();

			System.out.println("Successfully Deleted record");

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


}
