package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Account(int accountType) {
        this.accountType = accountType;
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

    public boolean isWithdrawnRecent(int dayLimit) {
        boolean recentWithdraw = false;

        // check if last withdrawal occurred within dayLimit
        for (Transaction transaction : transactions) {

            if (!transaction.isDeposit()) {
                long days = transaction.getWithdrawAge();
                if (days > 0 && days <= dayLimit) {
                    recentWithdraw = true;
                }
            }
        }
        return recentWithdraw;
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case SAVINGS:
                if (amount <= 1000) {
                    return amount * getDailyAccrueRate(0.001);
                } else {
                    return 1 + (amount - 1000) * getDailyAccrueRate(0.002);
                }

            case MAXI_SAVINGS:
                if (isWithdrawnRecent(10)) {
                    return amount * getDailyAccrueRate(0.001);
                } else {
                    return amount * getDailyAccrueRate(0.05);
                }
            default:
                return amount * getDailyAccrueRate(0.001);
        }
    }

    public double sumTransactions() {
        return checkIfTransactionsExist();
    }

    private double checkIfTransactionsExist() {
        double amount = 0.0;
        for (Transaction t : transactions) {
            amount += t.getAmount();
        }
        return amount;
    }

    private double getDailyAccrueRate(double rate) {
        double interest = 0.00;
        for (Transaction t : transactions) {
            if (t.isDeposit()) {
                long depositAge = t.getDepositAge();
                interest += Math.pow(1 + rate / 365, depositAge);
            }
        }

        interest = Double.parseDouble(String.format("%.5f%n", interest - 1));
        return interest;
    }

    public int getAccountType() {
        return accountType;
    }

}
