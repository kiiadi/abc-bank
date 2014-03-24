package com.abc.interest;

import com.abc.Transaction;
import com.abc.Utils;

import java.math.BigDecimal;
import java.util.List;

import static com.abc.Constants.*;


public class MaxiSavingsInterestStrategy implements InterestStrategy {

    @Override
    public BigDecimal interestEarned(BigDecimal amount, List<Transaction> transactions) {
        // last 10 days ago: today + 9 previous days
        BigDecimal rate = Utils.hasWithdrawalsForNdays(transactions, 9) ? ONE_THOUSAND_TH : FIVE_HUNDRED_TH;
        return amount.multiply(rate);
    }
}

