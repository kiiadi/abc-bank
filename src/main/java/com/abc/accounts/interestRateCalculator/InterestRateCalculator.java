package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;

import java.util.List;

public interface InterestRateCalculator {
    double calculateInterestRate(List<Transaction> transactions, double totalAmmount);


}
