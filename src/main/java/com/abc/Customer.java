package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * This class represents a Customer. With the instance of this class clients
 * will be able to create a new customer, add a new account, get statement
 * of accounts and get amount of interest earned for all the accounts.
 * 
 * @author Manish
 *
 */
public class Customer {
    private String name;
    private List<Account> accounts;

    /**
     * Constructs the customer object with the specified name.
     * @param name
     */
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Gets the name of this customer.
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a new account to the customer.
     * 
     * @param account
     * @return
     */
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    /**
     * Returns the number of accounts that this customer has.
     * 
     * @return
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Returns the total interest earned by this customer
     * @return
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts)
            total += account.interestEarned();
        return total;
    }

    /**
     * Returns the statement of all transactions performed by/for this customer.
     * @return
     */
    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        if(this.accounts.size()==0) {
        	return statement.append("No Accounts Found!").toString();
        }
        statement.append("Statement for ");
        statement.append(name);
        statement.append("\n");
        double total = 0.0;
        for (Account account : accounts) {
            statement.append("\n");
            statement.append(statementForAccount(account));
            statement.append("\n");
            total += account.sumTransactions();
        }
        statement.append("\nTotal In All Accounts ");
        statement.append(toDollars(total));
        return statement.toString();
    }

    /**
     * Returns the statement of a given account.
     * 
     * @param account
     * @return
     */
    private String statementForAccount(Account account) {
        StringBuilder statement = new StringBuilder(account.getAccountType().accountTypeDesc);
        statement.append("\n");

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : account.transactions) {
            statement.append("  ");
            statement.append(transaction.amount < 0 ? "withdrawal" : "deposit");
            statement.append(" ");
            statement.append(toDollars(transaction.amount));
            statement.append("\n");
            total += transaction.amount;
        }
        statement.append("Total ");
        statement.append(toDollars(total));
        return statement.toString();
    }

    /**
     * Returns a string value of amount with 2 decimal points
     * @param amount
     * @return
     */
    private String toDollars(double amount){
        return String.format("$%,.2f", abs(amount));
    }
}
