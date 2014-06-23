package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, date));
        }
    }
   
    public void withdraw(double amount, Date date) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("amount must be greater than zero");
    	} else if (sumTransactions() - amount < 0) {
    		throw new IllegalArgumentException("amount must be less than current balance");
    	} else {
    		transactions.add(new Transaction(-amount, date));
    	}
    }
   
    public double interestEarned() {
    	double totalInterest = 0.0;
    	double effectiveAmount = 0.0;
    	Date startDate = null, endDate = null;
    	final int size = transactions.size();
    	final Date now = DateProvider.getInstance().now();
    	//MAXI-Saving account has different rate based on last withdraw time
    	double overrideRate = (accountType == MAXI_SAVINGS) ? getMAXInterestRate() : 0.0;
    	for (int i = 0 ; i < size; i ++) {
    		Transaction t = transactions.get(i);
    		effectiveAmount += t.amount;
    		//Assuming transaction happened as end of day
    		startDate = t.getTransactionDate();
    		endDate = (i + 1 < size)?transactions.get(i + 1).getTransactionDate():now;
    		long duration = DateProvider.getInstance().dateDiff(startDate, endDate);
			totalInterest += interestEarnedForTransaction(effectiveAmount,
					duration,
					overrideRate);
    	}
    	return totalInterest;
    }
    /**
     * @return 5% if there is no withdraw in past 10 days, otherwise 0.1%
     */
    private double getMAXInterestRate() {
    	if (accountType == MAXI_SAVINGS) {
    		if (transactions.size() != 0) {
    			for (int j = transactions.size() - 1; j >= 0; j --) {
    				Transaction t = transactions.get(j);
    				if (DateProvider.getInstance().dateDiff(t.getTransactionDate(), null) <= 10) {
    					if (t.amount < 0) {
    						return 0.001; 
    					}
    				} else {
    					break;
    				}
    			}
    		}
    		return 0.05;
    	}
    	return 0.0;
    }
    private double interestEarnedForTransaction(double effectiveAmount, long duration, double overrideRate) {
    	double amount = effectiveAmount;
    	switch(accountType){
    	case SAVINGS:
    		if (amount <= 1000)
    			return amount * getDailyRateForPeriod(duration, 0.001);
    		else
    			return 1000 * getDailyRateForPeriod(duration, 0.001)
    					+ (amount-1000) * getDailyRateForPeriod(duration, 0.002);
    		//        case SUPER_SAVINGS:
    			//            if (amount <= 4000)
    		//                return 20;
    	case MAXI_SAVINGS:
    			return amount * getDailyRateForPeriod(duration, overrideRate);
    	default:
    		return amount * getDailyRateForPeriod(duration, 0.001);
    	}
    }

    private static double getDailyRateForPeriod(long duration, double annAccrueRate) {
    	return (duration * annAccrueRate /365.0);
    }
    
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }
    
    public int getAccountType() {
        return accountType;
    }

}
