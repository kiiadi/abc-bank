package com.abc;

/**
 * An administrative service for managing customers, for example, adding new customers.
 */
public interface AdminService {

    /**
     * Adds a customer.
     * @param customer the new customer
     * @throws java.lang.IllegalArgumentException if the customer ID is already known to the bank
     */
    void addCustomer(CustomerImpl customer);
}
