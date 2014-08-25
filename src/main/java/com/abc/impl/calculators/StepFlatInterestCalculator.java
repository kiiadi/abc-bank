package com.abc.impl.calculators;

import com.abc.api.Account;
import com.abc.api.InterestCalculator;

import java.math.BigDecimal;

public class StepFlatInterestCalculator implements InterestCalculator {

    private BigDecimal baseRate;
    private BigDecimal threshold;
    private BigDecimal stepRate;


    public StepFlatInterestCalculator(BigDecimal baseRate, BigDecimal threshold, BigDecimal stepRate) {
        this.baseRate = baseRate;
        this.threshold = threshold;
        this.stepRate = stepRate;
    }

    @Override
    public BigDecimal calculateInterest(Account account) {
        return null;
    }

    @Override
    public String toString() {
        return "StepFlatInterestCalculator{" +
                "baseRate=" + baseRate +
                ", threshold=" + threshold +
                ", stepRate=" + stepRate +
                '}';
    }
}
