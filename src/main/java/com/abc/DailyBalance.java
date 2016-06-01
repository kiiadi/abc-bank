package com.abc;

/**
 * This class will be used for calculating interest, it encapsulates days balance was maintained
 * @author santosh
 *
 */
public class DailyBalance {
	private int numberOfDays = 0;
	private double balance = 0.0;

	public DailyBalance(int numberOfDays, double balance) {
		super();
		this.numberOfDays = numberOfDays;
		this.balance = balance;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public double getBalance() {
		return balance;
	}
}
