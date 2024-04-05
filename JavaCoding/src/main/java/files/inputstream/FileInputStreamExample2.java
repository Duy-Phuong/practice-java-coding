package files.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInputStreamExample2 {
    public static void main(String args[])
            throws IOException
    {
        int n;
        try (InputStream in = Files.newInputStream(Paths.get("input.txt"))) {
            while ((n = in.read()) != -1) {
                System.out.print((char) n);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
