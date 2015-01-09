package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
	public enum Types {
		CHECKING(0, "Checking", CheckingAccount.class),
		SAVINGS(1, "Savings", SavingsAccount.class),
		MAXI_SAVINGS(2, "Maxi Savings", MaxiSavingsAccount.class);
		
		private int id;
		public int getId() { return id; }

		private String label;
		public String getLabel() {return label;}
		Class<? extends Account> clazz;
		
		private Types(int id, String label, Class<? extends Account> clazz) {
			this.id=id;
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
    private List<Transaction> transactions;
    public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	protected Account(Types accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        validateAmount(amount);
        transactions.add(new Transaction(amount));
    }

	private void validateAmount(double amount) {
		if (amount <= 0)
            throw new IllegalArgumentException("amount must be greater than zero");
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

    public Types getAccountType() {
        return accountType;
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
	private static double firstTierRate = 0.001;
	private static double secondTierRate = 0.002;
	
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
	private static final double firstTierMaxAmount = 1000; 
	private static double firstTierRate = 0.02;
	private static final double secondTierMaxAmount = 2000; 
	private static double secondTierRate = 0.05;
	private static double thirdTierRate = 0.1;
	
	public MaxiSavingsAccount() {
		super(Types.MAXI_SAVINGS);
	}
	
    public double interestEarned() {
        double amount = sumTransactions();
        double interest = 0;
        //be explicit with the rules for now, rather than alternative some looping approaches
        if (amount > secondTierMaxAmount) {
        	interest += (amount - secondTierMaxAmount) * thirdTierRate;
        	amount = secondTierMaxAmount;
        }
        if (amount > firstTierMaxAmount) {
        	interest += (amount - firstTierMaxAmount) * secondTierRate;
        	amount = firstTierMaxAmount;
        }
    	interest += amount * firstTierRate;
        return interest;
    }
}