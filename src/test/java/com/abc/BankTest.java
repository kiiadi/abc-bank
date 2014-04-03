package com.abc;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private static Date initialDate; 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	Date currentDate = DateProvider.getInstance().now();
    	long initialTime = currentDate.getTime() - (24 * 60 * 60 * 1000); // 1 day earlier
    	initialDate = new Date(initialTime);
    }
    
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(AccountFactory.createAccount(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = AccountFactory.createAccount(AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0, initialDate);

        assertEquals(0.000273972602739, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = AccountFactory.createAccount(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0, initialDate);

        assertEquals(0.005479452054794521, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = AccountFactory.createAccount(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000.0, initialDate);

        assertEquals(0.4657534246575342, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void transfer_accounts() {
        Bank bank = new Bank();
        Account checkingAccount = AccountFactory.createAccount(AccountType.CHECKING);
        Account savingsAccount = AccountFactory.createAccount(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount).openAccount(savingsAccount));

        checkingAccount.deposit(3000.0);
        savingsAccount.deposit(4000.0);
        checkingAccount.transferFunds(savingsAccount, 1000.0);

        assertEquals(5000.0, savingsAccount.getCurrentBalance(), DOUBLE_DELTA);
        assertEquals(2000.0, checkingAccount.getCurrentBalance(), DOUBLE_DELTA);
    }
    

}
