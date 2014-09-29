package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.customer.ICustomer;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Bank {
	
    private List<ICustomer> customers;

    public Bank() {
        customers = new ArrayList<ICustomer>();
    }

    public void addCustomer(ICustomer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
    	
    	return null;
    }

    public double totalInterestPaid() {
        double total = 0;
        for(ICustomer c: customers)
            total += c.getTotalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
    	
    	return null;
    }
}
