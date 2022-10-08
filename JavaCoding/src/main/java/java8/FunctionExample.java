package java8;

import java.util.function.Function;

@FunctionalInterface
interface Foo {
    String method(String string);
}

public class FunctionExample {
    public static String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }

    public static void main(String[] args) {
//        public interface Function<T, R> { … }
//        We may replace the lambda with a method reference that matches passed and returned value types.

        Function<String, Integer> f = s -> s.length();
        System.out.println(f.apply("I am happy now"));


        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteIntToString = quote.compose(intToString);

        // print "5"
        System.out.println(quoteIntToString.apply(5));

        // Best practice
//        Foo foo = parameter -> parameter + " from lambda";
//        String result = useFoo.add("Message ", foo);

        Function<String, String> fn =
                parameter -> parameter + " from lambda";
        String result = add("Message ", fn);

        System.out.println(result);

    }
}
