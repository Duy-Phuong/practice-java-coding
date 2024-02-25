package collection.convert;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertListToMapExample {
    @Data
    @Builder
    public static class Animal {
        private int id;
        private String name;

        //  constructor/getters/setters
    }

    public static Map<Integer, Animal> convertListBeforeJava8(List<Animal> list) {
        Map<Integer, Animal> map = new HashMap<>();
        for (Animal animal : list) {
            map.put(animal.getId(), animal);
        }
        return map;
    }

    public static Map<Integer, Animal> convertListAfterJava8(List<Animal> list) {
        Map<Integer, Animal> map = list.stream()
                .collect(Collectors.toMap(Animal::getId, Function.identity()));
        return map;
    }

    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(Animal.builder().id(1).name("dog").build());
        list.add(Animal.builder().id(2).name("cat").build());
        Map<Integer, Animal> map = convertListBeforeJava8(list);
        System.out.println(map);

        Map<Integer, Animal> map2 = convertListAfterJava8(list);
        System.out.println(map2);
    }
}
