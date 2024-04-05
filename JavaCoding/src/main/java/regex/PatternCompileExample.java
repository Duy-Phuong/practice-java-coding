package regex;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCompileExample {
    static Pattern ADMIN_PATH_PATTERN = Pattern.compile("/admins/.*");
    private static String UUID_PATTERN = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})";
    private static Pattern CONTAIN_ONE_KIND_OF_FRUIT = Pattern.compile(".*/(apple|mango)/" + UUID_PATTERN + ".*");
    private static Pattern CONTAIN_TENANT_ID = Pattern.compile("^.*?/" + UUID_PATTERN + "/.+");
    public static String extractTenantIdFromUriAsString(String uri) {
        Matcher matcher = CONTAIN_TENANT_ID.matcher(uri);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return "";
    }

    private static boolean isAdminsPath(String requestUri) {
        return ADMIN_PATH_PATTERN.asPredicate().test(requestUri);
    }

    public static void main(String[] args)
    {
        // create a REGEX String
        String REGEX = ".*www.*";

        // creare the string
        // in which you want to search
        String actualString
                = "www.geeksforgeeks.org";

        // compile the regex to create pattern
        // using compile() method
        Pattern pattern = Pattern.compile(REGEX);

        // get a matcher object from pattern
        Matcher matcher = pattern.matcher(actualString);

        // check whether Regex string is
        // found in actualString or not
        boolean matches = matcher.matches();

        System.out.println("actualString "
                + "contains REGEX = "
                + matches);
//        actualString contains REGEX = true

        System.out.println("isAdminsPath");
        System.out.println(isAdminsPath("https://reqres.in/admins/profile"));

        extractTenantIdFromUriAsString("/internal/925e28ef-188f-4913-86d8-103b7fd2c885/profile");

        Predicate<String> c = Pattern.compile("^\\/(signature-platform\\/).*").asPredicate();
        String url = "/internal/signature-platform/enterpriseId/users/userId/changePassword";
        boolean result = Arrays.asList(c, Pattern.compile("^\\/(internal\\/signature-platform\\/).*").asPredicate()).stream().anyMatch(p -> p.test(url));
        System.out.println(result);

        System.out.println("================================");
        String url1 = "/document-baskets/2434d6e5-618b-45e9-8d06-0e79b247b08d/documents";

        boolean result1 =  Arrays.asList(Pattern.compile("^\\/(document-baskets).+\\/(documents)").asPredicate()).stream().anyMatch(p -> p.test(url1));
        System.out.println(result1);

        String UUID_PATTERN = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})";
        String url2 = "/2434d6e5-618b-45e9-8d06-0e79b247b08d/document-baskets/2434d6e5-618b-45e9-8d06-0e79b247b08d/documents";
        boolean result2 =  Arrays.asList(Pattern.compile("^\\/.+\\/(document-baskets).+\\/(documents)").asPredicate()).stream().anyMatch(p -> p.test(url2));
        System.out.println(result2);

        boolean result3 =  Arrays.asList(Pattern.compile("^\\/" + UUID_PATTERN + "\\/(document-baskets)\\/" + UUID_PATTERN + "\\/(documents)").asPredicate()).stream().anyMatch(p -> p.test(url2));
        System.out.println(result3);

        System.out.println("Pattern check digit numbers");
        String NUMBER = "^[0-9]*$";
        System.out.println(Pattern.compile(NUMBER).asPredicate().test("ac"));
        System.out.println(Pattern.compile(NUMBER).asPredicate().test("123"));

    }
}
