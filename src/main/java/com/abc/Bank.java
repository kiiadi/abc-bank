package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * This class returns Bank. With the instance of Bank, client will be able to
 * add a new customer, get customer summary and total interest paid to the customers.
 * 
 * @author Manish
 *
 */
public class Bank {
    private List<Customer> customers;

    /**
     * Constructs a bank object.
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Adds a new customer to the bank.
     * 
     * @param customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Returns the list of customers with their names and number of accounts they have.
     * @return
     */
    public String getCustomerSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Customer Summary");
        for (Customer c : customers) {
            summary.append("\n - ");
            summary.append(c.getName());
            summary.append(" (");
            summary.append(format(c.getNumberOfAccounts(), "account"));
            summary.append(")");
        }
        return summary.toString();
    }

    /**
     * This method makes sure the correct plural of word is created based on the number passed in.
     * If number passed in is 1 just return the word otherwise add an 's' is suffixed
     * 
     * @param number
     * @param word
     * @return
     */
    private String format(int number, String word) {
    	StringBuilder finalWord = new StringBuilder();
    	finalWord.append(number);
    	finalWord.append(" ");
    	finalWord.append(number == 1 ? word : word + "s");
        return finalWord.toString();
    }

    /**
     * This methods returns the total number of interest paid to all the customers. 
     * @return
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    /**
     * Returns the first customer created in this bank.
     * @return
     */
    public String getFirstCustomer() {
    	if(customers.size()==0) {
    		return "No customers added yet!";
    	}
    	return customers.get(0).getName();
    }
}
