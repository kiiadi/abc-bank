package com.abc;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.util.DefaultDateProvider;

import static org.junit.Assert.assertEquals;
import static com.abc.accounts.AccountType.CHECKING;
import static com.abc.accounts.AccountType.SAVINGS;
import static com.abc.accounts.AccountType.MAX_SAVINGS;

public class BankTest {
	
	private static final double DOUBLE_DELTA = 1e-15;
    private static Date lastTransactionDate; 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	Date currentDate = DefaultDateProvider.getInstance().now();
    	lastTransactionDate = new Date(currentDate.getTime() - (24 * 60 * 60 * 1000));
    }
    
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testDepositOnCheckingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(CHECKING);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.getAccountBalance().setDate(lastTransactionDate);        
        
        checkingAccount.deposit(100.0, lastTransactionDate);

        assertEquals(0.000273972602739, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testDepositOnSavingsAccount() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.getAccountBalance().setDate(lastTransactionDate);  ;
                
        savingsAccount.deposit(1500.0, lastTransactionDate);

        assertEquals(0.005479452054794521, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testDepositOnMaxSavingsAccount() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(MAX_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.getAccountBalance().setDate(lastTransactionDate);
        
        maxiSavingsAccount.deposit(3000.0, lastTransactionDate);

        assertEquals(0.4657534246575342, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void testTransferFromCheckingToSavingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(CHECKING);
        Account savingsAccount = new Account(SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount).openAccount(savingsAccount));

        checkingAccount.deposit(3000.0, DefaultDateProvider.getInstance().now());
        savingsAccount.deposit(4000.0, DefaultDateProvider.getInstance().now());
        checkingAccount.transferFunds(savingsAccount, 1000.0);

        assertEquals(5000.0, savingsAccount.getCurrentAccountBalance(), DOUBLE_DELTA);
        assertEquals(2000.0, checkingAccount.getCurrentAccountBalance(), DOUBLE_DELTA);
    }
}
