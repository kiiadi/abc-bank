package com.abc.bank.util;

import static java.lang.Math.abs;

public class ConversionUtil {
    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
}
