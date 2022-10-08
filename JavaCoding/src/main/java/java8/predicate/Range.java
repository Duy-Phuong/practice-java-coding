package java8.predicate;


import java.util.function.Predicate;

public class Range<T extends Comparable<T>> {

    private static Range sc = null;
    private PredicateGeneric<T> lowerbound;
    private PredicateGeneric<T> upperbound;
    private Predicate<PredicateGeneric<T>> predicateNum;
    private static Predicate<String> predicateStr;


    public Range() {
    }

    private Range(PredicateGeneric<T> lowerbound, PredicateGeneric<T> upperbound) {
        this.lowerbound = lowerbound;
        this.upperbound = upperbound;
    }

    public Range getInstance(PredicateGeneric<T> lowerbound, PredicateGeneric<T> upperbound){
        if(sc==null){
            sc = new Range(lowerbound, upperbound);
        }
        return sc;
    }

    /**
     * Creates a new <b>closed</b> {@code Range} that includes both bounds.
     */
    public Range of(PredicateGeneric<T> lowerbound, PredicateGeneric<T> upperbound) {
        if (lowerbound.compareTo(upperbound) > 0) {
            return null;
        }
        Predicate<PredicateGeneric<T>> isGreaterThan = i -> i.compareTo(lowerbound) >= 0;
        Predicate<PredicateGeneric<T>> isLessThan = i -> i.compareTo(upperbound) <= 0;
        this.predicateNum = isGreaterThan.and(isLessThan);
        return this.getInstance(lowerbound, upperbound);
    }

    public  Range open(PredicateGeneric<T> lowerbound, PredicateGeneric<T> upperbound) {
        if (lowerbound.compareTo(upperbound) > 0) {
            return null;
        }
        Predicate<PredicateGeneric<T>> isGreaterThan = i -> i.compareTo(lowerbound) >= 0;
        Predicate<PredicateGeneric<T>> isLessThan = i -> i.compareTo(upperbound) <= 0;
        this.predicateNum = isGreaterThan.and(isLessThan);

        return this.getInstance(lowerbound, upperbound);
    }

    /**
     * Returns {@code true} on if the given {@code value} is contained in this
     * {@code Range}.
     */
    public boolean contains(PredicateGeneric<T> value) {
        return this.predicateNum.test(value);
    }

    /**
     * Returns the {@code lowerbound} of this {@code Range}.
     */
    public PredicateGeneric<T> getLowerbound() {
        return lowerbound;
    }

    /**
     * Returns the {@code upperbound} of this {@code Range}.
     */
    public PredicateGeneric<T> getUpperbound() {
        return upperbound;
    }

    @Override
    public String toString() {
        return "Range{" +
                "lowerbound=" + lowerbound +
                ", upperbound=" + upperbound +
                '}';
    }

}