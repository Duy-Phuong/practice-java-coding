package files.debug;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateFileBySize {
    public static void main(String[] args) {
        // Create file with size 700KB
//        Files.write(Paths.get("example.txt"),
//                IOUtils.readFully(randomInputStream(700 * 1024), 700 * 1024));
    }


    public static InputStream randomInputStream(long size) {
        return new InputStream() {
            private final long total = size;
            private final byte[] sample = new byte[1024];
            private long pos = 0;

            public int read() throws IOException {
                if (pos >= total) return -1;
                if (pos % sample.length == 0) ThreadLocalRandom.current().nextBytes(sample);
                // Math.abs MUST be used here because the contract of `read()` states that
                // The value byte is returned as an int in the range 0 to 255.
                return Math.abs(sample[(int)(pos++ % sample.length)]);
            }
        };
    }
}
