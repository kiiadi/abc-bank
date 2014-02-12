package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.controller.BankController;
import com.abc.model.Customer;

public class BankService {

	@Autowired
	final BankController transController = null;
	
	public List<Customer> getBankCustomers() throws Exception {
		return this.transController.getBank().getCustomers();
	}

}
