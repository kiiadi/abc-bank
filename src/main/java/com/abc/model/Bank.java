package com.abc.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.abc.account.Account;
import com.abc.exception.DuplicateAccountException;
import com.abc.exception.DuplicateCustomerException;
import com.abc.util.Constants;
import com.abc.util.Utils;

public class Bank {
	private Map<String, Customer> customerMap;

    public Bank() {
    	customerMap = new HashMap<String, Customer>();
    }

    public void addCustomer(Customer customer) throws DuplicateCustomerException {
		if (customerMap.containsKey(customer.getSsn())) {
			throw new DuplicateCustomerException("");
		} else {
			customerMap.put(customer.getSsn(), customer);
		}
    }
    
    public void openAccount(Customer customer, Account account) throws DuplicateAccountException {
    	customer.openAccount(account);
    }
    
    public String getCustomerStatement(Customer customer) {
    	return customer.getCustomerStatement();
    }
    
    public Customer getCustomer(String ssn) {
    	return customerMap.get(ssn);
    }
    
    public String getCustomerSummary() {
    	StringBuilder statementBuilder = new StringBuilder();
    	statementBuilder.append(Constants.CUSTOMER_SUMMARY);
    	statementBuilder.append(Constants.LINE_SEPARATOR);
        for (Customer c : customerMap.values()) {
        	statementBuilder.append(c.getDisplayName());
        	statementBuilder.append(Constants.FILLER);
        	statementBuilder.append(Constants.NUMBER_OF_ACCOUNTS);
        	statementBuilder.append(c.getAccountMap().values().size());
        }
        return statementBuilder.toString();
    }
  
    public BigDecimal getTotalInterestPaidAcrossAllAccounts() {
    	BigDecimal totalInterestPaid = BigDecimal.ZERO;
        for(Customer c: customerMap.values()) {
        	totalInterestPaid = totalInterestPaid.add(c.getTotalInterestEarned());
        }
        return totalInterestPaid;
    }
    
    public String getTotalInterestPaidStatement() {
    	StringBuilder statementBuilder = new StringBuilder();
    	statementBuilder.append(Constants.TOTAL_INTEREST_PAID);
    	statementBuilder.append(Constants.FILLER);
    	statementBuilder.append(Utils.displayRoundedWithCurrency(getTotalInterestPaidAcrossAllAccounts()));
        return statementBuilder.toString();
    }

}
