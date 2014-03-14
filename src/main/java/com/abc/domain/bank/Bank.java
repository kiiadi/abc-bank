package com.abc.domain.bank;

import java.util.ArrayList;
import java.util.List;

import com.abc.domain.account.Account;
import com.abc.domain.statement.CustomerSummaryStatement;
import com.abc.domain.sub.money.Money;

public class Bank {
	
	private List<Customer> customers;
	
	public Bank() {
		customers = new ArrayList<Customer>();
	}

    public void openAccount(Customer customer, Account... accounts) {
    	if (isNewCustomer(customer)) {
    		customers.add(customer);
    	}
    	
    	for (Account account : accounts) {
    		customer.openAccount(account);
    	}
    }
    
    private boolean isNewCustomer(Customer customer) {
        return !customers.contains(customer);
    }
    
	public String customerSummary() {
		return new CustomerSummaryStatement(customers).generate();
	}

	public Money totalInterestPaid() {
		Money total = Money.dollars(0);
		
		for (Customer customer : customers)
			total = total.plus(customer.totalInterest());
		
		return total;
	}

    public List<Customer> customers() {
    	return customers;
    }
}
