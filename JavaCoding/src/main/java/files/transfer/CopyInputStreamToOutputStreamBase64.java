package files.transfer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class CopyInputStreamToOutputStreamBase64 {
    public static void main(String[] args) {
        String data = "Hello World!";
        String initialString = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(initialString);

        System.out.println("====================== DECODE ==========================");

        // DECODE
        try (InputStream encodedInputStream = Base64.getDecoder().wrap(new ByteArrayInputStream(initialString.getBytes()));
             ByteArrayOutputStream decodedTargetStream = new ByteArrayOutputStream()) {
            encodedInputStream.transferTo(decodedTargetStream);

            System.out.println(new String(decodedTargetStream.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("====================== ENCODE ==========================");
        // ENCODE
        try (InputStream encodedInputStream = new ByteArrayInputStream(data.getBytes());
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream decodedTargetStream = Base64.getEncoder().wrap(byteArrayOutputStream);
             ) {
            encodedInputStream.transferTo(decodedTargetStream);

//            String encodedTarget = new String(byteArrayOutputStream.toByteArray());
            String encodedTarget = byteArrayOutputStream.toString();
            System.out.println(encodedTarget);
            System.out.println(new String(Base64.getDecoder().decode(encodedTarget)));
            System.out.println(new String(Base64.getDecoder().decode(initialString)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
