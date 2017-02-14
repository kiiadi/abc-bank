package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * 
 * @author Alex Gordon
 * Customer class
 *
 */
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

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Customer openAccount(Account account) {
		if (this.accounts.contains(account)) {
			throw new IllegalArgumentException(Constants.DUPLICATE_ACCOUNT_ERROR);
		} else {
			this.accounts.add(account);
			return this;
		}
	}

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarnedToday() {
        double total = 0.0;
        for (Account a : accounts)
            total += a.interestEarnedToday();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + this.name + "\n";
        double totalBalance = 0.0;
        for (Account a : accounts) {
            statement += a.statement() + "\n";
            totalBalance += a.getBalance();
        }
        statement += "Total Balance In All Accounts: " + Constants.toDollars(totalBalance);
        return statement;
    }
    
    public String summary() {
    	return "Customer Name: " + this.name + ", Number Of Accounts: " + this.getNumberOfAccounts();
    }
    
    public void transfer(Account fromAccount, Account toAccount, double amount) {
    	if (fromAccount == null) {
			throw new IllegalArgumentException(Constants.FROM_ACCOUNT_ERROR);
    	}
    	if (toAccount == null) {
			throw new IllegalArgumentException(Constants.TO_ACCOUNT_ERROR);
    	}
    	if (!this.accounts.contains(fromAccount)) {
			throw new IllegalArgumentException(Constants.FROM_ACCOUNT_ERROR);
    	}
    	if (!this.accounts.contains(toAccount)) {
			throw new IllegalArgumentException(Constants.TO_ACCOUNT_ERROR);
    	}
    	if (fromAccount.equals(toAccount)) {
			throw new IllegalArgumentException(Constants.FROM_TO_ACCOUNT_ERROR);
    	}
    	if (amount < 0.0) {
			throw new IllegalArgumentException(Constants.NEGATIVE_AMOUNT_ERROR);
    	}
    	fromAccount.withdraw(amount);
    	toAccount.deposit(amount);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}