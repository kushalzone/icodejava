package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticlesDB extends DatabaseUtility {
	
    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {
        createNewDatabase();

        ArticlesDB.createArticlesTable();
        ArticlesDB.insertArticles("Another Test 2");
        ArticlesDB.insertArticles("Another Test 3");
        ArticlesDB.insertArticles("Another Test 4");

    }
	
	

    public static void createArticlesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ARTICLES (\n"
                + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                + " CONTENT text NOT NULL\n"
                + ");";

        createNewTable(sql);
    }

    public static void insertArticles(String text) {

        String sql = "INSERT INTO ARTICLES(CONTENT) VALUES(?)" ;

        try (
            Connection conn = DriverManager. getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, text);
            int result = pstmt.executeUpdate();

            //Get Article ID back
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "select seq from sqlite_sequence where name=\"ARTICLES\"");

            System. out.println(rs.getInt(1));

        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }

    public static void deleteArticles(String sql) {

    }

    public static void selectAllArticles(){
        String sql = "SELECT * FROM ARTICLES";

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System. out.println(rs.getInt("ID" ) +  "\t" + rs.getString("CONTENT" ));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }

}