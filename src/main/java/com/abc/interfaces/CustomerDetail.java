package com.abc.interfaces;

import java.util.Map;

public interface CustomerDetail {

	Map<Object, AccountDetail> getAccounts();

	AccountDetail createAccount(int accountType);
	
	String getName();

	
}
