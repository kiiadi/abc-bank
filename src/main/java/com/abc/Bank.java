package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents our Bank that provides different services to our customers
 * @author Prachi
 * 
 */
public class Bank {
	
	/**
	 * list of all customers for bank
	 */
	private List<Customer> customers;
	
	/**
	 * Initializes list of customers
	 */
	public Bank() {
		customers = new ArrayList<Customer>();
	}

	/**
	 * To add a customer in Bank
	 * @param customer to be added
	 */
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	/**
	 * To get customer accounts summary
	 * @return customer summary
	 */
	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customers)
			summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	/**
	 * Concat Account with no. of accounts 
	 * @param number of accounts
	 * @param String to concatenated 
	 * @return concatenated string with account and number of accounts
	 */
	private String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	/**
	 * Calculate total interest paid by bank 
	 * @return total interest paid bank to all customers
	 */
	public double totalInterestPaid() {
		double total = 0;
		for(Customer c: customers)
			total += c.totalInterestEarned();
		return total;
	}

	/**
	 * To get the first customer of bank
	 * @return name of first customer
	 */
	public String getFirstCustomer() {
		try {
			customers = null;
			return customers.get(0).getName();
		} catch (Exception e){
			e.printStackTrace();
			return "Error";
		}
	}
}