package com.icodejava.blog.published.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTextFromFileToMySql {

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/sanjaal_com";
		String username = "root";
		String password = "";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void main(String[] args) throws Exception {
		String textValue = "";
		int textLength = 0;
		String category = "funny";
		String lineRead = null;
		PreparedStatement pstmt = null;

		String fileNameToInsertTextFrom = "C:/temp/smsCollection002.txt";
		FileReader fileReader = new FileReader(new File(fileNameToInsertTextFrom));

		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			BufferedReader reader = new BufferedReader(fileReader);
			pstmt = conn
					.prepareStatement("insert into smsTable(sms_text, sms_length, sms_category)" + "values (?, ?, ?)");

			while ((lineRead = reader.readLine()) != null) {
				try {
					textValue = lineRead.trim();
					textLength = textValue.length();

					if (textLength > 0) {

						pstmt.setString(1, textValue);
						pstmt.setInt(2, textLength);
						pstmt.setString(3, category);
						pstmt.executeUpdate();
						conn.commit();
					}

				} catch (NullPointerException npe) {
					// do nothing proceed to another line
				}
				lineRead = null;
			}
			
			reader.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
	}
}