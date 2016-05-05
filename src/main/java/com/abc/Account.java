package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public abstract double calculateInterest();

    public double getAccountBalance() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public Transaction getLastWithdrawalTransaction() {
        int index = transactions.size() - 1;
        while (index >= 0) {
            if (transactions.get(index).getAmount() < 0) {
                return transactions.get(index);
            }
            index--;
        }
        return null;
    }

    public int getAccountType() {
        return accountType;
    }

}
