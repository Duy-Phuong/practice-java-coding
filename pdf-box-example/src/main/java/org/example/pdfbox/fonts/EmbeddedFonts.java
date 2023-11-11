package org.example.pdfbox.fonts;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

public final class EmbeddedFonts {

    private EmbeddedFonts() {
    }

    public static void main(String[] args) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            String dir = "src/main/resources/fonts/";
            // https://svn.apache.org/viewvc/pdfbox/trunk/pdfbox/src/main/resources/org/apache/pdfbox/resources/ttf/?sortby=date
            // Sign with all unicode characters
            PDType0Font font = PDType0Font.load(document, new File(dir + "LiberationSans-Regular.ttf"));

            // Cannot sign with some groups characters: Miscellaneous, Non-European & historic Latin, Slovenian & Croatian, Livonian, Sinology at https://en.wikipedia.org/wiki/List_of_Unicode_characters
            // PDType0Font font = PDType0Font.load(document, new File(dir + "OpenSans-Medium.ttf"));

            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                stream.beginText();
                stream.setFont(font, 12);
                stream.setLeading(12 * 1.2f);

                stream.newLineAtOffset(50, 600);
                stream.showText("PDFBox's Unicode with Embedded TrueType Font # ¢ © Ä ç ą Ĥ  ū ș ȯ");
                stream.newLine();

                stream.showText("Supports full Unicode text ☺");
                stream.newLine();

                stream.showText("Supports full Unicode text: No glyph for U+01C2 (ǂ)");
                stream.newLine();

                stream.showText("Supports full Unicode text ƃ, ƛ, ȫ");
                stream.newLine();

                stream.showText("Supports full Unicode text ǣ ȍ ȯ ȶ");
                stream.newLine();

                stream.showText("English русский язык Tiếng Việt");
                stream.newLine();

                // ligature
                stream.showText("Ligatures: \uFB01lm \uFB02ood");
                stream.newLine();

                stream.showText("This is a special character: ţ");
                stream.newLine();

                stream.endText();
            }

            // Delete before save to new file
            document.save("src/main/resources/data/example.pdf");
        }
    }
}
