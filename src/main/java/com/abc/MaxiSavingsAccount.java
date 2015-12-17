package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Yi on 12/16/15.
 */
class MaxiSavingsAccount extends Account{
    @Override
    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions().setScale(8, RoundingMode.HALF_UP);
        BigDecimal thousand = new BigDecimal(1000).setScale(8, RoundingMode.HALF_UP);
        BigDecimal twoThousand = new BigDecimal(2000).setScale(8, RoundingMode.HALF_UP);
        if (amount.compareTo(thousand) <= 0)
            return amount.multiply(new BigDecimal(0.02));
        if (amount.compareTo(twoThousand) <= 0)
            return new BigDecimal(20).add(amount.subtract(thousand).multiply(new BigDecimal(0.05)));  // 20 + (amount - 1000) * 0.05
        return new BigDecimal(70).add(amount.subtract(twoThousand).multiply(new BigDecimal(0.1))); //70 + (amount -2000) *0.1
    }

    @Override
    public int getAccountType() {
        return MAXI_SAVINGS;
    }
}
