package com.abc;

import java.util.ArrayList;
import java.util.List;

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
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    /*
     * Change String to StringBuilder
     */
    public String getStatement() {
    	StringBuilder statement=new StringBuilder();
        statement.append( "Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts) {
        	statement.append( "\n" + statementForAccount(a) + "\n");
            total += a.sumTransactions();
        }
        statement.append( "\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }

    /*
     * Move the AccountType Conversion to a Different class AccountTypes
     */
    private String statementForAccount(Account a) {
    	StringBuilder s=new StringBuilder();
    	s.append(AccountTypes.getAccountType(a.getAccountType()));
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
        	s.append( "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n");
            total += t.amount;
        }
        s.append( "Total " + toDollars(total));
        return s.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
