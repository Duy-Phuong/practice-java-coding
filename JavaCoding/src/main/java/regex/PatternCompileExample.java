package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCompileExample {
    static Pattern ADMIN_PATH_PATTERN = Pattern.compile("/admins/.*");
    private static String UUID_PATTERN = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})";
    private static Pattern CONTAIN_ONE_KIND_OF_FRUIT = Pattern.compile(".*/(apple|mango)/" + UUID_PATTERN + ".*");

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

    }
}
