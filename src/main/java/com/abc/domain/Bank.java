package com.abc.domain;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	
    private List<Customer> customers = new ArrayList<Customer>();
    
    public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public String toString() {
		return "Bank [customers=" + customers + "]";
	}
}

	