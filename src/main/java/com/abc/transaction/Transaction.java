package com.abc.transaction;

import java.util.Date;

public abstract class Transaction {

	protected Date date;
	
	public Date getDate(){
		return this.date;
	}
	
	protected void validate(final double amount){
		if(amount < 0){
			throw new IllegalArgumentException("Amount has to be greater than zero!");
		}
	}
}
