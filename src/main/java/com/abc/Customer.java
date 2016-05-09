package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * The customer class will hold the name of the customer and the list of accounts belonging to the customer
 */

public class Customer {
    private String name;
    private List<Account> accounts;

    /**
     * Constructor to initialize the customer object by providing the name
     * @param name name of the customer
     */
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Getter method for the name of the customer
     * @return name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the list of accounts held by the customer
     * @return the list of accounts held by the customer
     */
    public List<Account> getAccounts() {
        return accounts;
    }


    /**
     * Opens the account specified by the account type - adds the account object into the list of accounts.
     * Assumption - One customer cannot hold more than one account type
     * @param accountType integer value of the type of account to be opened
     * @return the customer object after the account if opened
     * @throws IllegalArgumentException if the customer tries to open the same account type again
     */
    public Customer openAccount(int accountType) {
        Account account = AccountFactory.getAccount(accountType);
        if (accounts.contains(account)) {
            throw new IllegalArgumentException("Account already exists");
        }
        accounts.add(account);
        return this;
    }

    /**
     * Getter method for the number of accounts held by the customer
     * @return the number of accounts held by the customer
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Getter method to retrieve the account object by specifying the account type
     * @param accountType integer value of the type of account
     * @return the account object corresponding to the account type
     * @throws IllegalArgumentException if the account corresponding to the account type doesn't exist
     */
    public Account getAccountByType(int accountType) {
        for (Account acct : accounts) {
            if (acct.getAccountType() == accountType) {
                return acct;
            }
        }
        throw new IllegalArgumentException("Account does not exist");
    }


    /**
     * Deposit the amount into the account specified by the account type
     * @param amount the amount to be deposited
     * @param accountType the type of account into which the amount is to be deposited
     * @throws IllegalArgumentException if the amount to be deposited is negative
     */
    public void deposit(double amount, int accountType) {
        Account account = getAccountByType(accountType);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit failed. Amount must be greater than 0");
        }
        account.getTransactions().add(new Transaction(amount));
    }

    /**
     * Withdraw the amount from the account specified by the account type
     * @param amount the amount to be withdrawn
     * @param accountType the type of account from which the amount is to be withdrawn
     * @throws IllegalArgumentException if the amount to be withdrawn is negative or less than the balance in the account
     */
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

    /**
     * Calculates the total interest earned by the customer in all their accounts
     * Delegates to the respective account types to calculate the interest
     * @return the total interest earned
     */
    public double calculateTotalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.calculateInterest();
        return total;
    }

    /**
     * Transfer amount from source account specified by account type to the destination account specified by account type
     * This follows a two step transfer logic - withdraw from the source account and deposit to the destination account
     * @param amount the amount to be transferred
     * @param fromAccountType the source account specified by the account type
     * @param toAccountType the destination account specified by the account type
     * @throws IllegalArgumentException if the balance in the source account is less than the amount to be transferred
     */
    public void transfer(double amount, int fromAccountType, int toAccountType) {
        Account fromAccount = getAccountByType(fromAccountType);

        if (amount > fromAccount.getAccountBalance()) {
            throw new IllegalArgumentException("Cannot transfer between accounts. Transfer amount cannot be greater than balance");
        }
        this.withdraw(amount, fromAccountType);
        this.deposit(amount, toAccountType);
    }
}
