/*****
 * Transaction.java
 * 
 * Contains information about a single transaction
 * 
 * @author Mahir Aydin
 */
package com.abc;

import static java.lang.Math.abs;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    // Instance variables are private and final, 
	// only constructor can set them.
	private final double amount;
    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    //Getter methods for amount and date
    public double getAmount()
    {
    	return amount;
    }

    public Date getDate()
    {
    	return transactionDate;
    }

    // Formatted transaction summary (new method)
    public String getDetail()
    {
    	s = new String("");
    	if (amount < 0) 
			s+= "Withdrawal: "
		else 
			s+= "Deposit: ";
		s+= Utils.toDollars(amount) + "\n";
		return s;
    }
}
