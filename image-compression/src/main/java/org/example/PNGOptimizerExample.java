package org.example;

import com.googlecode.pngtastic.core.PngImage;
import com.googlecode.pngtastic.core.PngOptimizer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PNGOptimizerExample {
    public static void main(String[] args) throws IOException {
        // NOTE: Only works with PNG
        File input = new File("src/main/resources/SamplePNGImage_1mbmb.png");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        PngImage inputImage = new PngImage(Files.newInputStream(input.toPath()));

        PngOptimizer optimizer = new PngOptimizer();
        PngImage optimized = optimizer.optimize(inputImage);

        OutputStream output = Files.newOutputStream(Paths.get("src/main/resources/png_optimizer_output.png"));
        optimized.writeDataOutputStream(output);
        System.out.println(dateFormat.format(new Date()));
    }
}
