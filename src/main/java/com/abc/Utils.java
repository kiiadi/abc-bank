package com.abc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Utils {
	
	public static void checkIfAmountLessOrEqualToZero(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) == -1) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}
	}
	
    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
	public static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
	
	public static void checkIfTransactionsExist(List<Transaction> transactions) {
		if(transactions.isEmpty()){
			throw new RuntimeException(
					"Transaction List must not be empty");
		}
		
	}
	
	public static void checkIfBalanceIsLessThanAmount(BigDecimal balance, BigDecimal amount) {
		if (balance.compareTo(amount) == -1) {
			throw new IllegalArgumentException(
					"Withdrawal failed: Balance is less than amount requested");
		}
		
	}
	
	public static long calcNumOfDaysBetweenTwoDates(Date d1, Date d2) {
	        long milis1 = d1.getTime();
	        long milis2 = d2.getTime();
	        long diff = milis2 - milis1;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        return diffDays;	
	}
}
