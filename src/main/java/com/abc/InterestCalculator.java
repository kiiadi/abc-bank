package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Calculates interest accrued for a given account and rate provider.
 *
 * Note this class uses a fixed and somewhat arbitrary number of decimal places - a real implementation would need to consider
 * this in more detail.
 */
public class InterestCalculator {

    private static final BigDecimal BASIS_FRACTION = new BigDecimal(1.0d / 365.0d);
    private static final int DECIMAL_PLACES = 6;

    private final AccountImpl account;

    private final RateProvider rateProvider;

    public InterestCalculator(AccountImpl account, RateProvider rateProvider) {
        this.account = account;
        this.rateProvider = rateProvider;
    }

    /**
     * For each day in the period, finds applicable rate and accrues 1/basis x the rate.
     * @param endDate end of period
     * @return the interest accrued
     */
    public BigDecimal getInterestAccrued(final Date endDate) {

        BigDecimal interestTotal = BigDecimal.ZERO;

        if (!account.getTransactions().isEmpty()) {
            Date currentDate = getStartDate();

            while (currentDate.compareTo(endDate) < 0) {
                BigDecimal balance = account.getBalance(currentDate);
                Integer daysSinceWithdrawal = account.getDaysSinceLastWithdrawal(currentDate);
                BigDecimal applicableRate = rateProvider.getRate(balance, daysSinceWithdrawal);

                BigDecimal dailyInterest = BASIS_FRACTION.multiply(applicableRate).multiply(balance);
                interestTotal = interestTotal.add(dailyInterest);

                currentDate = DateUtils.addOneDay(currentDate);
            }
        }

        return interestTotal.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
    }

    private Date getStartDate() {
        return account.getTransactions().get(0).getTransactionDate();
    }
}
