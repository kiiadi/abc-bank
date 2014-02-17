package com.abc.bank.account;

import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.abc.bank.exception.InsufficuentFundsException;
import com.abc.bank.util.DateProvider;

/**
 * MaxiSavingsAccounts have an
 * interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
 */
class MaxiSavingsAccount extends Account{

    private Date lastWithdrawal = null;

    @Override
    public double interestEarned() {
        double amount = getBalance();
        if (null!=lastWithdrawal && Days.daysBetween(new LocalDate(lastWithdrawal),
                new LocalDate(DateProvider.getInstance().now())).getDays()<=10)
            return amount * 0.001;
        else{
            return amount * 0.05;

        }
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.maxiSavings;
    }

    @Override
    public double withdraw(double amount) throws InsufficuentFundsException{
        double amt = super.withdraw(amount);
        lastWithdrawal = DateProvider.getInstance().now();
        return amt;
    }
}
