package java8.groupby;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8MappingExamples4 {
    public static void main(String[] args) {

        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println(result);

    }
}

//{
//        19.99=[
//        Item{name='banana', qty=20, price=19.99},
//        Item{name='banana', qty=10, price=19.99}
//        ],
//        29.99=[
//        Item{name='orang', qty=10, price=29.99},
//        Item{name='watermelon', qty=10, price=29.99}
//        ],
//        9.99=[
//        Item{name='apple', qty=10, price=9.99},
//        Item{name='papaya', qty=20, price=9.99},
//        Item{name='apple', qty=10, price=9.99},
//        Item{name='apple', qty=20, price=9.99}
//        ]
//        }
//
//        //group by + mapping to Set
//        {
//        19.99=[banana],
//        29.99=[orang, watermelon],
//        9.99=[papaya, apple]
//        }