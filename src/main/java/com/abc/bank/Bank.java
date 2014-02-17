package com.abc.bank;

import com.abc.bank.customer.Customer;
import com.abc.bank.customer.Customers;
import com.abc.bank.exception.DuplicateCustomerException;

/**
 * Represents a bank.
 *
 */
public class Bank {

    private final Customers customers;

    /**
     * Parameterized constructor
     * @param customers
     */
    public Bank(Customers customers) {
        this.customers = customers;
    }

    /**
     * Adds the given customer to the bank.
     * @param customer
     * @throws DuplicateCustomerException if the customer 
     * already exists
     */
    public void addCustomer(Customer customer) throws DuplicateCustomerException {
        customers.addCustomer(customer);
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }
}