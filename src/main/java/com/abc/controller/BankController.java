package com.abc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.BankBuilder;
import com.abc.model.Bank;
import com.abc.model.Customer;

/**
 * A simple controller that will create a Bank.
 * 
 * @author erieed
 *
 */
public class BankController {
	@Autowired
	final BankBuilder builder = null;
	
	//only need one bank, but could be a map for several Banks
	private Bank bank;
	
	/**
	 * Default Constructor
	 */
	public BankController() {
		
	}
	
	/**
	 * 
	 * Gets the current bank for this application
	 * 
	 * @return
	 * @throws Exception
	 */
	public Bank getBank() throws Exception {
		if(this.bank != null) {
			return bank;
		} else {
			this.bank = builder.customers(new ArrayList<Customer>()).createBank();
			return bank;
		}
	}
}
