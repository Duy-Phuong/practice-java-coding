package regex.pattern;

import java.util.regex.Pattern;

public class PatternCompileExample3 {
    public static void main(String[] args) {
        // create a REGEX String
        String REGEX = "(.*)(for)(.*)?";

        // create the string
        // in which you want to search
        String actualString
                = "code of Machine";

        // compile the regex to create pattern
        // using compile() method
        Pattern pattern = Pattern.compile(REGEX,
                Pattern.CASE_INSENSITIVE);

        // check whether Regex string is
        // found in actualString or not
        boolean matches = pattern
                .matcher(actualString)
                .matches();

        System.out.println("actualString "
                + "contains REGEX = "
                + matches);
    }
}
