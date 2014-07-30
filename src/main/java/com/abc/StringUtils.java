package com.abc;


public class StringUtils {
    public static String pluralize(final String word, final int number) {
        if (number == 1) return word;
        return word + "s";
    }
}
