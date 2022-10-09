package secret;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Example {
    private static final String HMAC_SHA512 = "HmacSHA512";

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
        Mac mac = Mac.getInstance(HMAC_SHA512);
        mac.init(secretKeySpec);
//        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
        return toHexString(mac.doFinal(data.getBytes()));
    }

    public static void main(String[] args) throws Exception {
//        String data = "20201019T141652Z|{\"id\":\"2093aa28-58a0-402b-a27a-1869efe88681\",\"tenantId\":\"925e28ef-188f-4913-86d8-103b7fd2c885\",\"ubiIdCaseId\":\"b0d74bfb-4fa2-41a5-b06d-6ffdbab7372f\",\"extPersonId\":\"extPersonId\",\"identityProfile\":13212,\"language\":\"DE\",\"extCaseId\":\"11111111\",\"extApplication\":\"2222222222\",\"ubiIdCategory\":\"categorycode\",\"status\":\"SCHEDULED\",\"createdTime\":\"20201019T141646Z\",\"qTSPstatus\":\"NO_REGISTRATION\"}";
//        String data = "20210126T113817Z|{\"id\":\"81824798-9b28-4326-88d6-4dce6eff81c5\",\"tenantId\":\"925e28ef-188f-4913-86d8-103b7fd2c885\",\"extCaseId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"extApplication\":\"SELFONBOARDING\",\"documentBasketId\":\"b664ef00-fa87-48c8-890c-115f581648c8\",\"documentBasketStatus\":\"OUTSTANDING\",\"signers\":null,\"documents\":null}";

//        String data = "20210126T120438Z|{\"id\":\"8353a36e-2073-4871-9af5-9c514ad7da3a\",\"tenantId\":\"925e28ef-188f-4913-86d8-103b7fd2c885\",\"extCaseId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"extApplication\":\"SELFONBOARDING\",\"documentBasketId\":\"d3f87e47-6c08-4d1e-929d-7fb77a306a76\",\"documentBasketStatus\":\"OUTSTANDING\",\"signers\":null,\"documents\":null}";
        String data = "1587146330086:{\"id\":\"44b5e0cc-087d-4451-9bc7-1dcbd1d8a491\",\"date\":1587056526998,\"tenantId\":\"4eef8535-7465-4200-91e6-a5562e0a2db3\",\"event\":\"Video.Verified\",\"data\":{\"id\":\"e6fc1734-d691-485b-aefd-415524fea247\",\"videoId\":\"e5c48194-48e1-4dba-8cce-82a4c1d95a91\",\"verificationRequestId\":\"f2a132bf-b199-4ae9-8593-29109df2250c\",\"verifierId\":\"a51d3638-3bda-4f43-8551-5c1ce5480378\",\"rauthorityId\":\"c975edea-318f-446d-8762-0cf930167fc3\",\"verificationDate\":1587056526000,\"duration\":9,\"status\":\"Accepted\",\"subject\":{\"primaryName\":\"PATRICK\",\"secondaryName\":\"BRAZZALE\",\"documentNumber\":\"E1382687\",\"birthDate\":400896000000,\"expiryDate\":1673740800000,\"nationality\":\"CHE\",\"issuer\":\"CHE\",\"sex\":\"M\",\"otherEditableFields\":[{\"name\":\"documentNumberCtrl\",\"value\":\"9\"},{\"name\":\"issuingDate\",\"value\":\"Jan13,201612:00:00AM\"},{\"name\":\"birthDateCtrl\",\"value\":\"3\"},{\"name\":\"expiryDateCtrl\",\"value\":\"8\"},{\"name\":\"birthPlace\",\"value\":\"R\"}]},\"acceptance\":{\"reasons\":[{\"type\":\"Environment.NotAppropriate\",\"message\":\"Istherealivingperson,recognizableinawell-litenvironment?\"},{\"type\":\"Document.Aspect\",\"message\":\"Isthereevidenceofalegalidentitydocument?\"},{\"type\":\"Document.NotSecurityElements\",\"message\":\"Aredocumentfrontsecurityelementsidentified?\"},{\"type\":\"Document.NotLegible\",\"message\":\"Isthedocumentdatareadable?\"},{\"type\":\"Document.DoesNotMatchData\",\"message\":\"Dothefrontandbackofthedocumentcorrespond?\"},{\"type\":\"Photo.Invalid\",\"message\":\"Arethecharacteristicsofthephotoidentifiedwiththosedeterminedintheprotocol?\"},{\"type\":\"Photo.DoesNotMatchPerson\",\"message\":\"Doesthepersoninthevideoanddocumentmatcheachother?\"}]}}}";
        System.out.println(data);
        String hmac = calculateHMAC( data, "2c30b4fe7ea8b4dd2c805eb09e51d6f3");
        // Convert to base 64 => https://base64.guru/converter/encode/hex to check the same
        System.out.println(hmac);
    }
}
