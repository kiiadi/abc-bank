package com.abc.impl;

import com.abc.model.api.CustomerManager;
import com.abc.model.entity.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandrkoller on 31/10/2014.
 */
public class DefaultCustomerManager implements CustomerManager {

    private List<Customer> allCustomers = Collections.synchronizedList(new ArrayList<Customer>());

    @Override
    public Customer addCustomer(String name) {
        Customer customer = new Customer(name);
        allCustomers.add(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<Customer>(allCustomers);
    }
}
