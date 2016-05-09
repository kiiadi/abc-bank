package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    /**
     * The bank entity that holds a list of customers
     */
    private List<Customer> customers;

    /**
     * The default constructor to initialize an empty list to hold the customers
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Adds the customer object to the list of customers
     * @param customer the customer object to be added into the bank
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Getter method to list all the customers in the bank
     * @return list of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Calculates the total interest paid for all the customers in the bank.
     * Delegates to the respective customers' accounts to calculate their respective interests
     * @return the total interest paid by the bank
     */
    public double calculateTotalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.calculateTotalInterestEarned();
        return total;
    }
}
