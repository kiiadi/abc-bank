package com.abc.service;

import static com.abc.utils.Utils.toDollars;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.abc.domain.Transaction;

public class TransactionService {
	
	public static boolean withdrawl(final Transaction transaction) {
		return transaction.getAmount() < 0;
	}
	
	public static boolean within10days(final Transaction transaction) {
		Date transactionDate = transaction.getTransactionDate();	    
	    int gap = Days.daysBetween(new DateTime(transactionDate).withTimeAtStartOfDay() , new DateTime().withTimeAtStartOfDay() ).getDays();
	    if(gap > 10) {
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	public String getTransactionDetails(Transaction transaction) {
		double amount = transaction.getAmount();
    	return "  " + (amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(amount);
    }
}
