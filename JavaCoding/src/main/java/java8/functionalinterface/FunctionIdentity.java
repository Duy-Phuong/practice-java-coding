package java8.functionalinterface;

import java.util.function.Function;

public class FunctionIdentity {
    public static void main(String[] args){
        Function<Integer, Integer> identity = Function.identity();
        int arg = 1;
        System.out.printf("(%s == identity.apply(%s)) = %s", arg, arg, (arg == identity.apply(arg)));
        // Output: (1 == identity.apply(1)) = true
    }
}
