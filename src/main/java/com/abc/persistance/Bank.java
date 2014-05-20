package com.abc.persistance;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.interfaces.BankDetail;
import com.abc.interfaces.CustomerDetail;

public class Bank implements BankDetail {
	private String m_code;
	private Map<String, CustomerDetail> m_customers;

	public Map<String, CustomerDetail> getCustomers() {
		if (m_customers == null) {
			m_customers = new LinkedHashMap<String, CustomerDetail>();
		}
		return m_customers;
	}

	public CustomerDetail createNewCustomer(String accountName) {
		Customer customer = new Customer(accountName);
		getCustomers().put(accountName, customer);
		return customer;
	}

	public String getCode() {
		return m_code;
	}

	public void setCode(String code) {
		m_code = code;
	}
}
