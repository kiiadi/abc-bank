package com.abc.domain;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final AccountType accountType;
    public List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(AccountType accountType) {
        this.accountType = accountType;
    }
    
    public AccountType getAccountType() {
        return accountType;
    }
    
    public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
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
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", transactions="
				+ transactions + "]";
	}
}
