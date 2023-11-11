package org.example.pdfbox.images;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.util.Matrix;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Math.floor;

/**
 * An implementation of pdfbox to display signatures image
 * Add image and draw rectangle
 * Add text and handle for long text
 * Note: The height of signature image is different from the other signatures
 */
public class InsertingImageWithoutSignature {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File file = new File("src/main/resources/data/red.pdf");
        PDDocument doc = PDDocument.load(file);

        //Retrieving the page
//        PDPage page = doc.getPage(0);

        File image = new File("src/main/resources/data/red.png");

        //Creating PDImageXObject object
//        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(image, doc);


        Rectangle2D humanRect = new Rectangle2D.Float(100, 500, 250, 100);
        PDRectangle rect = createSignatureRectangle(doc, humanRect);

        try (SignatureOptions options = new SignatureOptions()) {

            createVisualSignatureTemplate(doc, 0, image.getPath(), rect);
            //Closing the document
            doc.close();
        }

    }

    private static PDRectangle createSignatureRectangle(PDDocument doc, Rectangle2D humanRect) {
        float x = (float) humanRect.getX();
        float y = (float) humanRect.getY();
        float width = (float) humanRect.getWidth();
        float height = (float) humanRect.getHeight();
        PDPage page = doc.getPage(0);
        PDRectangle pageRect = page.getCropBox();
        PDRectangle rect = new PDRectangle();
        switch (page.getRotation()) {
            case 90:
                rect.setLowerLeftY(x);
                rect.setUpperRightY(x + width);
                rect.setLowerLeftX(y);
                rect.setUpperRightX(y + height);
                break;
            case 180:
                rect.setUpperRightX(pageRect.getWidth() - x);
                rect.setLowerLeftX(pageRect.getWidth() - x - width);
                rect.setLowerLeftY(y);
                rect.setUpperRightY(y + height);
                break;
            case 270:
                rect.setLowerLeftY(pageRect.getHeight() - x - width);
                rect.setUpperRightY(pageRect.getHeight() - x);
                rect.setLowerLeftX(pageRect.getWidth() - y - height);
                rect.setUpperRightX(pageRect.getWidth() - y);
                break;
            default:
                // apply for case 0
                rect.setLowerLeftX(x);
                rect.setUpperRightX(x + width);
                rect.setLowerLeftY(pageRect.getHeight() - y - height);
                rect.setUpperRightY(pageRect.getHeight() - y);
                break;
        }

        return rect;
    }

    private static InputStream createVisualSignatureTemplate(
            PDDocument srcDoc, int pageNum, String iconPath, PDRectangle rect) throws IOException {
        try (PDDocument doc = srcDoc) {
            PDPage page = doc.getPage(0);
//            PDAcroForm acroForm = new PDAcroForm(doc);
//            doc.getDocumentCatalog().setAcroForm(acroForm);
//            PDSignatureField signatureField = new PDSignatureField(acroForm);
//            PDAnnotationWidget widget = signatureField.getWidgets().get(0);
//            List<PDField> acroFormFields = acroForm.getFields();
//            acroForm.setSignaturesExist(true);
//            acroForm.setAppendOnly(true);
//            acroForm.getCOSObject().setDirect(true);
//            acroFormFields.add(signatureField);
//            widget.setRectangle(rect);
//
//            PDStream stream = new PDStream(doc);
//            PDFormXObject form = new PDFormXObject(stream);
//            PDResources res = new PDResources();
//            form.setResources(res);
//            form.setFormType(1);
            PDRectangle bbox = new PDRectangle(rect.getWidth(), rect.getHeight());
            float height = bbox.getHeight();

            try (PDPageContentStream cs = new PDPageContentStream(doc,
                    page, PDPageContentStream.AppendMode.APPEND, true);) {
                if (iconPath != null) {
                    File image = new File(iconPath);
                    if (image.exists()) {
                        // show background image
                        // save and restore graphics if the image is too large and needs to be scaled
                        cs.saveGraphicsState();
                        drawDefaultSignatureImage(cs, image, doc);
                        cs.restoreGraphicsState();
                    }
                }

                addTextForDefaultSignature(doc, cs, height);

                int x = 100; // X-coordinate of the rectangle
                int y = 500; // Y-coordinate of the rectangle
                int width = 250 - 20; // Width of the rectangle with EID
                int height1 = 100; // Height of the rectangle
                drawRectangle(cs, x, y, width, height1);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save("src/main/resources/data/visual-image.pdf");

            return new ByteArrayInputStream(baos.toByteArray());
        }
    }

    private static void drawDefaultSignatureImage(PDPageContentStream cs, File image, PDDocument doc)
            throws IOException {
        cs.transform(Matrix.getScaleInstance(1, 1)); // image scale to fit with rect for Nami
//        cs.transform(Matrix.getScaleInstance(0.25f, 0.25f));
        PDImageXObject img = PDImageXObject.createFromFileByExtension(image, doc);
//        cs.drawImage(img, 0, 0);
        cs.drawImage(img, 100, 500, 250, 100);  // image scale to fit with rect for Nami
    }

    private static void addTextForDefaultSignature(PDDocument doc, PDPageContentStream cs, float height)
            throws IOException {
        PDFont font = PDType1Font.TIMES_ROMAN;
        // show text
        float fontSize = 8;
        float leading = fontSize * 1.5f;
        cs.beginText();
        cs.setFont(font, fontSize);
        cs.moveTextPositionByAmount(100, 500);
        cs.setNonStrokingColor(Color.black);
        cs.newLineAtOffset(fontSize, height - leading);
        cs.setLeading(leading);
        String reason = "Reason";
        String text = String.format("%s %s", reason, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ");
        float textWidth = font.getStringWidth(text) * fontSize / 1000f;
        System.out.println(font.getStringWidth(text));
        System.out.println(textWidth);
        System.out.println(textWidth/text.length());
        float widthOfCharacter = textWidth/text.length();
        int allowedNumberOfCharacter = (int) floor((250 - 28 - leading) / widthOfCharacter);
        System.out.println(text.substring(0, allowedNumberOfCharacter));
        text = text.substring(0, allowedNumberOfCharacter);
        cs.setFont(font, fontSize);
        cs.showText(text);
        cs.endText();
    }

    private static void drawRectangle(PDPageContentStream cs, int x, int y, int width, int height) {
        // Draw the border of the rectangle
        try {
            cs.addRect(x, y, width, height);
            cs.setLineWidth(2); // Set the border width
            cs.stroke();

            //Drawing a rectangle
            cs.fill();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
