package encryption.secret;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

// Send webhook notification

public class HmacSHA512Example {
  private static final String HMAC_SHA512 = "HmacSHA512";

  public static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte h : hash) {
      String hex = Integer.toHexString(0xff & h);
      if (hex.length() == 1) hexString.append('0');
      hexString.append(hex);
    }
    return hexString.toString();
  }

  // Another way to convert to hexString from bytes
  private static String bytesToHex2(byte[] bytes) {
    Formatter formatter = new Formatter();
    for (byte b : bytes) {
      formatter.format("%02x", b);
    }
    return formatter.toString();
  }

  public static String hmacWithJava(String algorithm, String data, String key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
    Mac mac = Mac.getInstance(algorithm);
    mac.init(secretKeySpec);
    byte[] macData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    // In ubiid we only need to encode with base64 and attach to the header
    // return Base64.getEncoder().encodeToString(macData);
    return bytesToHex(macData);
  }

  public static void main(String[] args) throws Exception {
    String hmacSHA256Value = "5b50d80c7dc7ae8bb1b1433cc0b99ecd2ac8397a555c6f75cb8a619ae35a0c35";
    String hmacSHA256Algorithm = "HmacSHA256";
    String data = "baeldung";
    String key = "123456";

    String result = hmacWithJava(hmacSHA256Algorithm, data, key);

    System.out.println("Value: " + hmacSHA256Value);
    System.out.println("Result: " + result);

    // Verify by input data + key: https://codebeautify.org/hmac-generator
    // HMAC (Hash-based Message Authentication Code) using SHA-256 is not meant to be decrypted.
    // HMAC is a mechanism for message authentication, which involves hashing the original data
    // along with a secret key to produce a unique hash. This hash is then used to verify the
    // authenticity and integrity of the data.
  }
}
