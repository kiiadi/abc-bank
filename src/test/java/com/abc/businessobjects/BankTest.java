package com.abc.businessobjects;

import com.abc.process.Transaction;
import com.common.utils.DateProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private Bank bank;
    private Account checkingAccount;
    private Account savingsAccount;
    private Account maxiSavingsAccount;

    @Before
    public void setUp() {
        bank = new Bank();
        checkingAccount = new Account(AccountType.CHECKING);
        savingsAccount = new Account(AccountType.SAVINGS);
        maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
    }

    @Test
    public void customerSummary() {
        Customer john = new Customer("John");
        john.openAccount(checkingAccount);
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryWithMultipleAccounts() {
        Customer john = new Customer("John");
        john.openAccount(checkingAccount);
        john.openAccount(savingsAccount);
        john.openAccount(maxiSavingsAccount);
        bank.addCustomer(john);

        //note plural account(s) - word
        assertEquals("Customer Summary\n - John (3 accounts)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountWithFivePercentSimple() {
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountWithFivePercentRateOldDeposit() throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateProvider.getInstance().now());
        cal.add(Calendar.DATE, -11);
        Date dateOutOfRange = cal.getTime();

        Transaction withdrawal = new Transaction(-3000.0, dateOutOfRange);
        Transaction deposit = new Transaction(5000.0);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(withdrawal);
        transactions.add(deposit);

        Account maxiAccount = new Account(AccountType.MAXI_SAVINGS, transactions);
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));

        assertEquals(100.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountWithDefaultRateDepositInRange() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateProvider.getInstance().now());
        cal.add(Calendar.DATE, -10);
        Date dateInRange = cal.getTime();

        Transaction withdrawal = new Transaction(-3000.0, dateInRange);
        Transaction deposit = new Transaction(5000.0);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(withdrawal);
        transactions.add(deposit);

        Account maxiAccount = new Account(AccountType.MAXI_SAVINGS, transactions);
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));


        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testGetFirstCustomer(){
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        bank.addCustomer(new Customer("Joe").openAccount(checkingAccount));
        bank.addCustomer(new Customer("Jill").openAccount(savingsAccount));

        assertTrue("First customer is not Bill!", bank.getFirstCustomerName().equals("Bill"));
    }

    @After
    public void tearDown() {
        bank = new Bank();
        checkingAccount = new Account(AccountType.CHECKING);
        savingsAccount = new Account(AccountType.SAVINGS);
        maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
    }
}
