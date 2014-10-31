package com.abc.model.entity;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class MaxiSavingsAccount extends Account {

    public MaxiSavingsAccount(String name) {
        super(name);
    }


    @Override
    public String getAccountType() {
        return "Maxi-Savings Account";
    }
}
