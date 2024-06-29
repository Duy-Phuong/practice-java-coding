package encryption.secret;

import java.security.SecureRandom;
import java.util.Base64;

public class CreateSecretKeyWithSecureRandom {
    public static void main(String[] args) {
        // Creating the object
        SecureRandom random = new SecureRandom();
        // We can invoke the following method
        // to retrieve random bytes
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        System.out.println(bytes);
        System.out.println(Base64.getUrlEncoder().withoutPadding().encodeToString(bytes));
    }
}
