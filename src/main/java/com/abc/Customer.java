package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    public static final String EOL = "\n";

    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
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

    public BigDecimal totalInterestEarned() {
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            total = total.add(a.interestEarned());
        }
        return total;
    }

    /**
     * print statement for all customers
     * @return
     */
    public String getStatement() {
        String statement = "Statement for " + name + EOL;
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            statement += EOL + statementForAccount(a) + EOL;
            total = total.add(a.getBalance());
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account account) {
        StringBuilder s = new StringBuilder(account.getLabel()).append(EOL);

        //Now total up all the transactions
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : account.getTransactions()) {
            s.append("  " + (transaction.getAmount().compareTo(BigDecimal.ONE) < 0 ? "withdrawal" : "deposit") + " " + toDollars(transaction.getAmount()) + EOL);
            total = total.add(transaction.getAmount());
        }
        s.append("Total " + toDollars(total));
        return s.toString();
    }

    private String toDollars(BigDecimal d){
        return String.format("$%,.2f", abs(d.doubleValue()));
    }
}
