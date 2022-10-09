package java8;

import java.util.function.Consumer;

public class ConsumerPrintName {

    public static void main(String[] args) {
    }
}

class PrintNameConsumer<T> implements Consumer<T> {

    @Override public void accept(Object o) {
        System.out.println(o);
    }
}