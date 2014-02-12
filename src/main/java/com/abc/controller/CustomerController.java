package com.abc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.CustomerBuilder;
import com.abc.model.Account;
import com.abc.model.Customer;

public class CustomerController {
	@Autowired
	final CustomerBuilder builder = null;
	final Map<Long, Customer> customers = new HashMap<Long, Customer>();
	
	public CustomerController() {
	
	}
	
	public Customer add(final String name) {
		final Customer customer = builder.accounts(new ArrayList<Account>()).name(name).createCustomer();
		this.customers.put(customer.getUid(), customer);
		return customer;
	}
	
	public void delete(final long id) throws Exception {
		//may need to delete accounts also??
		if(this.customers.containsKey(id)) {
			this.customers.remove(id);
		} else {
			throw new Exception("Customer already exists");
		}
	}
	
	public Customer get(final long id) throws Exception {
		if(this.customers.containsKey(id)) {
			return this.customers.get(id);
		} else {
			throw new Exception("Customer does not exists");
		}
	}
	
	public Collection<Customer> getAll() {
		return this.customers.values();
	}
	
	public void addAccount(final long id, final Account account) throws Exception {
		final Customer customer = this.customers.get(id);
		if(customer != null) {
			if(account != null) {
				customer.getAccounts().add(account);
			} else {
				throw new Exception("Account cant be null");
			}
		} else {
			throw new Exception("Account not added");
		}
	}
	
	public void deleteAccount(final long id, final Account act) throws Exception {
		final Customer customer = this.customers.get(id);
		if(customer != null) {
			if(customer.getAccounts().contains(act)) {
				customer.getAccounts().remove(act);
			} else {
				throw new Exception("Account not valid with this customer");
			}
		} else {
			throw new Exception("Account not added");
		}
	}

	public boolean contains(long uid) {
		return this.customers.containsKey(uid);
	}
}
