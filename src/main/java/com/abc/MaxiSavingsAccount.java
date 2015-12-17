package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Created by Yi on 12/16/15.
 */
class MaxiSavingsAccount extends Account{
    private InterestCalculator interestCalculatorWithDrawInTenDays;
    private InterestCalculator interestCalculator;

    public MaxiSavingsAccount(){
        super();
        ArrayList<BigDecimal> rateArray  = new ArrayList<BigDecimal>();
        ArrayList<BigDecimal> thresholdArray = new ArrayList<BigDecimal>();
        rateArray.add(new BigDecimal(0.001));
        thresholdArray.add(new BigDecimal(Double.MAX_VALUE));
        interestCalculatorWithDrawInTenDays = new AccurByDaySectionalRateStrategy(new SectionalRateStrategy(rateArray,thresholdArray));
        rateArray  = new ArrayList<BigDecimal>();
        rateArray.add(new BigDecimal(0.05));
        interestCalculator = new AccurByDaySectionalRateStrategy(new SectionalRateStrategy(rateArray,thresholdArray));

    }

    @Override
    public BigDecimal interestEarned() {
        if(checkWithdrawInTenDays()) {
            return interestCalculatorWithDrawInTenDays.calculateInterest(sumTransactions().setScale(2, RoundingMode.HALF_UP));
        } else {
            return interestCalculator.calculateInterest(sumTransactions().setScale(2, RoundingMode.HALF_UP));
        }
    }

    @Override
    public int getAccountType() {
        return MAXI_SAVINGS;
    }

    public boolean checkWithdrawInTenDays(){
        return this.transactions.stream().anyMatch(
                i-> (i.getAmount().compareTo(BigDecimal.ZERO)<0 )
                    &&
                    (i.getTransactionDate().toInstant().plus(10, ChronoUnit.DAYS).isAfter(DateProvider.getInstance().now().toInstant()))
                );
    }

    //For IOC Later
    public InterestCalculator getInterestCalculatorWithDrawInTenDays() {
        return interestCalculatorWithDrawInTenDays;
    }

    public void setInterestCalculatorWithDrawInTenDays(InterestCalculator interestCalculatorWithDrawInTenDays) {
        this.interestCalculatorWithDrawInTenDays = interestCalculatorWithDrawInTenDays;
    }

    public InterestCalculator getInterestCalculator() {
        return interestCalculator;
    }

    public void setInterestCalculator(InterestCalculator interestCalculator) {
        this.interestCalculator = interestCalculator;
    }
}
