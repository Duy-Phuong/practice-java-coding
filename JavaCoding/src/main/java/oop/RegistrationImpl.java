package oop;

public class RegistrationImpl {
    RegistrationInterface getRegistrationInterface() {
        return (name) -> new Student();
    }
}
