package regex;

public class CheckDigitNumber {
    public static void main(String[] args) {
        // https://stackoverflow.com/questions/15111420/how-to-check-if-a-string-contains-only-digits-in-java
//        String regex = "\\d+";
        String regex = "^[0-9]*$";

        // positive test cases, should all be "true"
        System.out.println("1".matches(regex));
        System.out.println("12345".matches(regex));
        System.out.println("123456789".matches(regex));
        System.out.println("1.2".matches(regex));

        // negative test cases, should all be "false"
        System.out.println("".matches(regex));
        System.out.println("foo".matches(regex));
        System.out.println("aa123bb".matches(regex));
    }
}
