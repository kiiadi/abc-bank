package com.abc;

import java.math.BigDecimal;

/**
 * Formatting utilities.
 */
public final class StringUtils {

    private StringUtils() {}

    /**
     *
     * Make sure correct plural of qualifier is created based on the number passed in.
     * If number passed in is 1 just return the qualifier otherwise add an 's' at the end
     * @param number the number to format into the string
     * @param qualifier the number qualifier to append to the string
     * @return the string
     */
    public static String getNumberDisplayString(int number, String qualifier) {
        String qualifierAsSingleOrPlural = number == 1 ? qualifier : qualifier + "s";
        return number + " " + qualifierAsSingleOrPlural;
    }

    /**
     * Formats a number as a monetary amount in dollars.
     * @param amount the amount to format
     * @return the string
     */
    public static String getDollarString(BigDecimal amount){
        return String.format("$%,.2f", amount.abs());
    }

}
