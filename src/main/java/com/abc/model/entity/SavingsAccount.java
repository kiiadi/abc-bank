package com.abc.model.entity;

import com.abc.impl.util.MathUtil;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class SavingsAccount extends Account {

    private static final double firstLayerInterestRate = 0.1;
    private static final BigDecimal threshold = new BigDecimal("1000");
    private static final double secondLayerInterestRate = 0.2;

    public SavingsAccount(String name) {
        super(name);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public BigDecimal calculateInterest() {
        if(getBalance().compareTo(threshold) > 0) {
            //there are two rates used, one for amount below threshold, one for above
            BigDecimal firstPartOfInterest = MathUtil.calculateInterestForOneDay(threshold,firstLayerInterestRate);
            BigDecimal amountOverThreshold = getBalance().subtract(threshold);
            BigDecimal secondPartOfInterest = MathUtil.calculateInterestForOneDay(amountOverThreshold,secondLayerInterestRate);
            return firstPartOfInterest.add(secondPartOfInterest);
        } else {
            //when the balance is less than 1000 it's as simple as this
            return MathUtil.calculateInterestForOneDay(getBalance(), firstLayerInterestRate);
        }
    }

}
