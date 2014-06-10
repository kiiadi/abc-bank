package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;

import java.util.List;

public interface InterestRateCalculator<T> {
    double calculateInterestRate(T input);


}
