package files.transfer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopyInputStreamToOutputStream {
    public static void main(String[] args) {
        String initialString = "Hello World!";

        try (InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
             ByteArrayOutputStream targetStream = new ByteArrayOutputStream()) {
            inputStream.transferTo(targetStream);

            System.out.println(new String(targetStream.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
