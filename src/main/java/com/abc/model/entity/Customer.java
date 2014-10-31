package com.abc.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class Customer extends Role {

    private List<Account> accounts = new ArrayList<Account>();

    public Customer(String name) {
        super(name);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccountByName(String name) {
        for(Account account : accounts) {
            if(account.getName().equals(name)) return account;
        }

        return null;
    }
}
