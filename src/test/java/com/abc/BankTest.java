package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest { 
   
	private static final double DOUBLE_DELTA = 1e-15;
   
   @Test 
   public void customerSummary() { 
       Bank bank = new Bank(); 
       Customer john = new Customer("John"); 
       john.openAccount(new Account(Account.CHECKING)); 
       bank.addCustomer(john); 
       assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary()); 
   } 
   
   @Test 
   public void checkingAccount() { 
       Bank bank = new Bank(); 
       Account checkingAccount = new Account(Account.CHECKING); 
       Customer bill = new Customer("Bill").openAccount(checkingAccount); 
       bank.addCustomer(bill); 
       checkingAccount.deposit(100.0); 
       assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA); 
   } 

   @Test 
   public void savings_account() { 
       Bank bank = new Bank(); 
       Account savingsAccount = new Account(Account.SAVINGS); 
       bank.addCustomer(new Customer("Bill").openAccount(savingsAccount)); 
       savingsAccount.deposit(1500.0); 
       assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA); 
   } 

   @Test 
   public void maxi_savings_account() { 
       Bank bank = new Bank(); 
       Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS); 
       bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount )); 
       maxiSavingsAccount.deposit(3000.0); 
       assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
   } 
   
   @Test 
   public void dailyInterestAccrual() { 
       
	   Bank bank = new Bank(); 
       
       Account checkingAccount = new Account(Account.CHECKING); 
       Customer bill = new Customer("Mike").openAccount(checkingAccount); 
       bank.addCustomer(bill); 
       checkingAccount.deposit(1000000.0); 
       
       Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS); 
       bank.addCustomer(new Customer("Leo").openAccount(maxiSavingsAccount )); 
       maxiSavingsAccount.deposit(30000000.0); 
       
       Account savingsAccount = new Account(Account.SAVINGS); 
       bank.addCustomer(new Customer("Shelly").openAccount(savingsAccount)); 
       savingsAccount.deposit(15000000.0); 
       
       assertEquals(4194.52, bank.totalDailyInterestAccrued(), 0.0099);
   } 
   
} 
