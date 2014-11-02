package com.abc.model.api;

import com.abc.model.entity.Customer;

import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface CustomerManager {

    Customer addCustomer(String name);
    List<Customer> getAllCustomers();

}
