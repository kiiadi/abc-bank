/**********
 * Customer.java
 * 
 * A customer is an object that contains
 * a list of accounts
 * 
 * @author Martin Aydin
 */

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

    // Extra method to facilitate transfer between two accounts
    // Since there is no unique ID that determines an account
    // I've implemented it by the array position of the accounts
    // Not very user friendly, but it works for the purposes of
    // This exercise.  It will return true for successful transfer
    // And false for failed transfer.
    public boolean transferFunds(int from, int to, int amount) {
    	
    	// Make sure the from/to id's are valid, as well as the amount
    	if (from < getNumberOfAccounts() && to < getNumberOfAccounts() && amount > 0) {
    		
    		// There is no requirement for a positive account balance
    		// So no checks are being performed
    		accounts[from].withdraw(amount);
    		accounts[to].deposit(amount);
    		return true;
    	}
    	else return false;
    }
    
    // Returns a string containing the statement for all accounts
    // This method replaces two of the old methods used together 
    // To create the statement.
    public String getStatement() {
        String statement = "";
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += a.getAccountName() + a.listTransactions() + 
            		"Account total: " + Utils.toDollars(a.sumTransactions) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + Utils.toDollars(total);
        return statement;
    }
}
