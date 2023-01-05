package java8.lambda;

@FunctionalInterface  //It is optional
interface Validator {
    public String getValidation();
}

public class LambdaExpressionExample3 {
    public static void main(String[] args) {
        Integer width = 10;

        //with lambda
        Validator d2 = () -> {
            return width.toString();
        };
        System.out.println(d2.getValidation());
    }
}

