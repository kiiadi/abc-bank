package com.abc.bank.customer;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.abc.bank.exception.DuplicateCustomerException;


public class CustomersImpl implements Customers{

    private ConcurrentMap<String, Customer> customers;

    public CustomersImpl(){
        customers = new ConcurrentHashMap<String, Customer>();
    }

    public void addCustomer(Customer c) throws DuplicateCustomerException {
        String id = c.getId();
        if(null!=customers.putIfAbsent(id, c)){
            throw new DuplicateCustomerException("Customer with id " + id + " already exists in the bank");
        }
    }

    public Customer removeCustomer(String id) {
        return customers.remove(id);
    }

    public Iterator<Customer> iterator() {
        return customers.values().iterator();
    }

    @Override
    public String toString() {
        String summary = "Customer Summary";
        for (Customer c : this)
            summary += "\n - " + c.getSummary() + ")";
        return summary;
    }
}
