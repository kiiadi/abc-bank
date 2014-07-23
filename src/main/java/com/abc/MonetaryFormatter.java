package com.abc;

import static java.lang.Math.abs;

public class MonetaryFormatter {
    public static String toDollars(final double amount){
        return String.format("$%,.2f", abs(amount));
    }
}
