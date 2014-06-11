package com.abc;

import java.math.BigDecimal;

/**
 * A provider of an interest rate.
 */
public interface RateProvider {

    /**
     * Gets a rate.
     * @param balance the balance on which it is to be paid
     * @param daysSinceWithdrawal the day count since last withdrawal, or null if none have been made
     * @return a rate
     */
    BigDecimal getRate(BigDecimal balance, Integer daysSinceWithdrawal);

}
