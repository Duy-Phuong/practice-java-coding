package base64;

import java.util.Base64;
import java.util.regex.Pattern;

public class Base64Validation {
    private static final String BASE64_ENCODED_PATTERN =
            "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";


    public static void main(String[] args) {
        // https://www.baeldung.com/java-check-string-base64-encoding#using-regular-expressions
        Pattern BASE64_PATTERN = Pattern.compile(
                "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$"
        );

        System.out.println("---- TEST CASE 1 ----");
        System.out.println((BASE64_PATTERN.matcher("SGVsbG8gd29ybGQ=").matches()));
        System.out.println(Pattern.matches(BASE64_ENCODED_PATTERN, "SGVsbG8gd29ybGQ="));

        String s2 = "JVBERi0xLjcNCiW1tbW1DQoxIDAgb2JqDQo8PC9UeXBlL0NhdGFsb2cvUGFnZXMgMiAwIFIvTGFuZyhlbikgL1N0cnVjdFRyZWVSb290IDIyIDAgUi9NYXJrSW5mbzw8L01hcmtlZCB0cnVlPj4vTWV0YWRhdGEgNj3Thisisinvalid";
        System.out.println("---- TEST CASE 2 ----");
        System.out.println((BASE64_PATTERN.matcher(s2).matches()));
        System.out.println(Pattern.matches(BASE64_ENCODED_PATTERN, s2));

        String s3 = "Testing";
        System.out.println("---- TEST CASE 3 ----");
        System.out.println((BASE64_PATTERN.matcher(s3).matches()));
        System.out.println(Pattern.matches(BASE64_ENCODED_PATTERN, s3));


        String s4 = "ABCD123==";
        System.out.println("---- TEST CASE 4 ----");
        System.out.println((BASE64_PATTERN.matcher(s4).matches()));
        System.out.println(Pattern.matches(BASE64_ENCODED_PATTERN, s4));

        // If we only use Base64.getDecoder().decode, we pass `Testing` string and it can decode successfully since it's a valid base64 content
        // Solution: Using decode method with try catch is good enough since it is faster 22 times than using regex
        try {
            Base64.getDecoder().decode(s4);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
