package com.abc.persistance;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.CustomerDetail;

public class Customer implements CustomerDetail {
	private String name;
	private Map<Object, AccountDetail> m_accounts;

	public Customer(String name) {
		this.name = name;
		this.m_accounts = new LinkedHashMap<Object, AccountDetail>();
	}

	public Map<Object, AccountDetail> getAccounts() {
		return m_accounts;
	}

	public AccountDetail createAccount(int accountType) {
		Account ac = new Account(accountType);
		getAccounts().put(accountType, ac);
		return ac;
	}

	public String getName() {
		return name;
	}

}
