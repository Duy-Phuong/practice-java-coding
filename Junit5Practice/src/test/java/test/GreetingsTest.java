package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GreetingsTest {
    @Test
    void whenCallingSayHello_thenReturnHello() {
        assertTrue("Hello".equals(Greetings.sayHello()));
    }
}