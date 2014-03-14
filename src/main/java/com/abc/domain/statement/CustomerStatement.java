package com.abc.domain.statement;

import com.abc.domain.account.Account;
import com.abc.domain.bank.Customer;

public class CustomerStatement extends AbstractStatement {

	private Customer customer;
	
	public CustomerStatement(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String generate() {
		String statement = "Statement for " + customer.getName() + NEW_LINE;
		
		for (Account account : customer.allAccounts()) {
			statement += NEW_LINE + account.statement().generate() + NEW_LINE;
		}
		
		statement += NEW_LINE + "Total In All Accounts " + dollarFormat(customer.totalBalance());
		
		return statement;
	}
}
