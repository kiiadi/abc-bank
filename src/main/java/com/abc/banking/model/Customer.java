package com.abc.banking.model;

import java.util.ArrayList;
import java.util.List;

import com.abc.banking.model.account.Account;

/**
 * This class stores customer information
 */
public class Customer {

	/**
	 *  Name of the customer
	 */
	private String customerName;
	/**
	 *  Accounts the customer holds 
	 */
	private List<Account> accountList;

	/**
	 * Constructor
	 * @param customerName
	 */
	public Customer(String customerName) {

		this.customerName = customerName;
		this.accountList = new ArrayList<Account>();
	}

	/**
	 * method to add new account
	 * @param account
	 */
	public void addAccount(Account account) {
		accountList.add(account);
	}	
	
	/**
	 * 
	 * @return number of accounts the customer holds
	 */
	public int getNumberOfAccounts() {
		return accountList.size();
	}

	/**
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Setter for customer name
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the accounts customer holds
	 */
	public List<Account> getAccountList() {
		return accountList;
	}

}
