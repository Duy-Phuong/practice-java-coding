package java8.predicate;


import java.util.function.Supplier;

public class CachedSupplier<T extends Comparable<T>> implements Comparable<CachedSupplier<T>>
{
    private Supplier<? extends T> supplier;
    private T value;
    boolean isAvailable;

    public CachedSupplier(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (!isAvailable) {
            value = supplier.get();
            isAvailable = true;
        }
        return value;
    }

    public int compareTo(CachedSupplier<T> o) {
        return get().compareTo(o.get());
    }
}