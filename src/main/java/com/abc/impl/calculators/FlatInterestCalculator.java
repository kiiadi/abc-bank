package com.abc.impl.calculators;

import com.abc.api.Account;
import com.abc.api.InterestCalculator;

import java.math.BigDecimal;

public class FlatInterestCalculator implements InterestCalculator {

    private BigDecimal rate;

    public FlatInterestCalculator(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public BigDecimal calculateInterest(Account account) {
        return null;
    }

    @Override
    public String toString() {
        return "FlatInterestCalculator{" +
                "rate=" + rate +
                '}';
    }
}
