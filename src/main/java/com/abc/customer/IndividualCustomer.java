package com.abc.customer;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.IAccount;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class IndividualCustomer implements ICustomer {
	
	private List<IAccount> accounts;

	private String customerId;
	
	private IndividualInformation individualInformation;
	
	public IndividualCustomer(final String customerId){
		this.customerId = customerId;
		this.accounts = new ArrayList<IAccount>();
	}
	
	public void openAccount(IAccount account) {
		this.accounts.add(account);
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double interst = 0.0;
		for(IAccount account : accounts){
		}
		return interst;
	}

	public String getStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
