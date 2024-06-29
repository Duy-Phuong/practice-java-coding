package encryption.digitalenvelope;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class CombineSymmetricAndAsymmetric {
  public static final String AES = "AES";
  public static final String RSA = "RSA";

  public static void main(String[] args) throws Exception {
    // 1. Create symmetric key
    KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
    // Initializing the KeyGenerator with 256 bits.
    keygenerator.init(256);
    SecretKey aesKey = keygenerator.generateKey();

    // 2. Generate RSA key pair
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // 3. Encrypt data using AES
    String plainText = "Private message";
    Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
    aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
    byte[] encryptedData = aesCipher.doFinal(plainText.getBytes());

    // 4. Encrypt AES key using RSA public key
    Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
    rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedAESKey = rsaCipher.doFinal(aesKey.getEncoded());

    // 5. Decrypt AES key using RSA private key
    rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedAESKey = rsaCipher.doFinal(encryptedAESKey);

    // 6. Create SecretKey from decrypted bytes
    SecretKey secretKey = new SecretKeySpec(decryptedAESKey, AES);
    // Decrypt data with AES key
    aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedData = aesCipher.doFinal(encryptedData);
    String decryptedDataString = new String(decryptedData);

    // 7. Encrypt data with AES key and send back
    Cipher newAesCipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
    newAesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] responseEncryptedData = newAesCipher.doFinal("Response message".getBytes());

    byte[] responseDecryptedData = aesCipher.doFinal(responseEncryptedData);

    System.out.println("Original text: " + plainText);
    System.out.println(
        "Original AES key: " + Base64.getEncoder().encodeToString(aesKey.getEncoded()));
    System.out.println("Encrypted text: " + Base64.getEncoder().encodeToString(encryptedData));
    System.out.println("Encrypted AES key: " + Base64.getEncoder().encodeToString(encryptedAESKey));
    System.out.println("Decrypted AES key: " + Base64.getEncoder().encodeToString(decryptedAESKey));
    System.out.println("Decrypted data: " + decryptedDataString);
    System.out.println("Send response data: " + new String(responseDecryptedData));
  }
}
