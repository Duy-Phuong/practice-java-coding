package org.example;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThumbnailsExample {
    public static void main(String[] args) throws IOException {
        File input = new File("src/main/resources/input_image2.jpg");
//        File input = new File("src/main/resources/input.svg");
//        File output = new File("src/main/resources/output.svg");
        File output = new File("src/main/resources/thumbnail_output2.svg");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        Thumbnails.of(input)
//                .size(100, 100)
                .scale(1)
                .outputQuality(0.5)
                .toFile(output);
        System.out.println(dateFormat.format(new Date()));
    }
}
