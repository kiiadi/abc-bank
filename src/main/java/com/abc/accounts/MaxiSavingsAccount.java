package com.abc.accounts;


public class MaxiSavingsAccount extends Account {

    final static double REGULAR_RATE = 5/100.0;
    final static double PENALTY_RATE = 0.1/100.0;
    
    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        boolean hasPenalty = hasWithdrawlInLast10Days();
        
        if(hasPenalty) {
            return amount * PENALTY_RATE;
        } else {
            return amount * REGULAR_RATE;
        }
    }

  

    @Override
    public String accountTypeString() {
        return "Maxi Savings Account";
    }
}
