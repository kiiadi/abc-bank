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

    public void addCustomer(final ICustomer customer) {
        customers.add(customer);
    }

    public String getCustomerSummary() {
    	final StringBuilder statementBuilder = new StringBuilder();
    	for(final ICustomer customer : customers){
    		statementBuilder.append(customer.getStatement());
    		statementBuilder.append("\n");
    	}
    	return statementBuilder.toString();
    }

    public double getTotalInterestPaid() {
        double total = 0;
        for(final ICustomer customer: customers){
            total += customer.getTotalInterestEarned();
        }
        return total;
    }

    public int getCustomerCount(){
    	return customers.size();
    }
}
