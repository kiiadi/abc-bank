package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.MaxiSavingsAccount;
import com.abc.accounts.SavingsAccount;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-12;

    @Test
    public void customerSummaryTest() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccountTest() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        Account checkingAccount = openCheckingAccount(bill);

        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(100 * .001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccountTest() {
        Bank bank = new Bank();
        Account savings = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savings));

        savings.deposit(1500.0);

        assertEquals(500*0.002/365 + 1000 * 0.001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountTest() {
        Bank bank = new Bank();
        Account checkingAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(3000 * .05/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void overdraftTest() {
        Bank bank = new Bank();
        Account checkingAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.withdraw(120.0);
    }
    
    @Test
    public void testTransferSuccess() {
        Bank bank = new Bank();
        
        Customer oscar = new Customer("Oscar");
        CheckingAccount checking = openCheckingAccount(oscar);
        SavingsAccount savings = openSavingsAccount(oscar);

        savings.deposit(1000.0);
        checking.deposit(300.0);
        boolean state = bank.transfer(savings, checking, 100.0);
        assertTrue(state);
        
        assertEquals(900, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals(400, checking.sumTransactions(), DOUBLE_DELTA);
    }
    
    @Test
    public void testTransferFailed() {
        Bank bank = new Bank();

        Customer oscar = new Customer("Oscar");

        CheckingAccount checking = openCheckingAccount(oscar);
        SavingsAccount savings = openSavingsAccount(oscar);
        
        savings.deposit(1000.0);
        checking.deposit(300.0);
        
        boolean state = bank.transfer(savings, checking, 1001.0);
        assertFalse(state);
        assertEquals(1000, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals(300, checking.sumTransactions(), DOUBLE_DELTA);
    }

    private CheckingAccount openCheckingAccount(Customer oscar) {
        CheckingAccount checking = new CheckingAccount();
        oscar.openAccount(checking);
        return checking;
    }

    private SavingsAccount openSavingsAccount(Customer oscar) {
        SavingsAccount savings = new SavingsAccount();
        oscar.openAccount(savings);
        return savings;
    }
    
    @Test
    public void testTotalInterestPaid() {
        Bank bank = new Bank();
        
        Customer oscar = new Customer("Oscar");
        
        bank.addCustomer(oscar);
        CheckingAccount checking = openCheckingAccount(oscar);
        SavingsAccount savings = openSavingsAccount(oscar);
        
        savings.deposit(1000.0);
        checking.deposit(300.0);
        assertEquals(1000*.001/365 + 300*.001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }
    
    @Test
    public void testTotalInterestPaidExcessSavings() {
        Bank bank = new Bank();
        Customer oscar = new Customer("Oscar");
        CheckingAccount checking = openCheckingAccount(oscar);
        SavingsAccount savings = openSavingsAccount(oscar);
                
        bank.addCustomer(oscar);

        
        savings.deposit(2000.0);
        checking.deposit(300.0);
        //interest: 1000*0.001 + 1000 * 0.002 + 300*0.001
        assertEquals(1000*0.001/365 + 1000 * 0.002/365 + 300*0.001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }
    
    @Test
    public void testTotalInterestPaidExcessMaxiNoWithdrawls() {
        Bank bank = new Bank();
        
        Customer oscar = new Customer("Oscar");
        
        bank.addCustomer(oscar);
        MaxiSavingsAccount maxi = openMaxiSavingsAccount(oscar);
        CheckingAccount checking = openCheckingAccount(oscar);
        
        maxi.deposit(2000.0);
        checking.deposit(300.0);
        
        //interest: 2000*0.05 + 300*0.001
        assertEquals(2000*0.05/365 + 300*0.001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }

    private MaxiSavingsAccount openMaxiSavingsAccount(Customer oscar) {
        MaxiSavingsAccount maxi = new MaxiSavingsAccount();
        oscar.openAccount(maxi);
        return maxi;
    }

    @Test
    public void testTotalInterestPaidExcessMaxiWithWithdrawls() {
        Bank bank = new Bank();
        
        Customer oscar = new Customer("Oscar");
        MaxiSavingsAccount maxi = openMaxiSavingsAccount(oscar);
        CheckingAccount checking = openCheckingAccount(oscar);
        
        bank.addCustomer(oscar);
        
        maxi.deposit(2000.0);
        checking.deposit(300.0);
        maxi.transactions.add(getTransaction(900, 5)); 
        maxi.transactions.add(getTransaction(-300, 3));

        /*
         * account has the following:
         *  900 - 5days ago
         *  600 - 2 days ago
         *  2600 - now
         *  
         *  Held balances and approx interest are
         *  900 - 3 days : 900 * (1+0.001/365)^2 - 900
         *  600 - 2 days : 600 * (1+0.001/365)^3 - 600
         *  2600 - 1 day : 2600  * 0.001/365
         */
        double maxiInterest = 2600  * 0.001/365 + 600 * Math.pow((1+0.001/365), 3) - 600 + 900 * Math.pow(1 + 0.001/365, 2) - 900;
        
        assertEquals(maxiInterest + 300*0.001/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }

    private Transaction getTransaction(double amount, int daysAgo) {
        Transaction base = new Transaction(amount) ;
        base.transactionDate = DateProvider.daysAgo(daysAgo);
        return base;
    }

    
}
