package com.abc.bank;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.abc.bank.Bank;
import com.abc.bank.account.Account;
import com.abc.bank.account.AccountType;
import com.abc.bank.customer.Customer;
import com.abc.bank.customer.Customers;
import com.abc.bank.customer.CustomersImpl;
import com.abc.bank.exception.DuplicateCustomerException;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private Bank bank;
    private Customers customers;
    private Customer customer ;

    @Before
    public void setup(){
        customers = new CustomersImpl();
        bank = new Bank(customers);
        String id = UUID.randomUUID().toString();
        customer  = new Customer(id,"John");;
    }

    @Test
    public void checkingAccount() throws DuplicateCustomerException {
        Account checkingAccount = customer.openAccount(AccountType.checking);
        bank.addCustomer(customer);
        checkingAccount.deposit(100.0);
        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() throws DuplicateCustomerException {
        Account savingsAccount =  customer.openAccount(AccountType.savings);
        bank.addCustomer(customer);
        savingsAccount.deposit(1500.0);
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() throws DuplicateCustomerException {
        Account maxiSavingsAccount =
                customer.openAccount(AccountType.maxiSavings);
        bank.addCustomer(customer);
        maxiSavingsAccount.deposit(3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}