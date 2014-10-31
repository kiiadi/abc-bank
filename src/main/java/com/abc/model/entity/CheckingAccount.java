package com.abc.model.entity;

import com.abc.impl.util.MathUtil;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class CheckingAccount extends Account {

    private static final double interestRate = 0.1;

    public CheckingAccount(String name) {
        super(name);
    }

    @Override
    public String getAccountType() {
        return "Checking Account";
    }

    @Override
    public BigDecimal calculateInterest() {
        return MathUtil.calculateInterestForOneDay(getBalance(), interestRate);
    }
}
