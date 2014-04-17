package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    /* Modified AccountType to be ENUM for reusability */
    private AccountType accountType;

    /* Transaction list should be private to promote encapsulation */
    private List<Transaction> transactions;

    /* Intrinsic locking (synchronized) preferred to Reentrant lock whose features (such as interruptibly or condition) are not needed. 
     * Different locks used to allow deposit & withdrawal to be executed concurrently (if needed); 
     * although same lock can be used (if deposit/withdrawal need to be mutually exclusive).
     */
     private Object depositLock = new Object();
     private Object withdrawLock = new Object();

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
		/* Ensure concurrency for a given account */
		synchronized (depositLock) {
            		transactions.add(new Transaction(amount));
		}
        }
    }

   public void withdraw(double amount) {
    	if (amount <= 0) {
        	throw new IllegalArgumentException("amount must be greater than zero");
    	} else {
	   /* Ensure concurrency for a given account */
	   synchronized (withdrawLock) { 
        	transactions.add(new Transaction(-amount));
	   }
    	}
   }

    public double interestEarned() {
        double amount = sumTransactions();

	/* Avoid multiple returns & streamline interest calculation */ 
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    amount = amount * 0.001;
                else
                    amount = 1 + ((amount-1000) * 0.002);
		break;

            case MAXI_SAVINGS:
                if (amount <= 1000)
                    amount = amount * 0.02;
                else if (amount <= 2000)
                    amount = 20 + ((amount-1000) * 0.05);
                else
		    amount = 70 + ((amount-2000) * 0.1);
		break;

            case CHECKING:
                amount = amount * 0.001;
		break;

	    default:
		//amount remains unchanged sans interest
        }

	  return amount;
    }

    public double sumTransactions() {
	/* No need to invoke superfluous checkIfTransactionsExist() */
	double amount = 0.0;
        for (Transaction transaction: transactions)
            amount += transaction.getAmount();
        return amount;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public List<Transaction> getTransactions() {
	return this.transactions;
    }

}
