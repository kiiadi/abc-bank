package com.abc.api;

import java.math.BigDecimal;
import java.util.List;

public interface AccountStatement {
    Account getAccount();
    List<Transaction> getTransactions();
    BigDecimal getBalance();
}
