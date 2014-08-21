package com.abc;

import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import com.abc.utilities.DateUtility;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: bradharper
 * Date: 8/17/14
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testDeposit() {
        Customer john = new Customer("John");
        john.openAccount(Account.AccountType.CHECKING, 1000.00);


        assertEquals("Account balance equals $1000.00", 1000.00, john.getAccounts().get(0).sumTransactions(), DOUBLE_DELTA);

        john.getAccounts().get(0).deposit(1000.00);

        assertEquals("Account balance equals $2000.00", 2000.00, john.getAccounts().get(0).sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void testWithdrawal() {
        Customer john = new Customer("John");
        SavingsAccount account = new SavingsAccount(1000.00, DateUtility.january(1));

        john.openAccount(account);

        assertEquals("Account balance equals $1000.00", 1000.00, john.getAccounts().get(0).sumTransactions(), DOUBLE_DELTA);

        john.getAccounts().get(0).withdraw(500.00);

        assertEquals("Account balance equals $500.00", 500.00, john.getAccounts().get(0).sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void testTransfer() {

        Account savings = new SavingsAccount(1000.00, DateUtility.january(1));
        Account checking = new CheckingAccount(100.00, DateUtility.january(1));

        assertEquals("Savings account balance equals $1000.00", 1000.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.sumTransactions(), DOUBLE_DELTA);

        checking.transferFrom(savings, 500.00);

        assertEquals("Savings account balance equals $500.00", 500.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals 5100.00", 600.00, checking.sumTransactions(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testTransferZeroBalance() {

        Account savings = new SavingsAccount(100.00, DateUtility.january(1));
        Account checking = new CheckingAccount(100.00, DateUtility.january(1));

        assertEquals("Savings account balance equals $100.00", 100.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.sumTransactions(), DOUBLE_DELTA);

        checking.transferFrom(savings, 100.07);

    }

    @Test
    public void testCheckingAccountInterestEarned() {
        Account checking = new CheckingAccount(2000.00, DateUtility.january(1));

        assertEquals("checking interest earned should be 1.2893024954835255", 1.2893024954835255, checking.interestEarned(), DOUBLE_DELTA);

    }

    @Test
    public void testSavingsAccountInterestEarned() {
        Account savings = new SavingsAccount(2000, DateUtility.january(1));

        assertEquals("savings interest earned should be 2.5802604747989335", 2.5802604747989335, savings.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void testMaxiAccountInterestEarned() {

        Account maxi = new MaxiSavingsAccount(2000, DateUtility.january(1));


        assertEquals("maxi interest earned should be 65.48933684315034", 65.48933684315034, maxi.interestEarned(), DOUBLE_DELTA);

        maxi.withdraw(100.00);//this should trigger our lower interest rate since it falls within our 10 day threshold.
        maxi.deposit(100.00);//this is just to restore our balance to the original for comparative purposes.

        assertEquals("Interest earned should be 1.2893024954835255", 1.2893024954835255, maxi.interestEarned(), DOUBLE_DELTA);

    }

    @Test
    public void testAccountBalanceForDate() {
        CheckingAccount account = new CheckingAccount(100.00, DateUtility.january(1));

        assertEquals("january sum should be $100.00", 100.00, account.transactionSumForDate(DateUtility.january(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.february(1));

        assertEquals("february sum should be $200.00", 200.00, account.transactionSumForDate(DateUtility.february(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.march(1));

        assertEquals("march sum should be $300.00", 300.00, account.transactionSumForDate(DateUtility.march(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.april(1));

        assertEquals("april sum should be $400.00", 400.00, account.transactionSumForDate(DateUtility.april(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.may(1));

        assertEquals("may sum should be $500.00", 500.00, account.transactionSumForDate(DateUtility.may(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.june(1));

        assertEquals("june sum should be $600.00", 600.00, account.transactionSumForDate(DateUtility.june(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.july(1));

        assertEquals("july sum should be $700.00", 700.00, account.transactionSumForDate(DateUtility.july(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.august(1));

        assertEquals("august sum should be $800.00", 800.00, account.transactionSumForDate(DateUtility.august(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.september(1));

        assertEquals("september sum should be $900.00", 900.00, account.transactionSumForDate(DateUtility.september(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.october(1));

        assertEquals("october sum should be $1000.00", 1000.00, account.transactionSumForDate(DateUtility.october(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.november(1));

        assertEquals("november sum should be $1100.00", 1100.00, account.transactionSumForDate(DateUtility.november(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.december(1));

        assertEquals("december sum should be $1200.00", 1200.00, account.transactionSumForDate(DateUtility.december(2)), DOUBLE_DELTA);

        account.deposit(100.00, DateUtility.january(1, DateUtility.DEFAULT_YEAR + 1));

        assertEquals("december sum should be $1300.00", 1300.00, account.transactionSumForDate(DateUtility.january(2, DateUtility.DEFAULT_YEAR + 1)), DOUBLE_DELTA);

        account.withdraw(100.00, DateUtility.january(3, DateUtility.DEFAULT_YEAR + 1));

        assertEquals("december sum should be $1200.00", 1200.00, account.transactionSumForDate(DateUtility.january(4, DateUtility.DEFAULT_YEAR + 1)), DOUBLE_DELTA);
    }

    @Test
    public void testCheckingAccountInterest() {
        CheckingAccount account = new CheckingAccount(1000.00, DateUtility.january(1));
    }

    @Test
    public void testSavingsAccountInterest() {
        SavingsAccount account = new SavingsAccount(1000.00, DateUtility.january(1));
    }

    @Test
    public void testMaxiSavingsAccountInterest() {
        MaxiSavingsAccount account = new MaxiSavingsAccount(1000.00, DateUtility.january(1));
    }

    @Test
    public void testCheckingAccountBalance() {
        CheckingAccount account = new CheckingAccount(1000.00, DateUtility.january(1));

        account.deposit(100, DateUtility.january(15));
        account.withdraw(10, DateUtility.january(15));
        account.deposit(100, DateUtility.february(15));
        account.deposit(1000, DateUtility.march(15));

        assertEquals(2190.00, account.sumTransactions(), DOUBLE_DELTA);
        assertTrue((2190.00 < account.availableBalance()));

        System.out.println("checking account balance --> " + account.availableBalance());
    }

    @Test
    public void testSavingsAccountBalance() {
        SavingsAccount account = new SavingsAccount(1000.00, DateUtility.january(1));

        account.deposit(100, DateUtility.january(15));
        account.withdraw(10, DateUtility.january(15));
        account.deposit(100, DateUtility.february(15));
        account.deposit(1000, DateUtility.march(15));

        assertEquals(2190.00, account.sumTransactions(), DOUBLE_DELTA);
        assertTrue((2190.00 < account.availableBalance()));

    }

    @Test
    public void testMaxiSavingsAccountBalance() {
        MaxiSavingsAccount account = new MaxiSavingsAccount(1000.00, DateUtility.january(1));

        account.deposit(100, DateUtility.january(15));
        account.withdraw(10, DateUtility.january(15));
        account.deposit(100, DateUtility.february(15));
        account.deposit(1000, DateUtility.march(15));

        assertEquals(2190.00, account.sumTransactions(), DOUBLE_DELTA);
        assertTrue((2190.00 < account.availableBalance()));
    }

}
