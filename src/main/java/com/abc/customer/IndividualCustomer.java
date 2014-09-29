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
	
	public IndividualInformation getIndividualInformation() {
		return individualInformation;
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
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public String getDisplayName() {
		return individualInformation.getDisplayName();
	}

}
