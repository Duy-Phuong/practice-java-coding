package keystore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class LoadKeystoreExample {

  public static void main(String[] args) {

    // Following readme.md to create a keystore file
    try (InputStream inputStream =
        LoadKeystoreExample.class.getClassLoader().getResourceAsStream("example.p12")) {
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

      String keystorePassword = "changeit";
      String keystoreAlias = "example";
      keystore.load(inputStream, keystorePassword.toCharArray());

      // checking the presence of the aliases
      // using containsAlias() method
      boolean status
              = keystore.containsAlias(keystoreAlias);

      // display the result
      if (status) System.out.println("This aliases " + keystoreAlias + " is present");
      else System.out.println("This aliases " + keystoreAlias + " is absent");

      Enumeration<String> enumeration = keystore.aliases();
      while (enumeration.hasMoreElements()) {
        String alias = enumeration.nextElement();
        System.out.println("alias name: " + alias);

        // getting the certificate
        // using getCertificate() method
        Certificate certificate = keystore.getCertificate(alias);
        System.out.println("\n ====== certificate ======= \n");
        System.out.println(certificate.toString());
      }
    } catch (java.security.cert.CertificateException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
