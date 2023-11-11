package org.example.pdfbox.images;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.Matrix;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * An implementation of pdfbox to display signatures image
 * Add image and draw rectangle
 * Add text and handle for long text
 * Create an invalid signature
 */
public class InsertingImageWithSignature {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File file = new File("src/main/resources/data/red.pdf");
        PDDocument doc = PDDocument.load(file);

        //Retrieving the page
        PDPage page = doc.getPage(0);

        File image = new File("src/main/resources/data/red.png");

        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(image, doc);

        //creating the PDPageContentStream object
//        PDPageContentStream contents = new PDPageContentStream(doc, page);

        //Drawing the image in the PDF document
//        contents.drawImage(pdImage, 70, 250);

//        System.out.println("Image inserted");

        //Closing the PDPageContentStream object
//        contents.close();

        //Saving the document
//        doc.save("src/main/resources/data/sample-with-image.pdf");

        Rectangle2D humanRect = new Rectangle2D.Float(100, 500, 250, 100);
        PDRectangle rect = createSignatureRectangle(doc, humanRect);
//        createVisualSignatureTemplate(doc, 0, image.getPath(), rect);

        PDSignature signature = new PDSignature();

        signature.setType(COSName.getPDFName("Sig"));
        signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        signature.setSubFilter(PDSignature.SUBFILTER_ETSI_CADES_DETACHED);

        // The signing date, needed for a valid signature
        final Calendar cal = Calendar.getInstance();
        long signatureTime = 1692617329341L;
        final Date signingDate = new Date(signatureTime);
        cal.setTime(signingDate);
        signature.setSignDate(cal);
        SignatureOptions options = new SignatureOptions();

        // Enough room for signature, timestamp and OCSP for baseline-LT profile.
        options.setPreferredSignatureSize(SignatureOptions.DEFAULT_SIGNATURE_SIZE);
        options.setVisualSignature(createVisualSignatureTemplate(doc, 0, image.getPath(), rect));
        options.setPage(0);

        // The code for visible signature starts here
        int pageNum = 0; // Page numbering starts from zero.
        options.setPage(pageNum);
        doc.addSignature(signature, options);

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/data/visual-signature.pdf");
        doc.save(outputStream);


        outputStream.close();

        //Closing the document
        doc.close();

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
        try (PDDocument doc = new PDDocument()) {
//            PDPage page = doc.getPage(0);
            PDPage page = new PDPage(srcDoc.getPage(pageNum).getMediaBox());
            doc.addPage(page);
            PDAcroForm acroForm = new PDAcroForm(doc);
            doc.getDocumentCatalog().setAcroForm(acroForm);
            PDSignatureField signatureField = new PDSignatureField(acroForm);
            PDAnnotationWidget widget = signatureField.getWidgets().get(0);
            List<PDField> acroFormFields = acroForm.getFields();
            acroForm.setSignaturesExist(true);
            acroForm.setAppendOnly(true);
            acroForm.getCOSObject().setDirect(true);
            acroFormFields.add(signatureField);
            widget.setRectangle(rect);

            PDStream stream = new PDStream(doc);
            PDFormXObject form = new PDFormXObject(stream);
            PDResources res = new PDResources();
            form.setResources(res);
            form.setFormType(1);
            PDRectangle bbox = new PDRectangle(rect.getWidth(), rect.getHeight());
            float height = bbox.getHeight();
            Matrix initialScale = null;
            switch (srcDoc.getPage(pageNum).getRotation()) {
                case 0:
                    break;
                case 90:
                    form.setMatrix(AffineTransform.getQuadrantRotateInstance(1));
                    initialScale =
                            Matrix.getScaleInstance(
                                    bbox.getWidth() / bbox.getHeight(), bbox.getHeight() / bbox.getWidth());
                    height = bbox.getWidth();
                    break;
                case 180:
                    form.setMatrix(AffineTransform.getQuadrantRotateInstance(2));
                    break;
                case 270:
                    form.setMatrix(AffineTransform.getQuadrantRotateInstance(3));
                    initialScale =
                            Matrix.getScaleInstance(
                                    bbox.getWidth() / bbox.getHeight(), bbox.getHeight() / bbox.getWidth());
                    height = bbox.getWidth();
                    break;
                default:
                    break;
            }
            form.setBBox(bbox);

            PDAppearanceDictionary appearance = new PDAppearanceDictionary();
            appearance.getCOSObject().setDirect(true);
            PDAppearanceStream appearanceStream = new PDAppearanceStream(form.getCOSObject());
            appearance.setNormalAppearance(appearanceStream);
            widget.setAppearance(appearance);

            try (PDPageContentStream cs = new PDPageContentStream(doc, appearanceStream);) {
                // for 90° and 270° scale ratio of width / height
                // not really sure about this
                // why does scale have no effect when done in the form matrix???
                if (initialScale != null) {
                    cs.transform(initialScale);
                }

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

                // draw the rectangle for EID
                float x = 0; // X-coordinate of the rectangle
                float y = 0; // Y-coordinate of the rectangle
                float width = 250 - 20; // Width of the rectangle with EID
                float height1 = 100; // Height of the rectangle
                drawRectangle(cs, x, y, width, height1);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save(baos);

            return new ByteArrayInputStream(baos.toByteArray());
        }
    }

    private static void drawDefaultSignatureImage(PDPageContentStream cs, File image, PDDocument doc)
            throws IOException {
        cs.transform(Matrix.getScaleInstance(1, 1)); // scale to fit with the rectangle
        PDImageXObject img = PDImageXObject.createFromFileByExtension(image, doc);
//        cs.drawImage(img, 0, 0);
        cs.drawImage(img, 0.0F, 0.0F, 250, 100);
    }

    private static void addTextForDefaultSignature(PDDocument doc, PDPageContentStream cs, float height)
            throws IOException {
        PDFont font = PDType1Font.TIMES_ROMAN;
        float fontSize = 8;
        float leading = fontSize * 1.5f;
        cs.beginText();
        cs.setFont(font, fontSize);
        cs.setNonStrokingColor(Color.black);
        cs.newLineAtOffset(fontSize, height - leading);
        cs.setLeading(leading);
        String reason = "Reason";
        cs.showText(String.format("%s %s", reason, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the "));
        cs.endText();
    }

    private static void drawRectangle(PDPageContentStream cs, float x, float y, float width, float height) {
        try {
            cs.addRect(x, y, width, height);
            cs.setLineWidth(2); // Set the border width
            cs.stroke();

            cs.fill();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
