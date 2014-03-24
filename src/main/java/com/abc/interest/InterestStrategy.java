package com.abc.interest;

import com.abc.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface InterestStrategy {
    BigDecimal interestEarned(BigDecimal amount, List<Transaction> transactions);
}
