package com.abc;

import java.util.LinkedList;

import static com.abc.AccountType.*;
import static java.lang.Math.abs;

public class Account {

    Transaction lastTransaction() {
        return transactions.getLast() ;
    }
    void rollback(Transaction t1) {
        if(lastTransaction()!=t1 && transactions.size()>0) transactions.removeLast() ;
    }

    static class Pair {
        double amount, rate;

        Pair(double a, double r) {
            amount = a;
            rate = r;
        }
    }

    private final Pair[] interestTable;
    private final AccountType accountType;
    protected LinkedList<Transaction> transactions;

    protected Account(AccountType accountType, Pair... pairs) {
        this.accountType = accountType;
        this.transactions = new LinkedList<>();     // we don't need access transaction by index and we don't want delays on ArrayList rebuild, hence LinkedList
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
        return new Account(MAXI_SAVINGS, new Pair(0, 0.02), new Pair(1000, 0.05), new Pair(2000, 0.10));  // initial implementation
    }

    // created separate factory method to demonstrate both versions of MAXI_SAVINGS work. Also adding new designated tests for thsi case
    public static Account newMaxiSavings5Flat() {
        MaxiSavingsAccount account = new MaxiSavingsAccount(MAXI_SAVINGS, new Pair(0, 0.05));
        account.setAlternativeInterestTable(new Pair(0,0.001));
        return account ;
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
        return interestEarned(interestTable) ;
    }

    protected double interestEarned(Pair[] interestTable) {
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

    String statementForAccount() {
        StringBuilder s = new StringBuilder(getAccountType().getReadableForm()).append(System.lineSeparator());

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : transactions) {
            s.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(String.format(" $%,.2f", abs(t.amount))).append(System.lineSeparator());
            total += t.amount;
        }
        s.append("Total ").append(String.format("$%,.2f", total));
        return s.toString();
    }

}
