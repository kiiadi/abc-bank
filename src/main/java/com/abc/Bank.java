package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	
	/**
	 * List of customers
	 */
    private List<Customer> customers;

    /**
     * Constructor
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Add customer
     * @param customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Get customer summary
     * @return summary
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    /**
     * Utility method to define number of accounts
     * Make sure correct plural of word is created based on the number passed in
     * If number passed in is 1 just return the word otherwise add an 's' at the end
     * @param number
     * @param word
     * @return Formatted description
     */
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * Calculate total interest
     * @return interest
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    /**
     * Get first customer
     * @return customer name
     */
    public String getFirstCustomer() {
        try {
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
}
