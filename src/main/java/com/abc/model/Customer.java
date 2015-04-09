package com.abc.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.abc.account.Account;
import com.abc.exception.DuplicateAccountException;
import com.abc.util.Constants;
import com.abc.util.Utils;

public class Customer {
	private String ssn;
    private String firstName;
    private String lastName;
    private Map<String, Account> accountMap;

    public Customer(String ssn, String firstName, String lastName) {
    	this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountMap = new HashMap<String, Account>();
    }

    public String getFirstName() {
		return firstName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Map<String, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<String, Account> accountMap) {
		this.accountMap = accountMap;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer openAccount(Account account) throws DuplicateAccountException {
		//check if account already exists
		if ( accountMap.containsKey(account.getAccountNumber())) {
			throw new DuplicateAccountException("");
		} else {
			accountMap.put(account.getAccountNumber(), account);
		}
        return this;
    }

    public int getNumberOfAccounts() {
        return accountMap.size();
    }

    public BigDecimal getTotalInterestEarned() {
        BigDecimal totalInterest = BigDecimal.ZERO;
        for (Account acc : accountMap.values()) {
        	totalInterest = totalInterest.add(new BigDecimal(acc.getInterestEarned()));
        }
        return totalInterest;
    }

	public String getDisplayName() {
		return new StringBuilder().append(getFirstName()).append(", ").append(getLastName()).toString();
	}

    public String getCustomerStatement() {
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append(Constants.STATEMENT);
        statementBuilder.append(Constants.FILLER);        
        statementBuilder.append(getDisplayName());
        statementBuilder.append(Constants.LINE_SEPARATOR);
        BigDecimal totalAcrossAllAccounts = new BigDecimal(0);
        for (Account acc : accountMap.values()) {
        	totalAcrossAllAccounts = totalAcrossAllAccounts.add(acc.getAccountBalance());
        	statementBuilder.append(acc.getDetailedStatement());
        	statementBuilder.append(Constants.LINE_SEPARATOR);
        }
        statementBuilder.append(Constants.TOTAL_FOR_CUSTOMER);
        statementBuilder.append(Constants.FILLER);
        statementBuilder.append(Utils.displayRoundedWithCurrency(totalAcrossAllAccounts));
        return statementBuilder.toString();
    }
    
}
