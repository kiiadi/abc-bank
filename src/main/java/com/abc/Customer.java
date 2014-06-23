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
    
    public List<Account> getAccounts() {
    	return accounts;
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions() + a.interestEarned();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
			s.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit")
					.append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        
        Double interest = a.interestEarned();
        s.append("  Interest Earned ").append(toDollars(interest)).append("\n");
        	
        s.append("Total Balance ").append(toDollars(total + interest));
        
        return s.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    /**Transfer Money from Account a to Account b
     * This should be ideally wrapped in a transaction (like DB transaction) to make sure
     * Integrity
     * @param a
     * @param b
     * @param Amount
     */
    public void Transfer(Account a, Account b, double amount) {
    	if (!isValidAccount(a) || !isValidAccount(b))
    		throw new IllegalArgumentException("customer transfer invalid account");
    	try {
    		a.withdraw(amount, null);
    		b.deposit(amount, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new IllegalArgumentException("Transfer failed", e);
    	}
    }
    
    private boolean isValidAccount(Account account) {
    	for (Account userAccount : this.accounts) {
    		if (account.equals(userAccount))
    			return true;
    	}
    	return false;
    }
}
