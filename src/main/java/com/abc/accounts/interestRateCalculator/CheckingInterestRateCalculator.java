package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public class CheckingInterestRateCalculator implements InterestRateCalculator<List<Transaction>>{

    @Override
    public double calculateInterestRate(List<Transaction> transactions) {
        return 0.001;
    }



}
