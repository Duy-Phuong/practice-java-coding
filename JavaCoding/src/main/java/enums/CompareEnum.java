package enums;

public class CompareEnum {

    static enum TestEnum {
        ONE,
        TWO,
        THREE
    }

    public static void main(String[] args) {

        int c = 0;

        // Both are technically correct. If you look at the source code for .equals(), it simply defers to ==.
        //I use ==, however, as that will be null safe.
        // https://stackoverflow.com/questions/1750435/comparing-java-enum-members-or-equals
        TestEnum value = null;

        if (TestEnum.ONE.equals(TestEnum.ONE)) {
            System.out.println("TestEnum.ONE.equals(TestEnum.TWO)");
        }
        if (TestEnum.ONE == TestEnum.ONE) {
            System.out.println("TestEnum.ONE. == (TestEnum.TWO)");
        }

        // Avoid NPT
        if (TestEnum.ONE == value) {
            System.out.println("Compare to null");
        }
    }

}
