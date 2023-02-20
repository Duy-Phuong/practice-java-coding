package bestpractice;


interface Doable{
    String doSomething(String str);
}

// https://www.delftstack.com/howto/java/java-pass-method-as-parameter/

// You can check class LambdaExpressionExample3 or ClientRegistrationRepository to use lambda expressions
public class LambdaSimpleTesting {
    public static void main(String[] args) {
        Doable doa = (str)-> str+" Rohan";
        show("Hello", doa); // passing lambda function as parameter
    }

    public static void show(String msg, Doable doa) {
        String greeting = doa.doSomething(msg);
        System.out.println(greeting);
    }
}
