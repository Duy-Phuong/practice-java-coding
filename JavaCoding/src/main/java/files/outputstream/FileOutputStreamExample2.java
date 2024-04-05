package files.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputStreamExample2 {
    public static void main(String[] args)
    {
        // http://www.java2s.com/Tutorials/Java/java.nio.file/Files/0800__Files.newOutputStream_Path_path_OpenOption_options_.htm
        Path rn_demo = Paths.get("demo.txt");
        String demo = "tutorial";

        //using NIO.2 unbuffered stream
        byte data[] = demo.getBytes();
        try (OutputStream outputStream = Files.newOutputStream(rn_demo)) {
            outputStream.write(data);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
