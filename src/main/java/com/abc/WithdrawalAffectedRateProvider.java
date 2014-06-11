package com.abc;

import java.math.BigDecimal;

/**
 * A provider of rates based on how many days since last withdrawal.
 */
public class WithdrawalAffectedRateProvider implements RateProvider {

    private final BigDecimal lowerRate;

    private final BigDecimal upperRate;

    private final int daysSinceWithdrawalForUpperRate;

    public WithdrawalAffectedRateProvider(BigDecimal lowerRate, BigDecimal upperRate, int daysSinceWithdrawalForUpperRate) {
        this.lowerRate = lowerRate;
        this.upperRate = upperRate;
        this.daysSinceWithdrawalForUpperRate = daysSinceWithdrawalForUpperRate;
    }

    @Override
    public BigDecimal getRate(BigDecimal balance, Integer daysSinceWithdrawal) {
        if (daysSinceWithdrawal == null || daysSinceWithdrawal >= daysSinceWithdrawalForUpperRate) {
            return upperRate;
        }
        else {
            return lowerRate;
        }
    }
}
