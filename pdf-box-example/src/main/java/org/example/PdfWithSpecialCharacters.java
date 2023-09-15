package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class PdfWithSpecialCharacters {
    public static void main(String[] args) throws IOException {
        // Specify the path to the PDF file within the resources folder
        File file = new File("src/main/resources/data/sample.pdf");

        PDDocument document = PDDocument.load(file);
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

//        try (FileInputStream fontStream = new FileInputStream("src/main/resources/fonts/Helvetica-Bold-Font.ttf")) {
//        try (FileInputStream fontStream = new FileInputStream("src/main/resources/fonts/Arialn.ttf")) {
//        try (FileInputStream fontStream = new FileInputStream("src/main/resources/fonts/OpenSans-Medium.ttf")) {
        try (FileInputStream fontStream = new FileInputStream("src/main/resources/fonts/LiberationSans-Regular.ttf")) {
            // Load a font that supports extended Latin characters
            PDType0Font font = PDType0Font.load(document, fontStream);

//            Return error with special unicode characters
//            PDFont font = PDType1Font.TIMES_ITALIC;
//            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 12); // Set the font and font size

            // Add text with special characters
            String text = "This is a special character: ţ";
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText(text);
            contentStream.endText();

            contentStream.close();

            // Delete before save to new file
            document.save("src/main/resources/data/output.pdf");
            document.close();
        }
    }
}
