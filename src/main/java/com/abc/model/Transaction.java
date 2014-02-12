package com.abc.model;

import java.util.Date;


public interface Transaction {

	public long getUid();
	
	public double getAmount();
	
	public Date getDateOfTransaction();
	
}
