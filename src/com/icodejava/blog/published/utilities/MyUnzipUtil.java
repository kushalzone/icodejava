package com.icodejava.blog.published.utilities;
/**
 * @author Kushal Paudyal
 * This class show you how you can extract a zip file using Java
 */
 
import java.io.*;
import java.util.*;
import java.util.zip.*;
 
public class MyUnzipUtil {
 
    public static final void main(String[] args) {
        /**
         * Initialize the default parameters
         */
        String zipFileName = "C:/myFolder/myZip.zip";
        String directoryToExtractTo = "C:/myFolder/unzipped/";
 
        /**
         * Check if the arguments are passed from console.
         * If so, we will discard the defaUlt parameters.
         */
 
        if (args.length != 2) {
            System.err.println("Usage: MyUnzipUtil zipfile unzipLocation");
            System.err.println("...Using The Hardcoded Zip Location");
        } else {
            zipFileName = args[0];
            directoryToExtractTo = args[1];
        }
 
        /**
         * Call the unzip method
         */
        unzipMyZip(zipFileName, directoryToExtractTo);
 
    }
 
    /**
     * This method
     * --Reads an input stream
     * --Writes the value to the output stream
     * --Uses 1KB buffer.
     */
    public static final void writeFile(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len;
 
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
 
        in.close();
        out.close();
    }
 
    public static void unzipMyZip(String zipFileName,
            String directoryToExtractTo) {
        Enumeration entriesEnum;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(zipFileName);
            entriesEnum = zipFile.entries();
 
            File directory= new File(directoryToExtractTo);
 
            /**
             * Check if the directory to extract to exists
             */
            if(!directory.exists())
            {
                /**
                 * If not, create a new one.
                 */
                new File(directoryToExtractTo).mkdir();
                System.err.println("...Directory Created -"+directoryToExtractTo);
            }
            while (entriesEnum.hasMoreElements()) {
                try {
                    ZipEntry entry = (ZipEntry) entriesEnum.nextElement();
 
                    if (entry.isDirectory()) {
                        /**
                         * Currently not unzipping the directory structure.
                         * All the files will be unzipped in a Directory
                         *
                         **/
                    } else {
 
                        System.err.println("Extracting file: "
                                + entry.getName());
                        /**
                         * The following logic will just extract the file name
                         * and discard the directory
                         */
                        int index = 0;
                        String name = entry.getName();
                        index = entry.getName().lastIndexOf("/");
                        if (index > 0 && index != name.length())
                            name = entry.getName().substring(index + 1);
 
                        System.out.println(name);
 
                        writeFile(zipFile.getInputStream(entry),
                                new BufferedOutputStream(new FileOutputStream(
                                        directoryToExtractTo + name)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
 
            zipFile.close();
        } catch (IOException ioe) {
            System.err.println("Some Exception Occurred:");
            ioe.printStackTrace();
            return;
        }
    }
 
}