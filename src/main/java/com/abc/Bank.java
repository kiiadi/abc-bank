package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
    private final List<Customer> customers;

    public Bank() {
        customers = Collections.synchronizedList(new ArrayList<Customer>());
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuffer summary = new StringBuffer() ;
        summary.append("Customer Summary\n");
        for (Customer c : customers) {
        	int no = c.getNumberOfAccounts() ;
        	summary.append(no) ;
        	summary.append(" account");
        	if ( no > 0 ) {
        		summary.append("s") ;
        	}
        	summary.append("\n");
        }
        return summary.toString();
    }


    public synchronized double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {  	
    	return  (customers.size() > 0) ? customers.get(0).getName() : "Error" ;
    }
}
