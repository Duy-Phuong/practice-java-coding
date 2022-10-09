package designpattern.decorator;

public class DecoratorDemo {

    public static void main(String[] args) {

        Animal animal = new LivingAnimal();
        animal.describe();

        animal = new LegDecorator(animal);
        animal.describe();

        animal = new WingDecorator(animal);
        animal.describe();

        animal = new GrowlDecorator(animal);
        animal = new GrowlDecorator(animal);
        animal.describe();

//        GrowlDecorator[] c = {new GrowlDecorator(animal)};
//        Arrays.stream(c)
//            .map(
//                value -> {
//                    System.out.println(value);
//                    return value.getClass().getName();
//                }
//                    )
//            .collect(Collectors.toList());
//        System.out.println(c);
    }

}
