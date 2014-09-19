package com.abc.transaction;

import com.abc.transaction.impl.SimpleTransaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

public class TransactionTest {

  Transaction transaction;
  SimpleDateFormat sdf ;

  Date pastDate1;
  @Before
  public void setUp() throws Exception {
    sdf = new SimpleDateFormat("dd/MM/yyyy");
    pastDate1 = sdf.parse("21/08/2014");

    transaction = new SimpleTransaction(new BigDecimal(3000), pastDate1);

  }

  @Test
  public void testGetAmount() throws Exception {
    assertTrue("Transaction.getAmount not working properly", transaction.getAmount().doubleValue() == 3000);

  }

  @Test
  public void testGetTransactionDate() throws Exception {
    assertTrue("Customer.openAccount not working properly", transaction.getTransactionDate().equals(pastDate1));

  }
}