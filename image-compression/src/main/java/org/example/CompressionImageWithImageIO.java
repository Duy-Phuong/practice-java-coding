package org.example;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class CompressionImageWithImageIO {
    public static void main(String[] args) throws IOException {
//        File inputFile = new File("src/main/resources/input_image2.jpg");
//        File inputFile = new File("src/main/resources/5mb.jpg");
        File inputFile = new File("src/main/resources/input_image.jpg");
//        Note: not support svg
//        File inputFile = new File("src/main/resources/input.svg");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        BufferedImage inputImage = ImageIO.read(inputFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        File outputFile = new File("src/main/resources/output.jpg");
        ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile);
        writer.setOutput(outputStream);

        ImageWriteParam params = writer.getDefaultWriteParam();
        params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        params.setCompressionQuality(0.5f);

        writer.write(null, new IIOImage(inputImage, null, null), params);

        System.out.println(dateFormat.format(new Date()));
        outputStream.close();
        writer.dispose();
    }
}
