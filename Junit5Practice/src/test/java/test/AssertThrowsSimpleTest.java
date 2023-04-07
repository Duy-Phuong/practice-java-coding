package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class AssertThrowsSimpleTest {
    @Test
    void testExpectedException() {

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        }, "NumberFormatException was expected");

        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
    }

    @Test
    void testExpectedExceptionWithParentType() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Integer.parseInt("One");
        });
    }
}
