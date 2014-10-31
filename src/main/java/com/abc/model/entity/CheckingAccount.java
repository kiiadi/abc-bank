package com.abc.model.entity;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class CheckingAccount extends Account {

    public CheckingAccount(String name) {
        super(name);
    }

    @Override
    public String getAccountType() {
        return "Checking Account";
    }
}
