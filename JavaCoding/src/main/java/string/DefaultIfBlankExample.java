package string;

import org.apache.commons.lang3.StringUtils;

// https://www.educative.io/answers/what-is-stringutilsdefaultifblank-in-java
public class DefaultIfBlankExample {
    public static void main(String[] args)
    {
        System.out.println(StringUtils.defaultIfBlank("", "defaultString"));
        System.out.println(StringUtils.defaultIfBlank(null, "defaultString"));
        System.out.println(StringUtils.defaultIfBlank("passedString", "defaultString"));
        System.out.println(StringUtils.defaultIfBlank("\t ", "defaultString"));
    }
}
