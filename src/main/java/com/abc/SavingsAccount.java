package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Yi on 12/16/15.
 */
class SavingsAccount extends Account{
    @Override
    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions().setScale(8, RoundingMode.HALF_UP);
        BigDecimal thousand = new BigDecimal(1000).setScale(8, RoundingMode.HALF_UP);
        BigDecimal twoThousand = new BigDecimal(2000).setScale(8, RoundingMode.HALF_UP);
        if (amount.compareTo(thousand) <= 0)
            return amount.multiply(new BigDecimal(0.001));
        else
            return BigDecimal.ONE.add(amount.subtract(thousand).multiply(new BigDecimal(0.002)));  // 1 + (amount - 1000) * 0.002    }
    }

    @Override
    public int getAccountType() {
        return SAVINGS;
    }
}
