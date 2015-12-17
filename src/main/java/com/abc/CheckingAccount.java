package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Yi on 12/16/15.
 */
class CheckingAccount extends Account{
    @Override
    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions().setScale(8, RoundingMode.HALF_UP);
        return amount.multiply(new BigDecimal(0.001));
    }

    @Override
    public int getAccountType() {
        return CHECKING;
    }
}
