package com.abc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.CustomerBuilder;
import com.abc.model.Account;
import com.abc.model.Customer;

/**
 * A simple controller that performs CRUD operations on a Customer
 * 
 * @author erieed
 */
public class CustomerController {
	@Autowired
	final CustomerBuilder builder = null;
	final Map<Long, Customer> customers = new HashMap<Long, Customer>();
	
	/**
	 * Default Constructor
	 */
	public CustomerController() {
	
	}
	
	/**
	 * Add a Customer to the store
	 * @param name - name of customer
	 * @return
	 */
	public Customer add(final String name) {
		final Customer customer = builder.accounts(new ArrayList<Account>()).name(name).createCustomer();
		this.customers.put(customer.getUid(), customer);
		return customer;
	}
	
	/**
	 * Delete Customer from the store
	 * 
	 * @param id - uid of customer
	 * @throws Exception
	 */
	public void delete(final long id) throws Exception {
		//may need to delete accounts also??
		if(this.customers.containsKey(id)) {
			this.customers.remove(id);
		} else {
			throw new Exception("Customer already exists");
		}
	}
	
	/**
	 * Customer to delete from the store
	 * 
	 * @param id - uid of customer
	 * @return
	 * @throws Exception
	 */
	public Customer get(final long id) throws Exception {
		if(this.customers.containsKey(id)) {
			return this.customers.get(id);
		} else {
			throw new Exception("Customer does not exists");
		}
	}
	
	/**
	 * Get all customers in the store
	 * 
	 * @return
	 */
	public Collection<Customer> getAll() {
		return this.customers.values();
	}
	
	/**
	 * Helper method to  add Account to the Customer
	 * 
	 * @param id - uid of customer
	 * @param account - account to add to customer (assumes that account is already in AccountController)
	 * @throws Exception
	 */
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
	
	/**
	 * Delete Account from the Customer
	 * 
	 * @param id - uid of customer
	 * @param act - account to delete 
	 * @throws Exception
	 */
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

	/**
	 * Check to see if Customer is in store
	 * 
	 * @param uid
	 * @return
	 */
	public boolean contains(long uid) {
		return this.customers.containsKey(uid);
	}
}
