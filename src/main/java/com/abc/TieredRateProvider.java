package com.abc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;

/**
 * A provider of rates on a tiered structure.
 */
public class TieredRateProvider implements RateProvider {

    private final SortedMap<BigDecimal, BigDecimal> ratesByUpperThreshold;

    public TieredRateProvider(SortedMap<BigDecimal, BigDecimal> ratesByUpperThreshold) {
        this.ratesByUpperThreshold = ratesByUpperThreshold;
    }

    @Override
    public BigDecimal getRate(BigDecimal balance, Integer daysSinceLastWithdrawl) {
        for (Map.Entry<BigDecimal, BigDecimal> thresholdAndRate : ratesByUpperThreshold.entrySet()) {
            if (balance.compareTo(thresholdAndRate.getKey()) <= 0) {
                return thresholdAndRate.getValue();
            }
        }
        throw new NoApplicableRateException("No rate applicable for balance " + balance);
    }
}
