package com.abc;

import java.util.ArrayList;
import java.util.List;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public Customer openAccount(int accountType) {
        Account account = AccountFactory.getAccount(accountType);
        if (accounts.contains(account)) {
            throw new IllegalArgumentException("Account already exists");
        }
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public Account getAccountByType(int accountType) {
        for (Account acct : accounts) {
            if (acct.getAccountType() == accountType) {
                return acct;
            }
        }
        throw new IllegalArgumentException("Account does not exist");
    }


    public void deposit(double amount, int accountType) {
        Account account = getAccountByType(accountType);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit failed. Amount must be greater than 0");
        }
        account.getTransactions().add(new Transaction(amount));
    }

    public void withdraw(double amount, int accountType) {
        Account account = getAccountByType(accountType);
        double acctBalance = account.getAccountBalance();
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal failed. Amount must be greater than 0");
        }
        if (amount > acctBalance) {
            throw new IllegalArgumentException("Withdrawal failed. Cannot withdraw more than the account balance");
        }
        account.getTransactions().add(new Transaction(-amount));
    }

    public double calculateTotalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.calculateInterest();
        return total;
    }

    public void transfer(double amount, int fromAccountType, int toAccountType) {
        Account fromAccount = getAccountByType(fromAccountType);

        if (amount > fromAccount.getAccountBalance()) {
            throw new IllegalArgumentException("Cannot transfer between accounts. Transfer amount cannot be greater than balance");
        }
        this.withdraw(amount, fromAccountType);
        this.deposit(amount, toAccountType);
    }


}
