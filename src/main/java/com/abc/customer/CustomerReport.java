package com.abc.customer;

import static java.lang.Math.abs;

import com.abc.accounts.Account;
import com.abc.accounts.Transaction;


/**
 * Contains static helper methods used for Customer reports
 *
 */
public class CustomerReport {

	public static String getStatement(Customer customer) {
        StringBuilder statement = new StringBuilder();
        statement.append("Statement for ").append(customer.getName()).append("\n");
        
        double total = 0.0;
        for (Account account : customer.getAccounts().values()) {
            statement.append("\n").append(statementForAccount(account)).append("\n");
            total += account.getBalance();
        }
        
        statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString();
    }
	
	private static String statementForAccount(Account account) {
        StringBuilder statement = new StringBuilder();
        
        // get account name from account type
        statement.append(account.getAccountType().getName()).append("\n");

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : account.getTransactions()) {
        	statement.append("  ")
        	         .append(transaction.getAmount() < 0 ? "withdrawal" : "deposit")
        	         .append(" ")
        	         .append(toDollars(transaction.getAmount()))
        	         .append("\n");
        	
            total += transaction.getAmount();
        }
        statement.append("Total ").append(toDollars(total));
        
        return statement.toString();
    }

    private static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
