package booleantest;

import org.apache.commons.lang3.BooleanUtils;

public class ToBooleanDefaultIfNullExample {
    public static void main(String[] args) {
        Boolean bool = Boolean.TRUE;
        boolean valueIfNull = false;
        boolean result = BooleanUtils.toBooleanDefaultIfNull(bool, valueIfNull);
        System.out.printf("BooleanUtils.toBooleanDefaultIfNull(%s, %s) = %s\n", bool, valueIfNull, result);

        bool = null;
        valueIfNull = false;
        result = BooleanUtils.toBooleanDefaultIfNull(bool, valueIfNull);
        System.out.printf("BooleanUtils.toBooleanDefaultIfNull(%s, %s) = %s\n", bool, valueIfNull, result);

    }
}
