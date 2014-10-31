package com.abc.unittests;

import com.abc.impl.manager.DefaultAccountManager;
import com.abc.model.api.AccountManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.Transaction;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class AccountBalanceManipulationTest {

    private Account account;
    private AccountManager accountManager = new DefaultAccountManager();

    @Before
    public void setUpAccount() {

        account = accountManager.openCheckingAccount(new Customer("Customer 1"),"Account 1");

    }

    @Test
    public void depositMoneyToAccount() {
        BigDecimal amountOfMoneyToDeposit = new BigDecimal(1000.11);

        accountManager.depositMoneyToAccount(account,amountOfMoneyToDeposit);

        assertEquals(1, account.getTransactions().size());

        Transaction transaction = account.getTransactions().get(0);
        assertEquals(amountOfMoneyToDeposit,transaction.getAmount());
        assertEquals(Transaction.Type.CREDIT,transaction.getType());
        assertNotNull(transaction.getCreatedOn());
    }

    @Test
    public void withdrawMoneyFromAccount() {
        BigDecimal amountOfMoneyToWithdraw = new BigDecimal(1000.11);

        accountManager.withdrawMoneyFromAccount(account,amountOfMoneyToWithdraw);

        assertEquals(1, account.getTransactions().size());

        Transaction transaction = account.getTransactions().get(0);
        assertEquals(amountOfMoneyToWithdraw,transaction.getAmount());
        assertEquals(Transaction.Type.DEBIT,transaction.getType());
        assertNotNull(transaction.getCreatedOn());
    }

    //@todo test over withdrawal. test summing up transactions

}
