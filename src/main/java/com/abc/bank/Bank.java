/**
 * 
 */
package com.abc.bank;

import java.util.ArrayList;
import java.util.List;

import com.abc.customer.Customer;
import com.abc.exceptions.DuplicateCustomerException;

/**
 * A commercial bank
 *
 */
public class Bank {
	private final List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public List<Customer> getCustomers() {
		return customers;
	}
    
    public void addCustomer(Customer customer) throws DuplicateCustomerException {
    	if(customers.contains(customer)) {
    		throw new DuplicateCustomerException("Duplicate customer");
    	}
    	
        customers.add(customer);
    }

    public String customerSummary() {
        return BankReport.customerSummary(customers);
    }

    public double totalInterestPaid() {
        double total = 0;
      
        for(Customer customer: customers) {
            total += customer.totalInterestEarned();
        }
       
        return total;
    }

    public String getFirstCustomer() {
    	if(customers.size() == 0) {
    		return "There are no customers";
    	}
    	
        return customers.get(0).getName();
    }
	
}
