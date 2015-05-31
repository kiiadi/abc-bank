package com.abc.account;

import java.util.Date;

import com.abc.transaction.DateProvider;
import com.abc.transaction.DefaultDateProvider;
import com.abc.transaction.Transaction;
import com.abc.transaction.WithdrawalTransaction;

public class MaxiSavingsAccount extends AbstractAccount {

    private static final InterestCalculator BONUS_INTEREST = new InterestCalculator(5.0);
    private static final InterestCalculator PENALTY_INTEREST = new InterestCalculator(0.1);

    private final DateProvider dateProvider;

    public MaxiSavingsAccount(DateProvider dateProvider) {
        super("Maxi Savings");
        this.dateProvider = dateProvider;
    }

    public MaxiSavingsAccount() {
        this(new DefaultDateProvider());
    }

    public double interestEarned() {
        InterestCalculator interest = isAnyWithdrawalAfter(dateProvider.now(-10)) ? PENALTY_INTEREST : BONUS_INTEREST;
        return interest.calculate(getBalance());
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
