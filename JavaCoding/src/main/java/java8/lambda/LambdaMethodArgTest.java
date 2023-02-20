package java8.lambda;

interface Algebra {
    int operate(int a, int b);
}
enum Operation {
    ADD, SUB, MUL, DIV
}

// https://techndeck.com/java-pass-lambda-as-parameter-to-method-how-to-java8/
// https://www.tutorialspoint.com/how-to-pass-a-lambda-expression-as-a-method-parameter-in-java
public class LambdaMethodArgTest {
    public static void main(String[] args) {
        print((a, b) -> a + b, Operation.ADD);
        print((a, b) -> a - b, Operation.SUB);
        print((a, b) -> a * b, Operation.MUL);
        print((a, b) -> a / b, Operation.DIV);
    }
    static void print(Algebra alg, Operation op) {
        switch (op) {
        case ADD:
            System.out.println("The addition of a and b is: " + alg.operate(40, 20));
            break;
        case SUB:
            System.out.println("The subtraction of a and b is: " + alg.operate(40, 20));
            break;
        case MUL:
            System.out.println("The multiplication of a and b is: " + alg.operate(40, 20));
            break;
        case DIV:
            System.out.println("The division of a and b is: " + alg.operate(40, 20));
            break;
        default:
            throw new AssertionError();
        }
    }
}
