package com.abc.impl.helper;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class InterestRateCalculator {

    //this would probably take a day count convention rather than just use 365 days per year
    public static double calculateInterestRateForOneDay(double annualInterestRateInPercent) {
        double annualMultiplier = (annualInterestRateInPercent / 100) + 1;
        double dailyMultiplier = Math.pow(annualMultiplier,1.0/365.0);

        return (dailyMultiplier - 1) * 100;
    }

    public static BigDecimal calculateInterestForOneDay(BigDecimal amount, double annualInterestRateInPercent) {

        double interestRateForOneDay = calculateInterestRateForOneDay(annualInterestRateInPercent);
        double dailyMultiplier = (interestRateForOneDay + 100) / 100.0;

        return amount.multiply(new BigDecimal(dailyMultiplier - 1));
    }

}
