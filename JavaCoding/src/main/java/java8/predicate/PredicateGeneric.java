package java8.predicate;


import java.time.LocalDate;

public class PredicateGeneric<T extends Comparable<T>> implements Comparable<PredicateGeneric<T>>
{
    private T value;

    public PredicateGeneric(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(PredicateGeneric<T> o) {
        return get().compareTo(o.get());
    }

    public T get() {
        if (value instanceof Integer) {
            return value;
        } else if (value instanceof String) {
            return value;
        } else if (value instanceof LocalDate) {
            return (T) this.convertToDateViaSqlDate((LocalDate) value);
        }
        return null;
    }

    public Long convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert).getTime();
    }
}