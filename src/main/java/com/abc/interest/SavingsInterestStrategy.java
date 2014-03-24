package com.abc.interest;

import com.abc.Constants;
import com.abc.Transaction;

import java.math.BigDecimal;
import java.util.List;


public class SavingsInterestStrategy implements InterestStrategy {


    @Override
    public BigDecimal interestEarned(BigDecimal amount, List<Transaction> transactions) {
        if (amount.compareTo(Constants.THOUSAND) <= 0)
            return amount.multiply(Constants.ONE_THOUSAND_TH);
        else
            return amount.subtract(Constants.THOUSAND).multiply(Constants.TWO_THOUSAND_TH).add(BigDecimal.ONE);
    }
}
