package java8;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExample {

//    The Supplier functional interface is yet another Function specialization
//    that does not take any arguments
    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    public static void main(String[] args) {
        Supplier<LocalDateTime> s = () -> LocalDateTime.now();
        LocalDateTime time = s.get();
        System.out.println(time);

        Supplier<Double> lazyValue = () -> {
            return 9d;
        };

        double valueSquared = squareLazy(lazyValue);

//        return 81
        System.out.println(valueSquared);
    }
}
