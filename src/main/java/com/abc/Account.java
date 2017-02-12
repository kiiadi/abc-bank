package com.abc;

import java.util.LinkedList;
import java.util.List;

import static com.abc.AccountType.*;

public class Account {

    static class Pair {
        double amount, rate;

        Pair(double a, double r) {
            amount = a;
            rate = r;
        }
    }

    private final Pair[] interestTable;
    private final AccountType accountType;

    public List<Transaction> transactions;

    private Account(AccountType accountType, Pair... pairs) {
        this.accountType = accountType;
        this.transactions = new LinkedList<>();
        this.interestTable = pairs;
    }

    /*
     An alternative could be an Accounts hierarchy, but the solution based on single constructor and unlimited configurations is more flexible.
     Keeping factory methods in one place makes them easy to read and compare.
      */
    public static Account newChecking() {
        return new Account(CHECKING, new Pair(0, 0.001));
    }

    public static Account newSavings() {
        return new Account(SAVINGS, new Pair(0, 0.001), new Pair(1000, 0.002));
    }

    public static Account newMaxiSavings() {
        return new Account(MAXI_SAVINGS, new Pair(0, 0.02), new Pair(1000, 0.05), new Pair(2000, 0.10));
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
        double balance = sumTransactions();
        double interest = 0;
        for (int i = interestTable.length - 1; i > 0; i--) {
            if (balance > interestTable[i].amount) {
                interest += (balance - interestTable[i].amount) * interestTable[i].rate;
                balance = interestTable[i].amount;
            }
        }
        interest += balance * interestTable[0].rate;
        return interest;
    }

    public double sumTransactions() {
        return transactions.stream().mapToDouble(x -> x.amount).sum();
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
