package com.abc.util;

import static java.lang.Math.abs;

public class ReportFormatterHelper {

    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    public static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
}
