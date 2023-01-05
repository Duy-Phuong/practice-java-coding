package java8.functionalinterface;

// https://www.tutorialspoint.com/how-to-create-our-own-custom-functional-interface-in-java


@FunctionalInterface
interface CustomFunctionalInterface {
    void display();
}
public class FunctionInterfaceLambdaTest {
    public static void main(String args[]) {
        // Using Anonymous inner class
        CustomFunctionalInterface test1 = new CustomFunctionalInterface() {
            public void display() {
                System.out.println("Display using Anonymous inner class");
            }
        };
        test1.display();
        // Using Lambda Expression
        CustomFunctionalInterface test2 = () -> {    // lambda expression
            System.out.println("Display using Lambda Expression");
        };
        test2.display();
    }
}
