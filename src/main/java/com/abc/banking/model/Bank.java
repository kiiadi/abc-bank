package com.abc.banking.model;

import java.util.List;

/**
 * 
 * This class stores the bank details
 *
 */
public class Bank {

	/**
	 * Name of the bank
	 */
	private String name;	
	
	/**
	 * List of customers in bank
	 */
	private List<Customer> customers;
	

	/**
	 * Setter method for bank name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter method for customers list
	 * @param customers
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * getter method for bank name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter method for customer list
	 * @return
	 */
	public List<Customer> getCustomers() {
		return customers;
	}
	
	
}
