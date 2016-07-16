package com.abc;

import java.math.BigDecimal;

/**
 * Created by dineshram on 2/28/15.
 */
public class SavingsAccount extends Account {

    private static final String ACCOUNT_NAME = "Savings Account";

    public SavingsAccount() {
        super();
    }

    @Override
    public BigDecimal interestEarned() {

        BigDecimal amount = sumTransactions();
        if(amount.compareTo(new BigDecimal(1000)) <=0 ) {
            return amount.multiply(new BigDecimal(0.001));
        }

        return amount.subtract(new BigDecimal(1000)).multiply(new BigDecimal(0.002)).add(new BigDecimal(1));
    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }
}
