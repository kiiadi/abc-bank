package com.abc;

import com.abc.account.transaction.Transaction;
import com.abc.account.transaction.TransactionType;
import com.abc.utils.DateUtils;
import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void createTransaction() {

        Transaction t = null;

        boolean iaeCaught = false;

        try {
            t = new Transaction(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
            iaeCaught = true;
        } finally {
            assertTrue(iaeCaught);
        }

        iaeCaught = false;

        try {
            t = new Transaction(BigDecimal.ZERO);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            iaeCaught = true;
        } finally {
            assertTrue(iaeCaught);
        }

        BigDecimal amount = null;
        amount = new BigDecimal("-105678395.00");

        t = new Transaction(amount);

        assertEquals(amount, t.getAmount());
        assertEquals(TransactionType.DEBIT, t.getType());
        assertEquals(TransactionType.DEBIT.getCode(), t.getType().getCode());
        assertEquals(TransactionType.DEBIT.getDescription(), t.getType().getDescription());
        assertEquals(
                " withdrawal ($105,678,395.00)\n", t.statement());

        System.out.println(t.statement());

        amount = new BigDecimal("10000.00");
        t = new Transaction(amount);

        assertEquals(amount, t.getAmount());
        assertEquals(TransactionType.CREDIT, t.getType());
        assertEquals(TransactionType.CREDIT.getCode(), t.getType().getCode());
        assertEquals(TransactionType.CREDIT.getDescription(), t.getType().getDescription());

        assertEquals(
                " deposit $10,000.00\n", t.statement());

        System.out.println(t.statement());

    }

}
