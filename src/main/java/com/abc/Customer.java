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

    //customer is able to deposit funds
    public void deposit(double amount, int accountNumber) {
        boolean accountFound = false;
        for (Account a : accounts) { //TODO: improve performance using Map instead
            if (a.getAccountNumber() == accountNumber) {
                accountFound = true;
                a.deposit(amount);
            }
        }
        if (!accountFound) throw new IllegalArgumentException("Account number not found: " + accountNumber);
    }

    //customer is able to withdraw funds
    public void withdraw(double amount, int accountNumber) {
        boolean accountFound = false;
        for (Account a : accounts) { //TODO: improve performance using Map instead
            if (a.getAccountNumber() == accountNumber) {
                accountFound = true;
                a.withdraw(amount);
            }
        }
        if (!accountFound) throw new IllegalArgumentException("Account number not found: " + accountNumber);
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

    public void transferBetweenAccounts(double amount, int fromAccountNumber, int toAccountNumber) {
        boolean accountFound = false;

        //withdraw
        this.withdraw(amount, fromAccountNumber);

        //deposit
        this.deposit(amount, toAccountNumber);

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

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
