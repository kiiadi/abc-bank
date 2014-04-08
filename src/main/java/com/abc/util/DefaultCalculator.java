package com.abc.util;

import java.util.Date;

import com.abc.accounts.AccountBalance;
import com.abc.accounts.AccountType;
import com.abc.interests.InterestStrategyContext;

public class DefaultCalculator implements Calculator {

    private final InterestStrategyContext interestStrategies =  new InterestStrategyContext();

    @Override
    public AccountBalance calculate(AccountType accountType, AccountBalance accountBalance, Date date) {

    	Date timeStamp = accountBalance.getDate();
        int daysInBetween =  (int)( (date.getTime() - timeStamp.getTime()) / (1000 * 60 * 60 * 24));
        
        double balance = accountBalance.getBalance();
        double interest = accountBalance.getInterest();
        
        for (int i=1; i <= daysInBetween; i++) {
        	double newInterest = interestStrategies.calculateInterest(accountType, balance, 1);
        	balance += newInterest;
            interest += newInterest;
        }
        
        accountBalance.setBalance(balance);
        accountBalance.setInterest(interest);
        
        return accountBalance;
     }
}
