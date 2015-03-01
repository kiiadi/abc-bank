package com.abc;

import java.math.BigDecimal;

/**
 * Created by dineshram on 2/28/15.
 */
public class CheckingAccount extends Account {

    private static final String ACCOUNT_NAME = "Checking Account";

    public CheckingAccount() {
        super();
    }

    @Override
    public BigDecimal interestEarned() {
        return sumTransactions().multiply(new BigDecimal(0.001));
    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }
}
