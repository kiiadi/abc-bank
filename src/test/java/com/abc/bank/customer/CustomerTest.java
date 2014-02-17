package com.abc.bank.customer;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abc.bank.account.Account;
import com.abc.bank.account.AccountType;
import com.abc.bank.customer.Customer;
import com.abc.bank.exception.InsufficuentFundsException;
import com.abc.bank.exception.NoSuchAccountException;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1e-15;
    private Customer customer ;

    @Before
    public void setup(){
        String id = UUID.randomUUID().toString();
        customer  = new Customer(id,"John");;
    }

    @Test
    public void testCustomerStatementGeneration() throws InsufficuentFundsException{

        Account checkingAccount = customer.openAccount(AccountType.checking);
        Account savingsAccount = customer.openAccount(AccountType.savings);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        //the map's values won't necessarily be returned in insertion order
        boolean option1 =
                customer.getStatement().equals("Statement for John\n" +
                        "\n" +
                        "Savings Account\n" +
                        "  deposit $4,000.00\n" +
                        "  withdrawal $200.00\n" +
                        "Total $3,800.00\n" +
                        "\n" +
                        "Checking Account\n" +
                        "  deposit $100.00\n" +
                        "Total $100.00\n" +
                        "\n" +
                        "Total In All Accounts $3,900.00" );
        boolean option2 =  customer.getStatement().equals("Statement for John\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00");
        
        Assert.assertTrue(option1 || option2);
    }

    @Test
    public void testOneAccount(){
        customer.openAccount(AccountType.savings);
        assertEquals(1, customer.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        customer.openAccount(AccountType.savings);
        customer.openAccount(AccountType.checking);
        assertEquals(2, customer.getNumberOfAccounts());
    }

    @Test
    public void transferFromSavingsToChecking() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount(AccountType.savings);
        customer.openAccount(AccountType.checking);
        double savingsInitial = 1000;
        customer.getAccount(AccountType.savings).deposit(savingsInitial);
        customer.transfer(AccountType.savings, AccountType.checking, savingsInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.savings).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.checking).getBalance(), DOUBLE_DELTA);
    }

    @Test
    public void transferFromCheckingToSavings() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount((AccountType.checking));
        customer.openAccount((AccountType.savings));
        double checkingInitial = 1000;
        customer.getAccount(AccountType.checking).deposit(checkingInitial);
        customer.transfer(AccountType.checking, AccountType.savings, checkingInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.checking).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.savings).getBalance(), DOUBLE_DELTA);
    }

    @Test
    public void transferFromCheckingToMaxiSavings() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount(AccountType.checking);
        customer.openAccount(AccountType.maxiSavings);
        double checkingInitial = 1000;
        customer.getAccount(AccountType.checking).deposit(checkingInitial);
        customer.transfer(AccountType.checking, AccountType.maxiSavings, checkingInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.checking).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.maxiSavings).getBalance(), DOUBLE_DELTA);
    }

    @Test
    public void transferFromMaxiSavingsToChecking() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount(AccountType.maxiSavings);
        customer.openAccount(AccountType.checking);
        double maxiSavingsInitial = 1000;
        customer.getAccount(AccountType.maxiSavings).deposit(maxiSavingsInitial);
        customer.transfer(AccountType.maxiSavings, AccountType.checking, maxiSavingsInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.maxiSavings).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.checking).getBalance(), DOUBLE_DELTA);
    }

    @Test
    public void transferFromMaxiSavingsToSavings() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount(AccountType.maxiSavings);
        customer.openAccount(AccountType.savings);
        double maxiSavingsInitial = 1000;
        customer.getAccount(AccountType.maxiSavings).deposit(maxiSavingsInitial);
        customer.transfer(AccountType.maxiSavings, AccountType.savings, maxiSavingsInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.maxiSavings).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.savings).getBalance(), DOUBLE_DELTA);
    }

    @Test
    public void transferFromSavingsToMaxiSavings() throws InsufficuentFundsException, NoSuchAccountException{
        customer.openAccount(AccountType.savings);
        customer.openAccount(AccountType.maxiSavings);
        double savingsInitial = 1000;
        customer.getAccount(AccountType.savings).deposit(savingsInitial);
        customer.transfer(AccountType.savings, AccountType.maxiSavings, savingsInitial);
        Assert.assertEquals(0,customer.getAccount(AccountType.savings).getBalance(), DOUBLE_DELTA);
        Assert.assertEquals(1000,customer.getAccount(AccountType.maxiSavings).getBalance(), DOUBLE_DELTA);
    }

    @Test(expected = NoSuchAccountException.class)
    public void getAccountTHrowsIfCustomerDoesNotHaveAccount() throws NoSuchAccountException{
        customer.getAccount(AccountType.savings);
    }



    @Test
    public void testThreeAcounts() {
        customer.openAccount(AccountType.savings);
        customer.openAccount(AccountType.checking);
        customer.openAccount(AccountType.maxiSavings);
        assertEquals(3, customer.getNumberOfAccounts());
    }
}