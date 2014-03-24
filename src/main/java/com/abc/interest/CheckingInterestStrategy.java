package com.abc.interest;

import com.abc.Constants;
import com.abc.Transaction;

import java.math.BigDecimal;
import java.util.List;


public class CheckingInterestStrategy implements InterestStrategy {
    @Override
    public BigDecimal interestEarned(BigDecimal amount, List<Transaction> transactions) {
        return amount.multiply(Constants.ONE_THOUSAND_TH);
    }
}
