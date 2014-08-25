package com.abc.impl.calculators;

import com.abc.api.Account;
import com.abc.api.InterestCalculator;

import java.math.BigDecimal;

public class TwoStepFlatInterestCalculator implements InterestCalculator {

    private BigDecimal baseRate;
    private BigDecimal baseThreshold;
    private BigDecimal stepRate;
    private BigDecimal stepThreshold;
    private BigDecimal stepTwoRate;

    public TwoStepFlatInterestCalculator(BigDecimal baseRate, BigDecimal baseThreshold, BigDecimal stepRate, BigDecimal stepThreshold, BigDecimal stepTwoRate) {
        this.baseRate = baseRate;
        this.baseThreshold = baseThreshold;
        this.stepRate = stepRate;
        this.stepThreshold = stepThreshold;
        this.stepTwoRate = stepTwoRate;
    }

    @Override
    public BigDecimal calculateInterest(Account account) {
        return null;
    }

    @Override
    public String toString() {
        return "TwoStepFlatInterestCalculator{" +
                "baseRate=" + baseRate +
                ", baseThreshold=" + baseThreshold +
                ", stepRate=" + stepRate +
                ", stepThreshold=" + stepThreshold +
                ", stepTwoRate=" + stepTwoRate +
                '}';
    }
}

