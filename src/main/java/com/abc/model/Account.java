package com.abc.model;

import java.util.List;

import com.abc.util.InterestCalculator;


/**
 * POJO for a Bank Account
 * 
 * 
 * @author erieed
 *
 */
public interface Account {
	
	public long getUid();
	
	public String getName();
	
	public List<Transaction> getTransactions();
	
	public double accept(final InterestCalculator cal, double amount);
}
