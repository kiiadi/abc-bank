package com.abc;

import static java.lang.Math.abs;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class Account {
	protected static final double DEFAULT_RATE = 0.001;
	protected static final int DAYS_IN_YEAR = 365;
	
	private List<Transaction> transactions = new ArrayList<Transaction>();

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else if (amount > sumTransactions()) {
			throw new IllegalArgumentException("insufficient funds");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}
	
	public double interestEarned() {
		double principal = 0.0;
		double interest = 0.0;
		Instant latestWidtdrawalOn = Instant.MIN;
		ListIterator<Transaction> i = transactions.listIterator();
		while(i.hasNext()) {
			Transaction t = i.next();
			Instant interestStartingOn = t.getTransactionDate().toInstant().truncatedTo(ChronoUnit.DAYS);
			principal += t.getAmount();
			
			if(t.getAmount() < 0) { latestWidtdrawalOn = interestStartingOn; }
			
			Instant interstUntil = DateProvider.getInstance().now().toInstant().truncatedTo(ChronoUnit.DAYS); //default: interest until today
			
			//continue over all transaction on the same day
			while(i.hasNext()) {
				Transaction t1 = i.next();
				Instant d1 = t1.getTransactionDate().toInstant().truncatedTo(ChronoUnit.DAYS);
				if(d1.isAfter(interestStartingOn)) {
					interstUntil = d1;
					i.previous();
					break;
				} else {
					if(t1.getAmount() < 0) { latestWidtdrawalOn = d1; }
					principal += t1.getAmount();
				}
			}
			
			interest += interestEarnedInPeriod(principal, interestStartingOn, interstUntil, latestWidtdrawalOn);
		}
		return interest;
	}
	
	public double interestEarnedInPeriod(double onAmount, Instant start, Instant end, Instant latestWidtdrawalOn) {
		return interestEarnedIn(ChronoUnit.DAYS.between(start, end), onAmount);
	}
	
	public abstract double interestEarnedIn(long days, double onAmount);
	
    public String statementForAccount() {
        String s = statementHeading() + "\n";

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : transactions) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	public abstract String statementHeading();
}
