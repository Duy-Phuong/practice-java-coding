package java8.predicate;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CachedSupplierTest {
    public static void main(String[] args) {
        CachedSupplier<Double> doubleSupplier = new CachedSupplier<>(Math::random);
        CachedSupplier<Double> doubleSupplier2 = new CachedSupplier<>(Math::random);
        System.out.println(doubleSupplier.get() + " - " +doubleSupplier2.get());
        List<CachedSupplier<Double>> list = new ArrayList<>();
        list.add(doubleSupplier);
        list.add(doubleSupplier2);
        System.out.println("After sort: ");
        Collections.sort(list);
        list.forEach(x -> System.out.println(x.get()));

        System.out.println("\n----------------- Long --------------------");
        CachedSupplier<Long> longSupplier = new CachedSupplier<>(System::currentTimeMillis);
        CachedSupplier<Long> longSupplier2 = new CachedSupplier<>(System::currentTimeMillis);
        System.out.println(doubleSupplier.get() + " - " +doubleSupplier2.get());
        List<CachedSupplier<Long>> list2 = new ArrayList<>();
        list2.add(longSupplier);
        list2.add(longSupplier2);
        System.out.println("After sort: ");
        Collections.sort(list);
        list.forEach(x -> System.out.println(x.get()));
    }
}
