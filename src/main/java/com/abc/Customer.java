package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.abs;

/**
 * The class implements behavior of a bank customer.<br>
 * The customer can have number of various bank accounts, 
 * manipulate with them and provide total balance statement.
 */
public class Customer {
    private final String name;
    private List<Account> accounts;

    /**
     * Create Customer instance with given name.
     */
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Return customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Add a new account.
     */
    public void openAccount(Account account) {
        accounts.add(account);
    }
    
    /**
     * Transfer amount between two accounts.
     */
    public void transfer(double amount, Account fromAccount, Account toAccount) {
    	if (amount <= 0)
    		throw new IllegalArgumentException("Amount must be greater than zero");
    	
    	double fromBallance = fromAccount.sumTransactions();
    	if (fromBallance < amount)
    		throw new IllegalArgumentException("Account balance is not allowed to overdraw");
    	
    	fromAccount.withdraw(amount);
    	toAccount.deposit(amount);    	
    }

    /**
     * Return number of accounts for the customer.
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Calculate total interest earned by customer until now.
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }
    
    /**
     * Calculate total interest earned by customer given date.
     */
    public double totalInterestEarned(Date date) {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned(date);
        return total;
    }

    /**
     * Return pretty representation of customer info.
     */
    public String getStatement() {
    	StringBuilder sb = new StringBuilder("Statement for ");
    	sb.append(name);
    	sb.append("\n\n");
    	
        double total = 0.0;
        for (Account a : accounts) {
        	sb.append(a.getStatement());
        	sb.append("\n");
            total += a.sumTransactions();
        }
        sb.append("Total In All Accounts ");
        sb.append(toDollars(total));
        sb.append("\n");
        return sb.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    @Override
    public String toString() {
    	return String.format("%s (%d accopunts(s))\n", name, accounts.size() );
    }
    
}
