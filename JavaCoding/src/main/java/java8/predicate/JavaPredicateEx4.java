package java8.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class CustomPredicate<T> implements Predicate<T> {

    @Override
    public boolean test(T v) {
        Integer max = 50;
        Integer min = 0;
        String text = "aa";
        Country country = (Country) v;
        return country.getPopulation() < max && country.getPopulation() > min && country.getName().contains(text);
    }
}

public class JavaPredicateEx4 {

    public static void main(String[] args) {

        List<Country> countries = new ArrayList<>();

        countries.add(new Country("Iran", 80840713));
        countries.add(new Country("Hungary", 9845000));
        countries.add(new Country("Poland", 38485000));
        countries.add(new Country("India", 1342512000));
        countries.add(new Country("Latvia", 1978000));
        countries.add(new Country("Vietnam", 95261000));
        countries.add(new Country("Sweden", 9967000));
        countries.add(new Country("Iceland", 337600));
        countries.add(new Country("Israel", 8622000));
        countries.add(new Country("Israael", 22));

        Predicate<Country> p1 = c -> c.getName().startsWith("I") &&
                c.getPopulation() > 10000000;

        countries.stream().filter(p1).forEach(System.out::println);

        System.out.println("===========================");
        CustomPredicate<Country> btf = new CustomPredicate<>();
        countries.stream().filter(btf).forEach(System.out::println);
    }
}