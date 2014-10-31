package com.abc.model.entity;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class SavingsAccount extends Account {

    public SavingsAccount(String name) {
        super(name);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
