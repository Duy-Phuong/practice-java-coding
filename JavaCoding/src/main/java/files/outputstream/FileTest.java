package files.outputstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
    public static void main(String[] args) throws IOException {
        String str = "Hello";

        Files.createDirectories(Path.of("example"));
        Path path = Paths.get("example/fileName.txt");
        byte[] strToBytes = str.getBytes();

        Files.write(path, strToBytes);

        Files.delete(path);

    }
}
