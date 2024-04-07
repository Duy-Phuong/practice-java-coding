package regex.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCompileExample1 {
    public static void main(String[] args) {
        // create a REGEX String
        String REGEX = ".*www.*";

        // create the string
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

        // Checks if the string matches with the regex
        // Should be single character a to z
        System.out.println(Pattern.matches("[a-z]", "g"));

        // Check if the element is range a to z or A to Z
        System.out.println(
                Pattern.matches("[a-zA-Z]", "Gfg"));
    }
}
