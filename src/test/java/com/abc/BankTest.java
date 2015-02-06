package com.abc;

import static com.abc.domain.AccountType.CHECKING;
import static com.abc.domain.AccountType.MAXI_SAVINGS;
import static com.abc.domain.AccountType.SAVINGS;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.abc.domain.Account;
import com.abc.domain.Bank;
import com.abc.domain.Customer;
import com.abc.domain.Transaction;
import com.abc.service.AccountService;
import com.abc.service.BankManager;
import com.abc.service.CustomerService;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    
    private static BankManager bankManager;
    private static CustomerService customerService;
    private static AccountService accountService;
    
    @BeforeClass
    public static void setUp() {
    	bankManager = new BankManager();
    	customerService = new CustomerService();
    	accountService = new AccountService();

    	bankManager.setCustomerService(customerService);
    	customerService.setAccountService(accountService);
    }
    
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Account checkingAccount = new Account(CHECKING);
        
        bankManager.openAccount(bank, john, checkingAccount);

        assertEquals("Customer Summary\n - John (1 account)", bankManager.getCustomerSummary(bank));
    }

    
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(CHECKING);
        Customer bill = new Customer("Bill");
        customerService.addAccount(bill, checkingAccount);
        bankManager.addCustomer(bank, bill);
        accountService.deposit(checkingAccount, 100.0);

        assertEquals(0.1, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }

    
    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(SAVINGS);
        bankManager.openAccount(bank, new Customer("Bill"), savingsAccount);
        accountService.deposit(savingsAccount, 1500.0);

        assertEquals(2.0, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(MAXI_SAVINGS);
        bankManager.openAccount(bank, new Customer("Bill"), maxiSavingsAccount);
        accountService.deposit(maxiSavingsAccount, 3000.0);

        assertEquals(150.0, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }
    
    @Ignore
    @Test
    public void maxSavingAccountNoWithdrawlInLast10Days() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(MAXI_SAVINGS);
        bankManager.openAccount(bank, new Customer("Bill"), maxiSavingsAccount);
        accountService.deposit(maxiSavingsAccount, 3000.0);

        assertEquals(3.0, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiSavingAccount_WithdrawlInLast10Days() {

        
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(MAXI_SAVINGS);
        bankManager.openAccount(bank, new Customer("Bill"), maxiSavingsAccount);
        accountService.deposit(maxiSavingsAccount, 3000.0);
        accountService.withdraw(maxiSavingsAccount, 2000.0);

        assertEquals(1.0, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiSavingAccount_NO_WithdrawlInLast10Days() {

        
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(MAXI_SAVINGS);
        bankManager.openAccount(bank, new Customer("Bill"), maxiSavingsAccount);
        accountService.deposit(maxiSavingsAccount, 3000.0);
        accountService.withdraw(maxiSavingsAccount, 2000.0);
        changeDateOfTransactions(maxiSavingsAccount);
        
        assertEquals(50.0, bankManager.getTotalInterestPaid(bank), DOUBLE_DELTA);
    }
    
    private void changeDateOfTransactions(Account maxiSavingsAccount) {  
        List<Transaction> transactions = maxiSavingsAccount.getTransactions();
        Transaction transaction = transactions.get(1);
        transaction.setTransactionDate(java.sql.Date.valueOf("2015-01-01"));
    }
}
