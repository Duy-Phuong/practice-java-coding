package java8.optional;

import java.util.OptionalInt;

public class OptionalIfPresentExample {
    public static void main(String[] args) {
        // create a OptionalInt
        OptionalInt opint = OptionalInt.empty();

        // apply ifPresent(IntConsumer)
        opint.ifPresent((value) -> {
            value = value * 2;
            System.out.println("Value after modification:=> "
                    + value);
        });

        System.out.println("As OptionalInt is empty value"
                + " is not modified");
    }
}
