package com.icodejava.research.nlp.database;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
*
* @author sqlitetutorial.net
*/
public class DatabaseUtility {

    public static final String DATABASE_URL = "jdbc:sqlite:C:/Users/paudyals/Desktop/NLP/nlpdb/npl2.db";

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     * @throws ClassNotFoundException 
     */
    public static void createNewDatabase() throws ClassNotFoundException {
    	
    	Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createNewTable(String sql) {


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement()) {
            // create a new table
            System.out.println("SQL to create new table\n" + sql );
            boolean result = stmt.execute(sql);
            if(result) {
            	System.out.println("The table has been successfully created");
            } else {
            	System.out.println("Table Already Exists. Table Creation Skipped.");
            }

            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


	public static void deleteAllRecords(String tableName) {
		
		 String sql = "DELETE FROM " + tableName;
		 
	     try (Connection conn = DriverManager. getConnection(DATABASE_URL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	
	         pstmt.executeUpdate();
	
	     } catch (SQLException e) {
	         System.out.println(e.getMessage());
	     }
	
	}


}