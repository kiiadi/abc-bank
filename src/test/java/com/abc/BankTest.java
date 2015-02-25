package com.abc;

import java.math.BigDecimal;

import org.junit.Test;

import com.abc.BankConstants.AccountType;
import com.abc.BankConstants.TransactionType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        int customerId = 0;
        int accountId = 0;
        Customer john = new Customer(++customerId , "John");
        john.openAccount(new Account(++accountId ,  AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        int customerId = 0;
        int accountId = 0;
        TransactionManager transactionManager = bank.getTransactionManager();
        Account checkingAccount = new Account(++accountId , AccountType.CHECKING);
        Customer bill = new Customer(++customerId , "Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        transactionManager.transaction(bill, checkingAccount, TransactionType.DEPOSIT, new BigDecimal("100"));
        transactionManager.applyIntereset(bill);
//        assertEquals(0.1, bank.totalInterestPaid()., DOUBLE_DELTA);
        
        assertTrue(bank.totalInterestPaid().compareTo( new BigDecimal("0.1")) == 0 );
        
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        int customerId = 0;
        int accountId = 0;
        TransactionManager transactionManager = bank.getTransactionManager();
        Account savingAccount = new Account(++accountId , AccountType.SAVINGS);
        Customer bill = new Customer(++customerId , "Bill").openAccount(savingAccount);
        bank.addCustomer(bill);

        transactionManager.transaction(bill, savingAccount, TransactionType.DEPOSIT, new BigDecimal("1500"));
        transactionManager.applyIntereset(bill);
        assertTrue(bank.totalInterestPaid().compareTo( new BigDecimal("2")) == 0 );
    }

    @Test
    public void maxi_savings_account() {

        Bank bank = new Bank();
        int customerId = 0;
        int accountId = 0;
        TransactionManager transactionManager = bank.getTransactionManager();
        Account maxSavingAccount = new Account(++accountId , AccountType.MAXI_SAVINGS);
        Customer bill = new Customer(++customerId , "Bill").openAccount(maxSavingAccount);
        bank.addCustomer(bill);

        transactionManager.transaction(bill, maxSavingAccount, TransactionType.DEPOSIT, new BigDecimal("3000.0"));
        transactionManager.applyIntereset(bill);


        assertTrue(bank.totalInterestPaid().compareTo( new BigDecimal("170")) == 0 );
        
    }

}
