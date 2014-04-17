package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
	/* Use efficient string concatenation */
	StringBuilder summary = new StringBuilder("Customer Summary"); 
        for (Customer customer : customers)
            summary.append("\n - " + customer.getName() + " (" + format(customer.getNumberOfAccounts(), "account") + ")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer customer: customers)
            total += customer.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        try {
	    /* customers list cannot be set to null to avoid resetting the list */ 
            //customers = null;

            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            //return "Error";
	    throw e;
        }
    }
}
