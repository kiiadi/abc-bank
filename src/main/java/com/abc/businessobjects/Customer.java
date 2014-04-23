package com.abc.businessobjects;

import com.abc.process.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.abc.businessobjects.AccountType.CHECKING;
import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts) {
            total += a.interestEarned();
        }
        return total;
    }

    public String getStatement() {
        String statement;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    public String getStatementWithInterestPaid() {
        String statement;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.interestEarned();
        }
        statement += "\nTotal Interest In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        StringBuilder statement = new StringBuilder();

        //Translate to pretty account type
        switch (a.getAccountType()) {
            case CHECKING:
                statement.append("Checking Account");
                break;
            case SAVINGS:
                statement.append("Savings Account");
                break;
            case MAXI_SAVINGS:
                statement.append("Maxi Savings Account");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            statement.append("\n  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ")
                    .append(toDollars(t.amount));
            total += t.amount;
        }
        statement.append("\nTotal " + toDollars(total));
        return statement.toString();
    }

    private String toDollars(double amount) {
        return String.format("$%,.2f", abs(amount));
    }

    public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        if (amount <= fromAccount.sumTransactions()) {
            toAccount.deposit(amount);
            fromAccount.withdraw(amount);
            return true;
        }
        return false;
    }
}
