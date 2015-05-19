package com.abc;

import java.math.BigDecimal;

public class SavingAccount extends Account {

    public BigDecimal interestEarned() {
        BigDecimal amount = getBalance();
        if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
            return amount.multiply(BigDecimal.valueOf(0.001));
        } else {
            return amount.subtract(BigDecimal.valueOf(1000)).multiply(BigDecimal.valueOf(0.002)).add(BigDecimal.ONE);
        }
    }

    public String getLabel() {
        return "Savings Account";
    }

}
