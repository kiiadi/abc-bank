package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public class SavingInterestCalculator implements InterestRateCalculator{

    @Override
    public double calculateInterestRate(List<Transaction> transactions, double totalAmount) {
        if (totalAmount <= 1000)
            return 0.001;
        else
            return 0.002;
    }





}
