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

    public Customer(String name, Account... accounts) {
        this(name);
        for (Account toAdd : accounts){
            openAccount(toAdd);
        }
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account.AccountType accountType) {
        accounts.add(new Account(accountType));
        return this;
    }

    public Customer openAccount(Account toOpen) {
        if (toOpen!=null){
            accounts.add(toOpen);
        }
        return this;
    }

    public Customer openAccount(Account.AccountType accountType, double initialDeposit){
        accounts.add(new Account(accountType, initialDeposit));
        return this;
    }

    public int getNumberOfAccounts() {
        if(accounts != null){
            return accounts.size();
        }
        return 0;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void transfer(Account fromAccount, Account toAccount, double amount){
        if(!fromAccount.equals(toAccount)){
            toAccount.transferFrom(fromAccount, amount);
        }
        throw new IllegalArgumentException("Accounts must be distinct from each other");
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.balance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account account) {
        StringBuilder output = new StringBuilder(account.accountType().getDesc()).append("\n");
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : account.transactions()) {
            output.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }

        output.append("Total ").append(toDollars(total));
        return output.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
