package com.abc;

import java.time.LocalDate;

public class Transaction {
    private final double amount;
    public double getAmount() { return amount;}

	private final LocalDate date;
    public LocalDate getDate() { return date;}
    
	public Transaction(double amount) {
        this.amount = amount;
        this.date = DateProvider.getInstance().now();
    }
}
