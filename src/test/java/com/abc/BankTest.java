package com.abc;

import static org.junit.Assert.*;

import javax.swing.text.AttributeSet.CharacterAttribute;

import org.junit.Test;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        //create three customers
        Customer john = new Customer("John");
        Customer danny = new Customer("danny");
        Customer zhiheng = new Customer("Zhiheng");
        
        //john open two accounts, which are checking and saving account
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.SAVINGS));
        
        //danny open three accounts, which are checking, saving and maxi_saving account
        danny.openAccount(new Account(Account.SAVINGS));
        danny.openAccount(new Account(Account.CHECKING));
        danny.openAccount(new Account(Account.MAXI_SAVINGS));
        
        //zhiheng open one account, which is checking
        zhiheng.openAccount(new Account(Account.CHECKING));
        
        //add these three customers into our bank's customer list
        bank.addCustomer(john);
        bank.addCustomer(danny);
        bank.addCustomer(zhiheng);
        
        assertEquals("Customer Summary\n - John (2 accounts)\n - danny (3 accounts)\n - Zhiheng (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Account checkingAccount2 = new Account(Account.CHECKING);
        
        //Bill opens a checking account
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        
        //Cliton opens a checking account
        Customer cliton = new Customer("Cliton").openAccount(checkingAccount2);
        
        //add Bill and Cliton into our bank's customer list
        bank.addCustomer(bill);
        bank.addCustomer(cliton);

        //deposit from Bill
        checkingAccount.deposit(100.00);
        checkingAccount.deposit(100.00);
        
        //deposit from Cliton
        checkingAccount2.deposit(100.00);
        checkingAccount2.deposit(100.00);
        
        //withdraw from Bill
        checkingAccount.withdraw(100.00);
        
        //withdraw from Cliton
        checkingAccount2.withdraw(100.00);

        assertEquals(0.2, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingAccount = new Account(Account.SAVINGS);
        Account savingAccount2 = new Account(Account.SAVINGS);
        
        //Bill opens a saving account
        bank.addCustomer(new Customer("Bill").openAccount(savingAccount));
        
        //Cliton opens a saving account
        bank.addCustomer(new Customer("Cliton").openAccount(savingAccount2));
        
        //deposit from Bill
        savingAccount.deposit(1500.0);
        savingAccount.deposit(1500.0);
        
        //deposit from Cliton
        savingAccount2.deposit(1500.0);
        savingAccount2.deposit(1500.0);
        
        //withdraw from Bill
        savingAccount.withdraw(1500.0);
        
        //withdraw from Cliton
        savingAccount2.withdraw(1500.0);

        assertEquals(4.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    //Test the new feature of Maxi-Saving account which has withdrawls in past 10 days
    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        Account maxiAccount2 = new Account(Account.MAXI_SAVINGS);
        
        //Bill opens a maxi_saving account
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));
        
        //Clinton opens a maxi_saving account
        bank.addCustomer(new Customer("Cliton").openAccount(maxiAccount2));

        //deposit from Bill
        maxiAccount.deposit(3000.0);
        maxiAccount.deposit(3000.0);
        
        //deposit from Cliton
        maxiAccount2.deposit(3000.0);
        maxiAccount2.deposit(3000.0);
        
        //withdraw from Bill
        maxiAccount.withdraw(3000.0);
        
        //withdraw from Cliton
        maxiAccount2.withdraw(3000.0);

        assertEquals(6.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    //Test the new feature of Maxi-Saving account which has no withdrawls in past 10 days
    @Test
    public void maxi_savings_account2() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        Account maxiAccount2 = new Account(Account.MAXI_SAVINGS);
        
        //Bill opens a maxi_saving account
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));
        
        //Clinton opens a maxi_saving account
        bank.addCustomer(new Customer("Cliton").openAccount(maxiAccount2));

        //deposit from Bill
        maxiAccount.deposit(3000.0);
        maxiAccount.deposit(3000.0);
        
        //deposit from Cliton
        maxiAccount2.deposit(3000.0);
        maxiAccount2.deposit(3000.0);

        assertEquals(600.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    //Test the new feature of Maxi-Saving account which has no deposits
    @Test
    public void maxi_savings_account3() {
    	Bank bank = new Bank();
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        Account maxiAccount2 = new Account(Account.MAXI_SAVINGS);
        
        //Bill opens a maxi_saving account
        bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));
        
        //Clinton opens a maxi_saving account
        bank.addCustomer(new Customer("Cliton").openAccount(maxiAccount2));
        
        assertEquals(0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    //Test the daily interest earned by checking account
    @Test
    public void dailyInterest(){
    	 Bank bank = new Bank();
         Account checkingAccount = new Account(Account.CHECKING);
       
         //Bill opens a checking account
         Customer bill = new Customer("Bill").openAccount(checkingAccount);
             
         //add Bill and Cliton into our bank's customer list
         bank.addCustomer(bill);
        
         //deposit from Bill
         checkingAccount.deposit(100.00);
         
         //total interest earned by Bill'checking account
         double totalInterestPaid = bank.totalInterestPaid();
         
         //daily interest earned by Bill'checking account
         double dailyInterest = checkingAccount.dailyInterest();
         
         assertEquals(totalInterestPaid/365, dailyInterest, DOUBLE_DELTA);
    }
    

}
