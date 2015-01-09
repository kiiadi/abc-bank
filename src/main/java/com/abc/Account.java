package com.abc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
	public enum Types {
		CHECKING("Checking", CheckingAccount.class),
		SAVINGS("Savings", SavingsAccount.class),
		MAXI_SAVINGS("Maxi Savings", MaxiSavingsAccount.class);
		
		private String label;
		public String getLabel() {return label;}
		Class<? extends Account> clazz;
		
		private Types(String label, Class<? extends Account> clazz) {
			this.label=label;
			this.clazz = clazz;
		}
		public Account newInstance() {
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
    private final Types accountType;
    public Types getAccountType() { return accountType; }
    
    protected List<Transaction> transactions;
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }
    
    private String customerId;
	public String getCustomerId() { return customerId;}
	public void setCustomerId(String customerId) { this.customerId = customerId; }

	protected Account(Types accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        validateAmount(amount);
        transactions.add(new Transaction(amount));
    }

	public void withdraw(double amount) {
		validateAmount(amount);
        transactions.add(new Transaction(-amount));
	}

    public abstract double interestEarned();
    
    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }
    
    public static void customerTransfer(Account from, Account to, double amount) {
    	validateAmount(amount);
    	if (!from.customerId.equals(to.customerId)) {
    		throw new IllegalArgumentException("Tranfer currently is only allowed between accounts of a same customer.");
    	}
    	from.withdraw(amount);
    	to.deposit(amount);
    }
	private static void validateAmount(double amount) {
		if (amount <= 0)
            throw new IllegalArgumentException("amount must be greater than zero");
	}
}

class CheckingAccount extends Account {
	public CheckingAccount() {
		super(Types.CHECKING);
	}

    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }
}

class SavingsAccount extends Account {
	private static final double firstTierMaxAmount = 1000; 
	private static final double firstTierRate = 0.001;
	private static final double secondTierRate = 0.002;
	
	public SavingsAccount() {
		super(Types.SAVINGS);
	}
	
    public double interestEarned() {
        double amount = sumTransactions();
        double interest = 0;
        if (amount > firstTierMaxAmount) {
        	interest += (amount - firstTierMaxAmount) * secondTierRate;
        	amount = firstTierMaxAmount;
        }
    	interest += amount * firstTierRate;
    	return interest;
    }
}


class MaxiSavingsAccount extends Account {
	private static final int recentWidthdrawDays = 10;
	private static final double reducedRateDueToRecentWithdraw = 0.001;
	private static final double maxsavingsRate = 0.05;
	public MaxiSavingsAccount() {
		super(Types.MAXI_SAVINGS);
	}
	
    public double interestEarned() {
    	double amount = sumTransactions();
    	if (hasWithdrawRecently(recentWidthdrawDays)) {
    		return amount * reducedRateDueToRecentWithdraw;
    	}
    	return amount * maxsavingsRate;
    }
    
    private boolean hasWithdrawRecently(int recentDays) {
    	LocalDate cutoff = DateProvider.getInstance().now().minusDays(recentDays);
    	for (Transaction transaction : transactions) {
    		if (!transaction.getDate().isBefore(cutoff)) {
    			return true;
    		}
    	}
    	return false;
    }

}