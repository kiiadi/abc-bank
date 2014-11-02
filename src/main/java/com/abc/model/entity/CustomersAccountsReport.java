package com.abc.model.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */

//this class is not adding much value right now, hopefully will be appreciated in the future
public class CustomersAccountsReport extends Report {

    private List<Customer> customers = new ArrayList<Customer>();

    public CustomersAccountsReport(String name) {
        super(name);
    }

    public void addCustomerToReport(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
