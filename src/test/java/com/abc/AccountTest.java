package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by serge on 2/13/14.
 */
public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void checkingAccount(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.CHECKING);
        assertEquals(Account.CHECKING, account.getAccountType());
        assertTrue(0 == account.transactions().size());
    }

    @Test
    public void savingAccount(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.SAVINGS);
        assertEquals(Account.SAVINGS, account.getAccountType());
        assertTrue(0 == account.transactions().size());
    }

    @Test
    public void testMaxiAccount(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.MAXI_SAVINGS);
        assertEquals(Account.MAXI_SAVINGS, account.getAccountType());
        assertTrue(0 == account.transactions().size());
    }

    @Test
    public void testDeposit() throws Exception {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.CHECKING);
        account.deposit(100.0);
        assertTrue(1 == account.transactions().size());
        assertEquals(100.0, account.transactions().get(0).amount, DOUBLE_DELTA);
    }

    @Test
    public void testNegativeDeposit() throws Exception {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.CHECKING);
        String eMessage=null;
        try{
            account.deposit(-100.0);
        }catch(IllegalArgumentException e){
            eMessage=e.getMessage();
        }
        assertTrue(0 == account.transactions().size());
        assertEquals(eMessage, "amount must be greater than zero");
        System.out.println("Deposit: "+eMessage);
    }

    @Test
    public void testWithdraw() throws Exception {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.SAVINGS);
        account.deposit(1000.0);
        assertEquals(1000.0, account.getAccountBalance(), DOUBLE_DELTA);
        account.withdraw(1000.0);
        assertEquals(0.0, account.getAccountBalance(), DOUBLE_DELTA);
        assertTrue(2 == account.transactions().size());
        assertEquals(-1000.0, account.transactions().get(1).amount, DOUBLE_DELTA);
    }

    @Test
    public void testWithdrawInsufficientAmount() throws Exception {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.SAVINGS);
        account.deposit(1000.0);
        assertEquals(1000.0, account.getAccountBalance(), DOUBLE_DELTA);
        String eMessage=null;
        try{
            account.withdraw(2000.0);
        }catch(IllegalArgumentException e){
            eMessage=e.getMessage();
        }
        assertEquals(1000.0, account.getAccountBalance(), DOUBLE_DELTA);
        assertTrue(1 == account.transactions().size());
        System.out.println("Withdrawal: "+eMessage);
    }

    @Test
    public void testNegativeWithdraw() throws Exception {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.SAVINGS);
        String eMessage=null;
        try{
            account.withdraw(-100.0);
        }catch(IllegalArgumentException e){
            eMessage=e.getMessage();
        }
        assertTrue(0 == account.transactions().size());
        assertEquals(eMessage, "amount must be greater than zero");
        System.out.println("Withdrawal: "+eMessage);
    }

    @Test
    public void testInterestEarnedCheckingNoInterest(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.CHECKING);
        account.deposit(1000.0);
        assertTrue(1 == account.transactions().size());
        assertEquals(1000.0, account.transactions().get(0).amount, DOUBLE_DELTA);
        assertEquals(0.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void testInterestEarnedCheckingOneYearInterest(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Account account = new Account(Account.CHECKING);
        DateProvider.getInstance().setFutureDate(-365);
        account.deposit(1000.0);
        assertTrue(1 == account.transactions().size());
        assertEquals(1000.0, account.transactions().get(0).amount, DOUBLE_DELTA);
        DateProvider.getInstance().reset();
        assertEquals(1.0, account.interestEarned(), DOUBLE_DELTA);
        assertEquals(1001.0, account.getAccountBalance(), DOUBLE_DELTA);
    }

    @Test
    public void testInterestEarnedSaving(){
    }

    @Test
    public void testInterestEarnedMaxi(){
    }
}
