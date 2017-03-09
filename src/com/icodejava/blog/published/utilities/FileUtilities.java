package com.icodejava.blog.published.utilities;
/**
 * @author Kushal Paudyal
 * Created on 08/15/2015
 *
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
 
public class FileUtilities {
 
    private FileUtilities() {
 
    }
 
    public static void main(String args[]) throws FileNotFoundException, IOException {
 
        /**
         * Non UTF8 format file read/write example
         * Reads content from a file and writes to another file.
         */
         
        byte[] fileContent = loadFile("/Users/kushal/Desktop/someFile.txt");
        writeFile("/Users/kushal/Desktop/someOutputFile", fileContent);
         
        /**
         * UTF8 format file read/write example
         * Reads content from a file and writes to another file.
         */
        String content = readUTF8File("/Users/paudyak/Desktop/np_name.txt");
        writeUTF8File("/Users/paudyak/Desktop/np_name_out.txt", content);
 
    }
 
    /**
     * This method shows how to read a file as bytes []
     */
    public static byte[] loadFile(String fileName) throws FileNotFoundException, IOException {
 
        File file = new File(fileName);
        int length = (int) file.length();
 
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
 
        return bytes;
 
    }
 
    /**
     * This method shows how to write byte [] into a file.
     */
    public static void writeFile(String fileName, byte[] content) throws IOException {
 
        File file = new File(fileName);
 
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
 
        writer.write(content);
 
        writer.flush();
 
        writer.close();
 
    }
 
    /**
     * This method shows how you can read a UTF8 file in Java.
     */
    public static String readUTF8File(String fileName) {
 
        String content = "";
 
        try {
 
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
 
            String line;
            while ((line = in.readLine()) != null) {
 
                System.out.println(line);
                content += line;
                 
 
            }
 
            in.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
 
    /**
     * This method show how to write UTF8 content to the file system.
     */
    public static void writeUTF8File(String fileName, String content) {
 
        try {
 
            File fileDir = new File(fileName);
 
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir),"UTF8"));
 
            out.append(content);
            out.flush();
            out.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
}