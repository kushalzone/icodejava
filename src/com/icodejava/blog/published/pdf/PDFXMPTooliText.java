package com.icodejava.blog.published.pdf;
 
/**
 * @author Kushal Paudyal
 * www.icodejava.com
 *
 *         Created on 2015-06-15
 *         Last Modified on 2015-06-15
 *
 *         This tool demonstrates how we can embeded Extensible Metadata Platform (XMP)
 *         information to a PDF using iText Library and then reads the XMP metadata
 *         from the file thus generated. This class also shows how to replace the existing
 *         metadata with a new one.
 */
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDFXMPTooliText {
 
    private PDFXMPTooliText() {
 
    }
     
    public static void main(String args []) throws Exception {
         
        /**
         * This is the file where the XMP embedded PDF will be created.
         * You don't need to have this file in the filesystem. This program
         * will create this file for you.
         */
         
        String fileName = "C:\\temp\\Sample.pdf";
         
        /**Create PDF with sample embedded metadata.**/
        createPdfWithMetadata(fileName);
         
        /**
         * Read the file thus created in step above and extract XMP
         * and store the XMP into a separate file
         */
        readXmpMetadata(fileName, "C:\\temp\\metadata.xmp");
         
    }
     
    /**
     * Creates a simple PDF with Hello World text and embedded XMP metadata and stores that to a file system.
     */
    public static void createPdfWithMetadata(String filename) throws DocumentException, IOException {
 
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        /**Embed the XMP meta data to the PDF**/
        writer.setXmpMetadata(getXMPMetadata().getBytes());
                 
        document.open();
        document.add(new Paragraph("Hello World!"));
        document.close();
         
        System.out.println("Successfully created a PDF with following XMP Embedded:\n" + getXMPMetadata() );
    }
     
 
    /**
     * Extracts PDF Metadata and returns the meta data as byte array.
     */
    public static byte[] readXmpMetadata(byte[] pdfBytes) throws IOException {
 
        PdfReader reader = new PdfReader(pdfBytes);
        byte[] b = reader.getMetadata();
        System.out.println(new String(b));
 
        return b;
    }
 
    /**
     * Reads a metadata from a file specified in the src parameter and stores the extracted
     * metadata to a file specified in the dest parameter.
     */
    public static void readXmpMetadata(String src, String dest) throws IOException {
 
        PdfReader reader = new PdfReader(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] b = reader.getMetadata();
        fos.write(b, 0, b.length);
        fos.flush();
        fos.close();
        reader.close();
         
        System.out.println("\n\nSuccessfully read metadata from PDF and found following:\n " + new String(b));
    }
     
     
     
    /**
     * returns a hardcoded metadata XMP file. Name and value pairs are
     * added at the Document Metadata section.
     */
    private static String getXMPMetadata() {
       return    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                  "<?xpacket begin=\"?\" id='W5M0MpCehiHzreSzNTczkc9d'?>"
                  + "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\">"
                  + "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">"
                  + "<rdf:Description rdf:about=\"\" "
                  + "xmlns:dc=\"http://purl.org/dc/elements/1.1/\">"
                    + "<dc:format>application/pdf</dc:format>"
                      + "<DOCUMENT_METADATA>"
                      + "<rdf:Bag>"
                          + "<rdf:li>Name=Brad Pitt</rdf:li>"
                          + "<rdf:li>Movie=FightClub</rdf:li>"
                          + "<rdf:li>Age=47</rdf:li>"
                          + "</rdf:Bag>"
                      + "</DOCUMENT_METADATA>"
                      + "</rdf:Description>"
                      + "</rdf:RDF>"
                      + "</x:xmpmeta>"
                      + "<?xpacket end=\"w\"?>";
    }
}