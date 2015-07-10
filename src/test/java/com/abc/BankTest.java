package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.MaxiSavingsAccount;
import com.abc.accounts.SavingsAccount;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

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
        Account checkingAccount = openSavingsAccount(bill);

        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccountTest() {
        Bank bank = new Bank();
        Account checkingAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountTest() {
        Bank bank = new Bank();
        Account checkingAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
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
        bank.transfer(savings, checking, 100.0);
        
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
        
        bank.transfer(savings, checking, 1001.0);
        
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
        //interest: 1000*.1 + 300*.1
        assertEquals(1.3, bank.totalInterestPaid(), DOUBLE_DELTA);
        
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
        assertEquals(3.3, bank.totalInterestPaid(), DOUBLE_DELTA);
        
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
        assertEquals(100.3, bank.totalInterestPaid(), DOUBLE_DELTA);
        
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
        
        Transaction testTransaction = new Transaction(-300) {
            @Override
            public Date getTransactionDate() {
                long inLast10Days = System.currentTimeMillis() - 86_400_000 * 3;
                return new Date(inLast10Days);
            }
        };
        
        
        maxi.transactions.add(testTransaction);
        System.out.println(maxi.transactions);
        System.out.println(checking.transactions);
        
        //interest: 1700*0.001 + 300*0.001
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }

    
}
