package com.abc.transaction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Archana on 9/18/14.
 */
public interface Transaction {
  BigDecimal getAmount();

  Date getTransactionDate();
}
