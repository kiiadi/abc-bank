package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private static final long NO_WITHDRAWAL = -1;

    private final int accountType;
    private final int accountNumber;
    private Date dateInterestAccruedLast;
    private double totalInterestEarned;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.accountNumber = Banks.getLastAccountNumber() + 1;
        Banks.setLastAccountNumber(this.accountNumber);
        dateInterestAccruedLast = DateProvider.getInstance().now();
        totalInterestEarned = 0;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        long daysOfInterest = getDaysOfInterest();
        //long daysOfInterest = 365;
        double amount = sumTransactions();
        switch (accountType) {
            case CHECKING:
                return (amount * 0.001) * daysOfInterest / 365;
            case SAVINGS:
                if (amount <= 1000)
                    return (amount * 0.001) * daysOfInterest / 365;
                else
                    return (1000 * 0.001) * daysOfInterest / 365 + ((amount - 1000) * 0.002) * daysOfInterest / 365;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if(daysSinceLastWithdrawal() >= 10 || daysSinceLastWithdrawal() == NO_WITHDRAWAL) {
                    return (amount * 0.05) * daysOfInterest / 365;
                }
                else {
                    return (amount * 0.001) * daysOfInterest / 365;
                }
                /*if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount - 1000) * 0.05;
                return 70 + (amount - 2000) * 0.1;*/
            default:
                return 0;  //no interest earned for by default
        }
    }

    public double sumTransactions() {
        //TODO hard coding
        return checkIfTransactionsExist(true);
    }

    public int getAccountType() {
        return accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getTotalInterestEarned() {
        return totalInterestEarned;
    }

    public void setTotalInterestEarned(double totalInterestEarned) {
        this.totalInterestEarned = totalInterestEarned;
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.amount;
        return amount;
    }

    private long daysSinceLastWithdrawal() {
        long now = DateProvider.getInstance().now().getTime();
        long lastTran = NO_WITHDRAWAL ;
        for (Transaction t : transactions) {
            if (t.getAmount() < 0)
                lastTran = t.getTransactionDate().getTime();
        }

        return (lastTran == NO_WITHDRAWAL) ? lastTran : Math.abs((now-lastTran)/(1000*60*60*24));
    }

    private long getDaysOfInterest() {
        long d1 = DateProvider.getInstance().now().getTime();
        long d2 = dateInterestAccruedLast.getTime();

        return Math.abs((d1-d2)/(1000*60*60*24));
    }

    public void setDateInterestAccruedLast(Date dateInterestAccruedLast) {
        this.dateInterestAccruedLast = dateInterestAccruedLast;
    }
}
