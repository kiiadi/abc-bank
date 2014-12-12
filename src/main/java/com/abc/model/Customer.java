package com.abc.model;

import java.util.List;

public interface Customer {
	public long getUid();
	
	public List<Account> getAccounts();
	
	public String getName();
}
