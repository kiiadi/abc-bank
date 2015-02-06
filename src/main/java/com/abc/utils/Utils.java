package com.abc.utils;

import static java.lang.Math.abs;

public class Utils {
	
	public static String EOL = System.getProperty("line.separator");
	
	public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
