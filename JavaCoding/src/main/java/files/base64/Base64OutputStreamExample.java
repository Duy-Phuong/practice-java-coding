package files.base64;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64OutputStreamExample {
    private static final String DEFAULT_TEMPORARY_DIRECTORY = "java.io.tmpdir";

    public static void main(String[] args) {
        byte[] emptyArray = new byte[0];
        System.out.println("Create an empty bytes array");
        System.out.println(emptyArray);
        System.out.println(emptyArray.length);

        System.out.println("================================");

        System.out.println("Temporary folder" + System.getProperty(DEFAULT_TEMPORARY_DIRECTORY));

        // Need to add commons-codec to pom.xml to avoid error

        System.out.println("=============== Base64OutputStream with decode =================");
        // false => DECODE SGVsbG8= => Hello
        try (Base64OutputStream bout = new Base64OutputStream(new FileOutputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), false);) {
//            byte b[] = "Hello".getBytes();
            byte b[] = "SGVsbG8=".getBytes();
            // NOTE: Base64OutputStream cannot throw exception exactly like Base64.getDocoder().decode()
//            byte b[] = "%".getBytes();
            bout.write(b);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("=============== Base64InputStream =================");
        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()));) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // �e
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), false);) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // �e
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get data from file and ENCODE it: ex: from Hello => SGVsbG8=
        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), true);) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // SGVsbG8=
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("================= Read file contents =================");
        try {
            // Cannot apply for big file (> 2GB)
            byte[] bytes2 = Files.readAllBytes(Paths.get("/Users/phuong/Downloads/test/base/text.txt"));
            System.out.println(new String(bytes2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=============== Base64OutputStream with encode =================");

        try (Base64OutputStream bout = new Base64OutputStream(new FileOutputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), true);) {
            byte b[] = "Hello".getBytes();
            bout.write(b);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("=============== Base64InputStream =================");
        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()));) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // Hello
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), false);) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // Hello
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get data from file and ENCODE it: ex: from Hello => SGVsbG8=
        try (Base64InputStream base64InputStream2 = new Base64InputStream(new FileInputStream(Paths.get("/Users/phuong/Downloads/test/base/text.txt").toFile()), true);) {
            byte[] bytes2 = base64InputStream2.readAllBytes();
            System.out.println(new String(bytes2)); // U0dWc2JHOD0NCg==
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("================= Read file contents =================");
        try {
            // Cannot apply for big file (> 2GB)
            byte[] bytes2 = Files.readAllBytes(Paths.get("/Users/phuong/Downloads/test/base/text.txt"));
            System.out.println(new String(bytes2));
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("============== BASE64 ==================");
        String s = Base64.getEncoder().encodeToString("Hello".getBytes());
        System.out.println("encoded string: " + s);
        byte[] bytes = Base64.getDecoder().decode(s);
        System.out.println("Decoded string: " + new String(bytes, StandardCharsets.UTF_8));
        Path path = Paths.get("baeldung/tutorial.txt");

    }
}
