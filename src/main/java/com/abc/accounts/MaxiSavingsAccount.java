package com.abc.accounts;


public class MaxiSavingsAccount extends Account {

    final static double REGULAR_RATE = 5/100.0;
    final static double PENALTY_RATE = 0.1/100.0;
    
    
    @Override
    public double getAnnualRateForBalance(double balance) {
        if(hasWithdrawlInLast10Days()) {
            return PENALTY_RATE;
        } else {
            return REGULAR_RATE;
        }
    }
  

    @Override
    public String accountTypeString() {
        return "Maxi Savings Account";
    }
}
