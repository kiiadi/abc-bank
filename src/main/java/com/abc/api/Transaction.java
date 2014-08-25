package com.abc.api;

import java.math.BigDecimal;
import java.util.Date;

public interface Transaction {

    AccountId getAccountId();
    Date getDate();
    BigDecimal getAmount();



}
