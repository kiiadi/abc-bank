package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the account for a customer. Accounts can be of 
 * 3 types - CHECKING, SAVINGS OR MAXI_SAVINGS.
 * Using instance of this class transactions such as deposit and withdraw 
 * can be performed for a customer. Also, 
 * @author Manish
 *
 */
public class Account {

	enum AccountType {
		CHECKING("Checking Account"), SAVINGS("Saving Account"), MAXI_SAVINGS("Maxi Savings Account");
		String accountTypeDesc;
		private AccountType(String accountTypeDesc) {
			this.accountTypeDesc=accountTypeDesc;
		}
	};

    private final AccountType accountType;
    public List<Transaction> transactions;

    /**
     * Constructs the account object with account type as parameter
     * @param accountType
     */
    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * Adds a deposit transaction to transactions list for this account,
     * with an amount greater than zero.
     * @param amount
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    /**
     * Adds a withdraw transaction to transactions list for this account,
     * with an amount greater than zero.
     * @param amount
     */
	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount));
	    }
	}

	/**
	 * For this account this method returns the 
	 * @return
	 */
    public double interestEarned() {
        double amount = sumTransactions();
        if(amount==0.0d) {
        	return 0.0d;
        }
        switch(accountType){
            case SAVINGS:
            	return (amount <= 1000) ? 
            			(amount * 0.001) : 
            			(1 + (amount-1000) * 0.002);
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    /**
     * This method returns sum of all the transactions for this account.
     * @return
     */
    public double sumTransactions() {
    	if(transactions.size()==0) {
    		return 0.0d;
    	}
        double amount = 0.0d;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    /**
     * Returns the account type of this account.
     * @return
     */
    public AccountType getAccountType() {
        return accountType;
    }

}
