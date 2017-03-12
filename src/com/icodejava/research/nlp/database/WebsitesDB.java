package com.icodejava.research.nlp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class WebsitesDB extends DatabaseUtility {
	
	public static void main(String args []) {
		createWebsitesTable();
		deleteAllRecords(Tables.WEBSITES);
		//deleteRecordsByID(6);
		//deleteRecordsByURL("http://www.sanjaal.com/");
		//deleteRecordsByDomain("http://www.sanjaal.com/test/test.php");//NOT TESTED
		//insertWebsite("http://www.sanjaal.com/", "http://www.sanjaal.com/test/test.php");
		selectAllWebsites();
		
		
		
	}
	
    public static void createWebsitesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS WEBSITES (\n"
                + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
        		+ " DOMAIN text NOT NULL,\n"
                + " URL text NOT NULL,\n"
        		+ " CRAWLED_DATE DATE NOT NULL \n"
                + ");";

        createNewTable(sql);
    }
    
    public static void insertWebsite(String domain, String url) {
    	
    	if(alreadyExists(domain, url)) {
    		return;
    	}

        String sql = "INSERT INTO WEBSITES (DOMAIN, URL, CRAWLED_DATE) VALUES(?,?,?)" ;
        

        try (
            Connection conn = DriverManager. getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, domain);
            pstmt.setString(2, url);
            pstmt.setDate(3,  new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pstmt.executeUpdate();

            //Get Article ID back
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "select seq from sqlite_sequence where name=\"WEBSITES\"");

            System. out.println(rs.getInt(1));

        } catch (Exception e) {
            System. out.println(e.getMessage());
        }
    }

    public static void deleteRecordsByID(int id) {
    	
   	 String sql = "DELETE FROM WEBSITES WHERE ID=" + id;
   	 
        try (Connection conn = DriverManager. getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.executeUpdate();
            
            System.out.println("Successfully Deleted record");
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

   }
    
    public static void deleteRecordsByDomain(String domain) {
    	
      	 String sql = "DELETE FROM WEBSITES WHERE DOMAIN=\"" + domain+"\"";
      	 
           try (Connection conn = DriverManager. getConnection(DATABASE_URL);
                   PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
               pstmt.executeUpdate();
               
               System.out.println("Successfully Deleted record");
    
           } catch (SQLException e) {
        	   e.printStackTrace();
               System.out.println(e.getMessage());
           }

      }
    
    public static void deleteRecordsByURL(String url) {
    	
     	 String sql = "DELETE FROM WEBSITES WHERE DOMAIN=\"" + url+"\"";
     	 
          try (Connection conn = DriverManager. getConnection(DATABASE_URL);
                  PreparedStatement pstmt = conn.prepareStatement(sql)) {
   
              pstmt.executeUpdate();
              
              System.out.println("Successfully Deleted record");
   
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }

     }

    public static void selectAllWebsites(){
        String sql = "SELECT * FROM WEBSITES";

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System. out.println(rs.getInt("ID" ) +  "\t" + rs.getString("DOMAIN") + "\t" + rs.getString("URL") + "\t"+  rs.getString(3));
            }
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
    }
    
    public static boolean alreadyExists(String domain, String url){
    	boolean alreadyExists = false;
        String sql = "SELECT * FROM WEBSITES WHERE DOMAIN=\""+domain+"\" AND URL=\""+url+"\"";

        try (Connection conn = DriverManager.getConnection( DATABASE_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	
        	if(rs.next()) {
        		System.out.println("Record Already Exists");
        		alreadyExists = true;
        	}

            
        } catch (SQLException e) {
            System. out.println(e.getMessage());
        }
        
        return alreadyExists;
    }

}
