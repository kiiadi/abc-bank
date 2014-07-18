package com.abc;

/**
 * Class represents the maxi-savings account implementation.
 */
public class MaxiSavingsAccount extends Account {

    private static final String ACCOUNT_NAME = "Maxi Savings Account";

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        boolean isRecentWithdrawal = withdrawalsInLast10days(getLastWithdrawalTransaction());
        if (isRecentWithdrawal) {
            return amount * 0.001;
        } else {
            return amount * 0.05;
        }
    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }

    /**
     * Find the latest transaction for the account when the funds were withdrawn.
     *
     * @return Transaction
     */
    private Transaction getLastWithdrawalTransaction() {
        Transaction last = null;
        for (Transaction t : getTransactions()) {
            // withdrawal transactions
            if (t.getAmount() < 0) {
                if (last == null) {
                    last = t;
                } else {
                    if (t.getTransactionTime() > last.getTransactionTime()) {
                        last = t;
                    }
                }
            }
        }
        return last;
    }

    private boolean withdrawalsInLast10days(Transaction t) {
        if (t != null) {
            // time from the last transaction
            long period = dateProvider.now() - t.getTransactionTime();
            return period < dateProvider.periodInDays(10);
        } else return false;
    }
}
