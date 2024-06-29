package keystore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class GetCertificateChainKeystoreExample {

  public static void main(String[] args) {

    // Following readme.md to create a keystore file
    try (InputStream inputStream =
        GetCertificateChainKeystoreExample.class
            .getClassLoader()
            .getResourceAsStream("example.p12")) {
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

      String keystorePassword = "changeit";
      String keystoreAlias = "example";
      keystore.load(inputStream, keystorePassword.toCharArray());

      // getting the certificate
      // using getCertificateChain() method
      Certificate[] certchain = keystore.getCertificateChain(keystoreAlias);

      // display the result
      System.out.println("Type of certificate at index 0 : " + certchain[0].getType());
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
