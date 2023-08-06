package bestpractice;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

//Pattern to check fields in object with generic type and return error message like:
//[name is required, lastName is required, isSomething MUST be true]

// Explanation: How to use lambda with interface => LambdaExpressionExample3

public class CheckObjectFieldsNotNullPattern {
    public static void main(String args[]) {

        SampleDataStructure justSample = new SampleDataStructure();

        System.out.println(
                Stream.of(
                                SimpleValidator.validate(Objects::nonNull, justSample::getName, "name is required"),
                                SimpleValidator.validate(StringUtils::isNotEmpty, justSample::getLastName,
                                        "lastName is required"),
                                SimpleValidator.validate((any) -> any == true, justSample::isSomething,
                                        "isSomething MUST be true")
                        )
                        .map(SimpleValidator::getValidation)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(java.util.stream.Collectors.toList())
        );

    }

    public static class StringUtils {
        public static boolean isEmpty(String someString) {
            return someString == null ? true : someString.isEmpty();
        }

        public static boolean isNotEmpty(String someString) {
            return !isEmpty(someString);
        }
    }

    public static class SampleDataStructure {

        public String getName() {
            return null;
        }

        public String getLastName() {
            return "";
        }

        public boolean isSomething() {
            return false;
        }

        public int getAge() {
            return 99;
        }
    }

    @FunctionalInterface
    public interface SimpleValidator {

        public static <T> SimpleValidator validate(Predicate<T> validateFunction, Supplier<T> valueExtractor,
                String failValidationMessage) {
            return () -> java.util.Optional.ofNullable(
                    validateFunction.test(valueExtractor.get()) ? null : failValidationMessage);
        }

        public java.util.Optional<String> getValidation();
    }
}


