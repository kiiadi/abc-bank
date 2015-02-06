package com.abc.domain;

import java.util.Map;
import java.util.TreeMap;

public class Customer {
	private String name;
    private Map<AccountType, Account> accounts = new TreeMap<>();

    public Customer(String name) {
        this.name = name;
    }

	public String getName() {
        return name;
    }
    
    public Map<AccountType, Account> getAccounts() {
    	return accounts;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
    @Override
	public String toString() {
		return "Customer [name=" + name + ", accounts=" + accounts + "]";
	}
}
