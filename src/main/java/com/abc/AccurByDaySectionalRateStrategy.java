package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Yi on 12/17/15.
 */
public class AccurByDaySectionalRateStrategy implements InterestCalculator {
    SectionalRateStrategy calculator;
    AccurByDaySectionalRateStrategy(SectionalRateStrategy calculator){
        this.calculator = calculator;
        convertRate();
    }

    private void convertRate(){
        ArrayList<BigDecimal> rates= calculator.getRateArray();
        ArrayList<BigDecimal> newRates=  (ArrayList<BigDecimal>)rates.stream().map(i->annRateToCmpdRate(i)).collect(Collectors.toList());
        calculator.setRateArray(newRates);
    }

    protected BigDecimal annRateToCmpdRate(BigDecimal annRate ){
        double i = annRate.doubleValue();
        double r = Math.pow((1 + i/365), 365)-1;
        return new BigDecimal(r);
    }

    public BigDecimal calculateInterest(BigDecimal amount) {
        return  calculator.calculateInterest(amount);
    }
}
