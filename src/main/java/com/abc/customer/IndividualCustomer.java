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

	private String id;
	
	private IndividualInformation individualInformation;
	
	public IndividualCustomer(final String id){
		this.id = id;
		this.accounts = new ArrayList<IAccount>();
	}
	
	public void setIndividualInformation(final IndividualInformation individualInformation) {
		this.individualInformation = individualInformation;
	}
	
	public void openAccount(IAccount account) {
		this.accounts.add(account);
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double getTotalInterestEarned() {
		double interst = 0.0;
		for(IAccount account : accounts){
			interst += account.getInterestEarned();
		}
		return interst;
	}

	public String getStatement() {
		final StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("Customer ID: ");
		statementBuilder.append(id);
		statementBuilder.append(" - ");
		statementBuilder.append("Customer Name: ");
		statementBuilder.append(getDisplayName());
		statementBuilder.append(" - ");
		statementBuilder.append("Number of Account(s): ");
		statementBuilder.append(getNumberOfAccounts());
		return statementBuilder.toString();
	}

	public String getId() {
		return id;
	}

	public String getDisplayName() {
		return individualInformation.getDisplayName();
	}

}
