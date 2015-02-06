package com.abc.service;

import static com.abc.utils.Utils.EOL;

import com.abc.domain.Account;
import com.abc.domain.Bank;
import com.abc.domain.Customer;

public class BankManager {
	
	private CustomerService customerService;
	
    public void openAccount(Bank bank, Customer customer, Account account) {
    	addCustomer(bank, customer);
    	customerService.addAccount(customer, account);
    }
	
    public void addCustomer(Bank bank, Customer customer) {
    	if(bank == null) {
    		throw new IllegalArgumentException("Attempt to add customer to null Bank.");
    	}
    	if( ! bank.getCustomers().contains(customer)) {
    		bank.getCustomers().add(customer);
    	}
    }
    
    public String getCustomerSummary(Bank bank) {
    	if(bank == null) {
    		throw new IllegalArgumentException("Attempt to add customer summary for null Bank.");
    	}
    	
    	StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : bank.getCustomers())
            summary.append(EOL).append(" - ").append(c.getName()).append(" (")
            	.append(format(customerService.getNumberOfAccounts(c), "account")).append(")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    
    
    public double getTotalInterestPaid(Bank bank) {
    	if(bank == null) {
    		throw new IllegalArgumentException("Attempt to get total interest paid by null Bank.");
    	} 
    	
        double total = 0;
        for(Customer customer: bank.getCustomers())
            total += customerService.getTotalInterestEarned(customer);
        return total;
    }
    
    public String getFirstCustomer(Bank bank) {
    	if(bank == null) {
    		throw new IllegalArgumentException("Attempt to get first customer of null Bank.");
    	} 
    	
        if(bank.getCustomers().isEmpty()) {
        	return "No customer yet. We are just too new in this space.";
        } else {
        	return bank.getCustomers().get(0).getName();
        }
    }

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
