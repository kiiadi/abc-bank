package com.abc.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class Transaction {

    public enum Type {
        CREDIT,
        DEBIT
    }

    private BigDecimal amount;
    private Type type;
    private Date createdOn;

    public Transaction(BigDecimal amount, Type type, Date createdOn) {
        this.amount = amount;
        this.type = type;
        this.createdOn = createdOn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
