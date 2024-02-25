package string;

import java.util.Objects;

public class DefaultString {
    static class Temp{
        private int a, b;

        public Temp(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Temp{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) {
        String nullDefault = "default string";
        System.out.println(Objects.toString(null, nullDefault));

        Temp temp = new Temp(2,3);
        String nullDefault2 = "default string";
        System.out.println(Objects.toString(temp, nullDefault2));
    }
}
