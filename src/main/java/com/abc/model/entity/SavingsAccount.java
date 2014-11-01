package com.abc.model.entity;

import com.abc.impl.util.MathUtil;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class SavingsAccount extends Account {

    private static final double FIRST_LAYER_INTEREST_RATE = 0.1;
    private static final BigDecimal THRESHOLD = new BigDecimal("1000");
    private static final double SECOND_LAYER_INTEREST_RATE = 0.2;

    public SavingsAccount(String name) {
        super(name);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public BigDecimal calculateInterest() {
        if(getBalance().compareTo(THRESHOLD) > 0) {
            return calculateInterestUsingBothInterestRates();
        } else {
            return calculateInterestWithOneInterestRateOnly();
        }
    }

    private BigDecimal calculateInterestUsingBothInterestRates() {
        BigDecimal firstPartOfInterest = MathUtil.calculateInterestForOneDay(THRESHOLD, FIRST_LAYER_INTEREST_RATE);
        BigDecimal amountOverThreshold = getBalance().subtract(THRESHOLD);
        BigDecimal secondPartOfInterest = MathUtil.calculateInterestForOneDay(amountOverThreshold, SECOND_LAYER_INTEREST_RATE);
        return firstPartOfInterest.add(secondPartOfInterest);
    }

    private BigDecimal calculateInterestWithOneInterestRateOnly() {
        return MathUtil.calculateInterestForOneDay(getBalance(), FIRST_LAYER_INTEREST_RATE);
    }
}
