package base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64EncodeAndDecode {
    public static void main(String[] args) {
        System.out.println("============== BASE64 ==================");
        String s = Base64.getEncoder().encodeToString("Hello".getBytes());
        System.out.println("encoded string: " + s);
        byte[] bytes = Base64.getDecoder().decode(s);
        System.out.println("Decoded string: " + new String(bytes, StandardCharsets.UTF_8));
    }
}
