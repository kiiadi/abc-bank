package com.abc.utils;

import static java.lang.Math.abs;

public class BankUtils {
	
    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

}
