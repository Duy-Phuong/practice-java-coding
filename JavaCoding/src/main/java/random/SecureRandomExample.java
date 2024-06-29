package random;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class SecureRandomExample {
    public static void main(String[] args) {
        //create instance of securerandom
        SecureRandom rndm = new SecureRandom();

        //create byte array
        byte[] bytes = new byte[16];

        //generate random bytes
        rndm.nextBytes(bytes);

        //display the byte array
        System.out.println(Arrays.toString(bytes));

        // Create a random token string
        System.out.println(Base64.getUrlEncoder().withoutPadding().encodeToString(bytes));
    }
}
