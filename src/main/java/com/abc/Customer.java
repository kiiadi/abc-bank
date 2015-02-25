package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private final String _name;
    private final Integer _id ;
    private List<Account> _accounts;
    public Customer(final Integer id_ , String name_) {
    	_id = id_;
        _name = name_;
        _accounts = new ArrayList<Account>();
    }
    public String getName() {
        return _name;
    }

    public Integer getId()
    {
    	return _id;
    }
    public Customer openAccount(Account account) {
        _accounts.add(account);
        return this;
    }

    public List<Account> getAccounts()
    {
    	return _accounts;
    }
    
    public int getNumberOfAccounts() {
        return _accounts.size();
    }
}
