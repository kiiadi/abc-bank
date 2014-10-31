package com.abc.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public abstract class Account {

    private List<Transaction> transactions = new ArrayList<Transaction>();

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
