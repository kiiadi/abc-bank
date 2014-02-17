package com.abc.bank.customer;

import com.abc.bank.exception.DuplicateCustomerException;


/**
 * Encapsulates a collection of customers
 *
 */
public interface Customers extends Iterable<Customer>{
    /**
     * Adds a customer to the collection of customers
     * Throws if a customer exists already
     * @param c
     */
    public void addCustomer(Customer c) throws DuplicateCustomerException;
    /**
     * Removes and returns the customer from the collection of customers
     * @param id
     * @return
     */
    public Customer removeCustomer(String id);
}
