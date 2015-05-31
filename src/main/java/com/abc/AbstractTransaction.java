package com.abc;

import java.util.Date;

public abstract class AbstractTransaction implements Transaction {

    private final String name;
    private final double amount;
    private final Date date;

    protected AbstractTransaction(String name, double amount) {
        this.name = name;
        this.amount = amount;
        date = DateProvider.now();
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
