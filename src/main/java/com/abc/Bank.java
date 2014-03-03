package com.abc;

import com.abc.utils.WordFormater;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }
    
    public void addCustomer(Customer customer) {
        
        if (customer != null && customer.getName() != null) {
            customers.add(customer);
        } else {
            throw new IllegalArgumentException("Invalid customer.");
        }
        
    }

    public String customerSummary() {
        
        int numberOfAccounts = 0;
        
        String accountWord = null;
        StringBuilder summary = new StringBuilder("Customer Summary");
        
        for (Customer customer : customers) {
            summary.append("\n - ");
            summary.append(customer.getName());
            summary.append(" (");
            
            numberOfAccounts = customer.getNumberOfAccounts();
            summary.append(numberOfAccounts);
            summary.append(" ");
            
            accountWord = WordFormater.ACCOUNT;
            
            if (numberOfAccounts > 1) {
                accountWord = WordFormater.puralize(accountWord);
            }
            
            summary.append(accountWord);
            summary.append(")");
        }
        
        return summary.toString();
        
    }

    public BigDecimal totalInterestPaid() {
        
        BigDecimal total = BigDecimal.ZERO.setScale(2);
        
        for (Customer customer : customers) {
            total = total.add(customer.totalInterestEarned());
        }
        
        return total;
    }

    public String getFirstCustomerName() {
        String customerName = null;

        if (customers.size() > 0) {
            customerName = customers.get(0).getName();
        }

        return customerName;
    }

}
