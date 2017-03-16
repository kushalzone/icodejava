package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDB extends DBUtility {
	/*
	ID pk
	SITE_ID fk WEBSITES.ID
	CONTENT_HTML
	CONTENT_TEXT
	TITLE
	WORD_EXTRACT_UNREFRENCED
	*/
    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {
    	//createArticlesTable();
    	//selectAllArticles();
    	//selectArticleByID(4);
    	//selectDistinctTitles();
    	selectArticlesCountProcessedForUnreferenceWord();
    }
	
	

    public static void createArticlesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ARTICLES (\n"
                + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                + " SITE_ID integer,\n"
                + " CONTENT_HTML text,\n"
                + " CONTENT_TEXT text NOT NULL,\n"
                + " TITLE text WORD_EXTRACT_UNREFERENCED CHAR(1),\n"
                + " FOREIGN KEY (SITE_ID) REFERENCES WEBSITES(ID)"
                + ");";
        
        System.out.println(sql);

        createNewTable(sql);
    }

    public static int insertArticles(int siteID, String html, String text, String title) {

        String sql = "INSERT INTO ARTICLES(SITE_ID, CONTENT_HTML, CONTENT_TEXT, TITLE) VALUES(?, ?, ?,?)" ;

        try (
            Connection conn = DriverManager. getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, siteID);
            pstmt.setString(2, html);
            pstmt.setString(3, text);
            pstmt.setString(4, title);
            
            pstmt.executeUpdate();

            //Get Article ID back
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "select seq from sqlite_sequence where name=\"ARTICLES\"");
            
            
            int articleID =  rs.getInt(1);

            System. out.println("Successfully Inserted Article " + articleID);
            
            return articleID;

        } catch (SQLException e) {
            System. out.println(e.getMessage());
            return -999;
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
                System. out.println(rs.getInt("ID" ) +  "\t" + rs.getString("CONTENT_HTML" ) +  "\t" + rs.getString("CONTENT_TEXT" ));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }
    
    public static void selectArticlesCount(){
        String sql = "SELECT COUNT (*) FROM ARTICLES";

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (rs.next()) {
                System. out.println("Total Articles in the Database: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }
    
    public static void selectArticleByID(int articleID){
        String sql = "SELECT * FROM ARTICLES WHERE ID=" +  articleID;

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System. out.println(rs.getInt("ID" ) +  "\t" + rs.getString("CONTENT_HTML" ) +  "\t" + rs.getString("CONTENT_TEXT" ));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }



	public static String selectArticleTextByID(int articleID) {
		String text="";
		String sql = "SELECT CONTENT_TEXT FROM ARTICLES WHERE ID=" +  articleID;

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (rs.next()) {
                //System. out.println(rs.getInt("ID" ) +  "\t" + rs.getString("CONTENT_HTML" ) +  "\t" + rs.getString("CONTENT_TEXT" ));
            	text=rs.getString("CONTENT_TEXT" );
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
        return text;
	}
	
	
	public static void selectDistinctTitles() {
		String sql = "SELECT DISTINCT TITLE FROM ARTICLES LIMIT 1000";

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
            	String title = rs.getString("TITLE" );
            	System.out.println(title);
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
	}
	
	public static List<Integer> selectArticlesUnProcessed(int limit) {
		
		String sql = "SELECT ID FROM ARTICLES WHERE WORD_EXTRACT_UNREFERENCED IS NULL LIMIT " + limit;
		
		List<Integer> ids = new ArrayList<Integer>();

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
            	ids.add(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
        
        System.out.println("Returning " +  ids.size() + " records not previously processed for extracing UNREFERENCED WORDS");
        return ids;
		
	}



	public static void updateArticleMarkAsProcessedForUnreferenceWord(Integer id) {

		String sql = "UPDATE " + Tables.ARTICLES + " SET WORD_EXTRACT_UNREFERENCED=\"Y\" WHERE ID="+id;

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int result = pstmt.executeUpdate();

			if (result > 0) {
				//System.out.println("Successfully updated Article to mark it as processed for word extract (unrefereneced) + ID " + id);
			} else {
				System.out
						.println("Could not update the Article. Make sure the ID exists or there are no other issues. ID "+ id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public static void selectArticlesCountProcessedForUnreferenceWord() {
		String sql = "SELECT count(*) FROM ARTICLES WHERE WORD_EXTRACT_UNREFERENCED=\"Y\"";

		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				System.out.println("Total Articles Processed for extracing Unreferenced Words: " + rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
    
}