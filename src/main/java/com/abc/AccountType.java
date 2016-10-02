package com.abc;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public enum AccountType {

	Checking
	{
		@Override
		public double interest(double amount, int days, int lastWithdrawDays) {
			
			return amount * 0.001 * days / DAYS_IN_YEAR;
		}
	},	
	
	Savings
	{
		@Override
		public double interest(double amount, int days, int lastWithdrawDays) {
			
			return amount * (amount > 1000.0 ? 0.002 : 0.001) * days / DAYS_IN_YEAR;
		}
	},
	
	Max_Savings
	{
		@Override
		public double interest(double amount, int days, int lastWithdrawDays) {  
			
			
			if(lastWithdrawDays > 10) {
				
				int daysInHighRate = Math.min(lastWithdrawDays - 10, days);
				
//				System.out.println(amount + " : " + days + " : " + lastWithdrawDays + " : " + daysInHighRate);
				return amount * ((days - daysInHighRate) * 0.001 + daysInHighRate * 0.05) / DAYS_IN_YEAR;
				
			}else {
				
//				System.out.println(amount + " : " + days + " : " + lastWithdrawDays + " : " + 0);
				return amount * 0.001 * days / DAYS_IN_YEAR;
			}
		}
	};
	
	private static int DAYS_IN_YEAR = 365;
	
	abstract double interest(double amount, int days, int lastWithdrawDays);
	
	public double interestEarned(List<Transaction> transactions) {
		
		sortTransactions(transactions);
		double amount = 0.0;
		double interest = 0.0;
		Date prevDate = null;
		Date lastWithDrawDate = null;
		for (Transaction transaction : transactions) {
			
			if(prevDate != null) {
				
				interest += interest(amount, prevDate, transaction.getTransactionDate(), lastWithDrawDate);
			}
			
			prevDate = transaction.getTransactionDate();
			amount += transaction.getAmount();
			if(transaction.getAmount() < 0.0) {
				
				lastWithDrawDate = transaction.getTransactionDate();
			}
		}
		
		if(prevDate != null) {
			
			interest += interest(amount, prevDate, DateProvider.getInstance().now(), lastWithDrawDate);
		}
		
		return interest;
	}
	
	private double interest(double amount, Date prevDate, Date today, Date lastWithDrawDate) {
		
		int lastWithdrawDays = lastWithDrawDate == null ? Integer.MAX_VALUE : DateProvider.days(lastWithDrawDate, today);
		int days = DateProvider.days(prevDate, today);
		return interest(amount, days, lastWithdrawDays) ;
	}
	
	

	private void sortTransactions(List<Transaction> transactions) {
		
		Collections.sort(transactions, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction o1, Transaction o2) {
				
				return o1.getTransactionDate().compareTo(o2.getTransactionDate());
			}
		});
	}
}
