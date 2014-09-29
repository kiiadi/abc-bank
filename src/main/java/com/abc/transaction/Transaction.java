package com.abc.transaction;

import java.util.Date;

public abstract class Transaction {

	protected Date date;
	
	public Date getDate(){
		return this.date;
	}
}
