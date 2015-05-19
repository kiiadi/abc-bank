package com.abc;


import java.math.BigDecimal;

public class CheckingAccount extends Account {

    public BigDecimal interestEarned() {
        return getBalance().multiply(new BigDecimal(0.001));
    }

    public String getLabel() {
        return "Checking Account";
    }

}
