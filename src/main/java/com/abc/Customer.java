package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
	
	private String id;
	public String getId() { return id; } 

    private String name;
    public String getName() { return name; }
    
    private List<Account> accounts;
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts);}

	public Customer(String name) {
		this.id = idAssumeSameAsUniqueNameForNow(name);
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    private String idAssumeSameAsUniqueNameForNow(String name) {
		return name;
	}

	public void openAccount(Account account) {
		account.setCustomerId(id);
        accounts.add(account);
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }
    

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
    	return Reports.customerStatement(this);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
