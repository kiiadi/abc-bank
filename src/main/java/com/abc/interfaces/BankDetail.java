package com.abc.interfaces;

import java.util.Map;

public interface BankDetail {
	String getCode();
	CustomerDetail createNewCustomer(String accountName);
	Map<String, CustomerDetail> getCustomers();
}
