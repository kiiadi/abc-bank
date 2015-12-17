package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Yi on 12/16/15.
 */
class CheckingAccount extends Account{
    private InterestCalculator interestCalculator;

    public CheckingAccount(){
        super();
        ArrayList<BigDecimal> rateArray  = new ArrayList<BigDecimal>();
        ArrayList<BigDecimal> thresholdArray = new ArrayList<BigDecimal>();
        rateArray.add(new BigDecimal(0.001));
        thresholdArray.add(new BigDecimal(Double.MAX_VALUE));
        interestCalculator = new AccurByDaySectionalRateStrategy(new SectionalRateStrategy(rateArray,thresholdArray));
    }

    @Override
    public BigDecimal interestEarned() {
        return interestCalculator.calculateInterest(sumTransactions().setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public int getAccountType() {
        return CHECKING;
    }

    //For IOC Later
    public InterestCalculator getInterestCalculator() {
        return interestCalculator;
    }

    public void setInterestCalculator(InterestCalculator interestCalculator) {
        this.interestCalculator = interestCalculator;
    }
}
