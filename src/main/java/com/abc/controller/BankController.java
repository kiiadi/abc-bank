package com.abc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.BankBuilder;
import com.abc.model.Bank;
import com.abc.model.Customer;

public class BankController {
	@Autowired
	final BankBuilder builder = null;
	
	//only need one bank, but could be a map for several Banks
	private Bank bank;
	
	public BankController() {
		
	}
	
	public Bank getBank() throws Exception {
		if(this.bank != null) {
			return bank;
		} else {
			this.bank = builder.customers(new ArrayList<Customer>()).createBank();
			return bank;
		}
	}
}
