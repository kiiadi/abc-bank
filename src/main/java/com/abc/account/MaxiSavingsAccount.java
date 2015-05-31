package com.abc.account;

import java.util.Calendar;
import java.util.Date;

import com.abc.transaction.DateProvider;
import com.abc.transaction.DefaultDateProvider;
import com.abc.transaction.Transaction;
import com.abc.transaction.WithdrawalTransaction;

public class MaxiSavingsAccount extends AbstractAccount {

    private final DateProvider dateProvider;

    public MaxiSavingsAccount(DateProvider dateProvider) {
        super("Maxi Savings");
        this.dateProvider = dateProvider;
    }

    public MaxiSavingsAccount() {
        this(new DefaultDateProvider());
    }

    public double interestEarned() {
        double rate = isAnyWithdrawalAfter(dateProvider.now(-10)) ? 0.001 : 0.05;
        return getBalance() * rate;
    }

    private boolean isAnyWithdrawalAfter(Date date) {
        for (Transaction transaction : getTransactions()) {
            if (transaction instanceof WithdrawalTransaction
                    && transaction.getDate().after(date)) {
                return true;
            }
        }
        return false;
    }
}
