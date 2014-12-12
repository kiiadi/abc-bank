package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.controller.BankController;
import com.abc.model.Customer;

/**
 * Class that represents a Service for doing all things
 * a Bank needs to do for our ABC-Bank 
 * 
 * @author erieed
 */
public class BankService {

	@Autowired
	final BankController transController = null;
	
	/**
	 * Since only one Bank exists,  create and return.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Customer> getBankCustomers() throws Exception {
		return this.transController.getBank().getCustomers();
	}

}
