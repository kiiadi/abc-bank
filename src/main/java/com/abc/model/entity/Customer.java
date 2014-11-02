package com.abc.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class Customer extends Role {

    private List<Account> accounts = Collections.synchronizedList(new ArrayList<Account>());

    public Customer(String name) {
        super(name);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<Account>(accounts);
    }

    public Account getAccountByName(String name) {
        synchronized (accounts) {
            for (Account account : accounts) {
                if (account.getName().equals(name)) return account;
            }
        }

        return null;
    }
}
