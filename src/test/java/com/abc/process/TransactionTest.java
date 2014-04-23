package com.abc.process;

import com.abc.businessobjects.Account;
import com.abc.businessobjects.AccountType;
import com.abc.businessobjects.Customer;
import com.abc.process.Transaction;
import com.common.utils.DateProvider;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }

    @Test
    public void transactionWithTransactions(){
        Transaction withdrawal = new Transaction(3000.0, DateProvider.getInstance().now());

        assertTrue(withdrawal instanceof Transaction);

        assertTrue("Amounts don't match!", 3000.0 == withdrawal.getAmount());
        assertTrue("Transaction Date doesn't match!", withdrawal.getTransactionDate().compareTo(DateProvider.getInstance().now()) == 0 );
    }
}
