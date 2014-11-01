package com.abc.unittests;

import com.abc.impl.DefaultAccountManager;
import com.abc.model.api.AccountManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.Transaction;
import static org.junit.Assert.*;

import com.abc.model.entity.Transfer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class AccountBalanceManipulationTest {

    private AccountManager accountManager = new DefaultAccountManager();
    private final double DELTA = 0.000001;

    @Before
    public void clearAccountManager() {
        accountManager.getAllAccounts().clear();
    }

    @Test
    public void depositMoneyToAccount() {
        Account account = accountManager.openCheckingAccount(new Customer("Customer 1"),"Account 1");
        BigDecimal amountOfMoneyToDeposit = new BigDecimal("1000.11");

        accountManager.depositMoneyToAccount(account,amountOfMoneyToDeposit);

        assertEquals(1, account.getTransactions().size());

        //check the transaction
        Transaction transaction = account.getTransactions().get(0);
        assertEquals(amountOfMoneyToDeposit, transaction.getAmount());
        assertEquals(Transaction.Type.CREDIT, transaction.getType());
        assertNotNull(transaction.getCreatedOn());
        //and check total balance
        assertEquals(amountOfMoneyToDeposit,account.getBalance());
    }

    //Few tests on withdrawal follows
    @Test(expected = AccountManager.AttemptedAccountOverflow.class)
    public void withdrawMoneyFromAccount_askedForTooMuch() {
        Account account = accountManager.openCheckingAccount(new Customer("Customer 1"),"Account 1");
        BigDecimal amountOfMoneyToWithdraw = new BigDecimal("1000.11");

        accountManager.withdrawMoneyFromAccount(account,amountOfMoneyToWithdraw);
    }

    @Test
    public void withdrawMoneyFromAccount() {
        Account account = accountManager.openCheckingAccount(new Customer("Customer 1"),"Account 1");
        BigDecimal initialBalance = new BigDecimal("2000");
        BigDecimal amountOfMoneyToWithdraw = new BigDecimal("1000.11");

        accountManager.depositMoneyToAccount(account, initialBalance);
        accountManager.withdrawMoneyFromAccount(account,amountOfMoneyToWithdraw);

        assertEquals(2, account.getTransactions().size());

        //check the transaction
        Transaction transaction = account.getTransactions().get(1);
        assertEquals(amountOfMoneyToWithdraw, transaction.getAmount());
        assertEquals(Transaction.Type.DEBIT, transaction.getType());
        assertNotNull(transaction.getCreatedOn());
        //and check total balance
        assertEquals(initialBalance.subtract(amountOfMoneyToWithdraw),account.getBalance());
    }

    @Test
    public void testSummingUpTransactions() {
        Account account = accountManager.openMaxiSavingsAccount(new Customer("Customer 1"),"Account 1");
        accountManager.depositMoneyToAccount(account, new BigDecimal("100.00"));
        accountManager.withdrawMoneyFromAccount(account, new BigDecimal("30.00"));
        accountManager.depositMoneyToAccount(account, new BigDecimal("20.00"));

        assertEquals(90.0,account.getBalance().doubleValue(),DELTA);
    }

    //Now follow tests for transfer between customer's accounts
    @Test
    public void moveMoneyFromOneAccountToAnother() {
        Customer customer = new Customer("Customer 1");
        String accountOneName = "Account 1";
        String accountTwoName = "Account 2";
        Account accountOne = accountManager.openCheckingAccount(customer,accountOneName);
        Account accountTwo = accountManager.openSavingsAccount(customer, accountTwoName);

        accountManager.depositMoneyToAccount(accountOne, new BigDecimal("200.00"));
        accountManager.depositMoneyToAccount(accountTwo,new BigDecimal("1000.00"));

        Transfer transfer = Transfer.CreateInternalTransfer(customer,accountOneName,accountTwoName);

        BigDecimal transferredAmount = new BigDecimal("100.00");
        transfer.setAmount(transferredAmount);

        accountManager.transferMoney(transfer);

        //we expect one new transaction on each side
        assertEquals(2,accountOne.getTransactions().size());
        assertEquals(2,accountTwo.getTransactions().size());

        //one is debit the other credit
        Transaction debitTransaction = accountOne.getTransactions().get(1);
        Transaction creditTransaction = accountTwo.getTransactions().get(1);

        assertEquals(Transaction.Type.DEBIT,debitTransaction.getType());
        assertEquals(Transaction.Type.CREDIT,creditTransaction.getType());

        assertEquals(transferredAmount,debitTransaction.getAmount());
        assertEquals(transferredAmount,creditTransaction.getAmount());

        //check the total balances
        assertEquals(new BigDecimal("100.00"),accountOne.getBalance());
        assertEquals(new BigDecimal("1100.00"),accountTwo.getBalance());
    }

    @Test(expected = Transfer.AccountNotFound.class)
    public void moveMoneyFromNonExistingAccount() {
        Customer customer = new Customer("Customer 1");
        accountManager.openCheckingAccount(customer,"Existing account");
        Transfer transfer = Transfer.CreateInternalTransfer(customer,"Non existing account","Existing account");
        transfer.setAmount(new BigDecimal("1000"));
        accountManager.transferMoney(transfer);
    }

    @Test(expected = Transfer.AccountNotFound.class)
    public void moveMoneyToNonExistingAccount() {
        Customer customer = new Customer("Customer 1");
        accountManager.openCheckingAccount(customer,"Existing account");
        Transfer transfer = Transfer.CreateInternalTransfer(customer,"Existing account","Non existing account");
        transfer.setAmount(new BigDecimal("1000"));
        accountManager.transferMoney(transfer);
    }

    @Test(expected = AccountManager.AttemptedAccountOverflow.class)
    public void moveMoneyFromOneAccountToAnother_askedForMoreThanBalance() {
        Customer customer = new Customer("Customer 1");
        String accountOneName = "Account 1";
        String accountTwoName = "Account 2";
        accountManager.openMaxiSavingsAccount(customer,accountOneName);
        accountManager.openCheckingAccount(customer,accountTwoName);

        Transfer transfer = Transfer.CreateInternalTransfer(customer, accountOneName, accountTwoName);
        transfer.setAmount(new BigDecimal("1000.00"));
        accountManager.transferMoney(transfer);
    }

    @Test(expected = AccountManager.AttemptedNegativeAmountTransfer.class)
    public void moveMoneyFromOneAccountToAnother_askedForNegativeAmount() {
        Customer customer = new Customer("Customer 1");
        String accountOneName = "Account 1";
        String accountTwoName = "Account 2";
        accountManager.openMaxiSavingsAccount(customer,accountOneName);
        accountManager.openCheckingAccount(customer,accountTwoName);

        Transfer transfer = Transfer.CreateInternalTransfer(customer,accountOneName, accountTwoName);
        transfer.setAmount(new BigDecimal("-1000.00"));

        accountManager.transferMoney(transfer);
    }

}
