
Read:

- secret
CreateSecretKeyWithSecureRandom.java
HmacSHA512Example.java
- symmetric
GenerateSymmetricKey.java
EncryptAndDecryptExample.java
-asymmetric
AsymmetricGenerateRSAKkeyPair.java
EncryptAndDecryptExample.java
- digitalenvelope
CombineSymmetricAndAsymmetric.java

- Keystrore
/Users/phuong/Documents/developer/development/private-source/obsidian/practice-template-project/spring-digital-signature-demo/README.md

Command to generate the keystore

```bash
keytool -genkeypair -alias example -keyalg RSA -keysize 4096 -dname "cn=example.com,ou=exampleou,dc=example,dc=com" -keypass changeit -validity 365 -storetype PKCS12 -storepass changeit -keystore example.p12
```
- *alias* – the name for our certificate
- *keypass* – the password of the certificate. We’ll need this password to have access to the private key of our certificate
- *validity* – the time (in days) of the validity of our certificate
- *storepass* – the password for the keystore. This will be the password of the keystore if the store doesn’t exist

List

```bash
❯ keytool -list -keystore example.p12
Keystore type: PKCS12
Keystore provider: SUN

Your keystore contains 1 entry

❯ keytool -list -keystore example.p12 -alias example


```