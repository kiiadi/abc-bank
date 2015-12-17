package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Yi on 12/17/15.
 */
public class SectionalRateStrategy implements InterestCalculator {

    private ArrayList<BigDecimal> rateArray;
    private ArrayList<BigDecimal> thresholdArray;

    public SectionalRateStrategy(ArrayList<BigDecimal> rateArray, ArrayList<BigDecimal> thresholdArray) {
        if(rateArray.size()!= thresholdArray.size())
            throw new IllegalArgumentException("rateArray size must equalls thresholdArray size");
        this.rateArray = rateArray;
        this.thresholdArray = thresholdArray;
    }

    public BigDecimal calculateInterest(BigDecimal amount) {
        BigDecimal result = BigDecimal.ZERO;
        for (int i =0; i!=rateArray.size();i++){
            if( amount.compareTo(thresholdArray.get(i))>0)
                if(i!=0) result = result.add(thresholdArray.get(i).subtract(thresholdArray.get(i - 1)).multiply(rateArray.get(i)));
                else  result =result.add(thresholdArray.get(i).subtract(BigDecimal.ZERO).multiply(rateArray.get(i)));
            else {
                if(i!=0) result = result.add(amount.subtract(thresholdArray.get(i - 1)).multiply(rateArray.get(i)));
                else result = result.add(amount.multiply(rateArray.get(i)));
            }
        }
        return result;
    }

    public ArrayList<BigDecimal> getRateArray() {
        return rateArray;
    }

    public void setRateArray(ArrayList<BigDecimal> rateArray) {
        this.rateArray = rateArray;
    }

    public ArrayList<BigDecimal> getThresholdArray() {
        return thresholdArray;
    }

    public void setThresholdArray(ArrayList<BigDecimal> thresholdArray) {
        this.thresholdArray = thresholdArray;
    }


}
