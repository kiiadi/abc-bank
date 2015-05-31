package com.abc.transaction;

import java.util.Date;

public abstract class AbstractTransaction implements Transaction {

    private static final DateProvider DATE_PROVIDER = new DefaultDateProvider();
    private final String name;
    private final Date date;
    private final double amount;

    protected AbstractTransaction(String name, double amount) {
        this.name = name;
        this.amount = validate(amount);
        date = DATE_PROVIDER.now();
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

    private static double validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        return amount;
    }
}
