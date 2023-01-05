package java8.lambda;

interface Drawable {
    public void draw();
}

// https://www.javatpoint.com/java-lambda-expressions#:~:text=Lambda%20expression%20provides%20implementation%20of,an%20interface%20as%20functional%20interface.

public class LambdaExpressionExample {
    public static void main(String[] args) {
        int width = 10;

        //without lambda, Drawable implementation using anonymous class
        Drawable d = new Drawable() {
            public void draw() {
                System.out.println("Drawing " + width);
            }
        };
        d.draw();
    }
}
