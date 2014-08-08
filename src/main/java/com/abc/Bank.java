package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class implements behavior of a bank.<br>
 * The bank can have number of customers, 
 * manipulate with them and provide total bank statement.
 */
public class Bank {
    private List<Customer> customers;

    /**
     * Create Bank instance.
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Add a new customer.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Return pretty representation of bank info.
     */
    public String customerSummary() {
        StringBuilder sb = new StringBuilder("Customer Summary");
        for (Customer c : customers) {
        	sb.append("\n - ");
        	sb.append(c.getName());
        	sb.append(" (");
        	sb.append(Account.formatNumber(c.getNumberOfAccounts(), "account"));
        	sb.append(")");
        }
        return sb.toString();
    }    

    /**
     * Calculate total interest earned by bank customers until now.
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }
    
    /**
     * Calculate total interest earned by bank customers until given date.
     */
    public double totalInterestPaid(Date date) {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned(date);
        return total;
    }

    /**
     * Return the first customer name if any. Otherwise return empty string.
     */
    public String getFirstCustomer() {
    	if (customers.size() > 0)
    		return customers.get(0).getName();
    	else
    		return "";
    }
}
