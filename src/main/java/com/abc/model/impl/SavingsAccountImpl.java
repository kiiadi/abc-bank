package com.abc.model.impl;

import java.util.List;

import com.abc.model.Transaction;
import com.abc.util.InterestCalculator;

public class SavingsAccountImpl extends AbstractAccountImpl {

	public SavingsAccountImpl(List<Transaction> transactions, long uid, String name) {
		super(transactions, uid, name);
	}
	
	public double accept(InterestCalculator cal, double amount) {
		return cal.calculateInterest(this, amount);
	}

}
