package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + Utils.format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    public BigDecimal totalInterestPaid() {
    	BigDecimal total = BigDecimal.ZERO;
        for(Customer c: customers)
            total = total.add(c.totalInterestEarned());
        return total;
    }

    public String getFirstCustomer(){
            customers = null;
            if(null == customers)
            	throw new NullPointerException("Customer List not initialized");
            return customers.get(0).getName();

    }
}
