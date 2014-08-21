package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Creates a bank with the provided customers...
     * @param customers
     */
    public Bank(Customer... customers){
        this();
        for (Customer toAdd : customers) {
            addCustomer(toAdd);
        }
    }

    /**
     * Adds the provided customer to this bank.
     * @param customer
     */
    public void addCustomer(Customer customer) {
        if(customers==null){
            customers = new ArrayList<Customer>();
        }
        customers.add(customer);
    }

    /**
     * Provides a formatted summary of customer related information.
     * @return
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + pluralize(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    /**
     * Ensures correct plural of word is created based on the number passed in:
     * - If number passed in is 1 just return the word otherwise add an 's' at the end
     */
    private String pluralize(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * Calculates the total amount of interested paid on all accounts of all customers.
     * @return - the total amount of interested paid on all accounts of all customers.
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
