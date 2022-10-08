package java8;

/*
 @FunctionalInterface annotation is used to ensure that the functional interface
 can’t have more than one abstract method. In case more than one abstract methods
 are present, the compiler flags an ‘Unexpected @FunctionalInterface annotation’
 message. However, it is not mandatory to use this annotation.

Some Built-in Java Functional Interfaces
        Since Java SE 1.8 onwards, there are many interfaces that are converted into
        functional interface. All these interfaces are annotated with @FunctionalInterface.
        These interfaces are as follows –

        Runnable –> This interface only contains the run() method.
        Comparable –> This interface only contains the compareTo() method.
        ActionListener –> This interface only contains the actionPerformed() method.
        Callable –> This interface only contains the call() method.

Java SE 8 included four main kinds of functional interfaces which can be applied in multiple
        situations. These are:

        Consumer
        Predicate
        Function
        Supplier
        Amidst the previous four interfaces, the first three interfaces,i.e., Consumer,
        Predicate, and Function, likewise have additions that are provided beneath –

        Consumer -> Bi-Consumer
        Predicate -> Bi-Predicate
        Function -> Bi-Function, Unary Operator, Binary Operator

 */
@FunctionalInterface
interface Square {
    int calculate(int x);
}

class Test {
    public static void main(String args[])
    {
        int a = 5;

        // lambda expression to define the calculate method
        Square s = (int x) -> x * x;

        // parameter passed and return type must be
        // same as defined in the prototype
        int ans = s.calculate(a);
        System.out.println(ans);
    }
}

