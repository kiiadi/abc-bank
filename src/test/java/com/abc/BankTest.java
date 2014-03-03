package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BankTest {

    @Test
    public void addAccount() {

        Bank bank = new Bank();
        Customer john = null;

        try {
            bank.addCustomer(john);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            john = new Customer(null);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        john = new Customer("John");

        try {
            john.openAccount(null);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        john.openAccount(new CheckingAccount());

        assertEquals("John", john.getName());
        assertEquals(1, john.getNumberOfAccounts());
    }

    @Test
    public void oneAccount() {

        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        john.openAccount(new CheckingAccount());

        assertEquals("John", john.getName());
        assertEquals(1, john.getNumberOfAccounts());

        System.out.println(bank.customerSummary());

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void twoAccounts() {

        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        john.openAccount(new CheckingAccount());
        john.openAccount(new CheckingAccount());

        assertEquals("John", john.getName());
        assertEquals(2, john.getNumberOfAccounts());

        System.out.println(bank.customerSummary());

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    @Test
    public void fiveAccounts() {

        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        john.openAccount(new CheckingAccount());
        john.openAccount(new CheckingAccount());
        john.openAccount(new SavingsAccount());
        john.openAccount(new MaxiSavingsAccount());
        john.openAccount(new MaxiSavingsAccount());

        assertEquals("John", john.getName());
        assertEquals(5, john.getNumberOfAccounts());

        System.out.println(bank.customerSummary());

        assertEquals("Customer Summary\n - John (5 accounts)", bank.customerSummary());
    }

    @Test
    public void multipleCustomers() {

        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        john.openAccount(new CheckingAccount());
        john.openAccount(new CheckingAccount());
        john.openAccount(new SavingsAccount());
        john.openAccount(new MaxiSavingsAccount());
        john.openAccount(new MaxiSavingsAccount());

        assertEquals("John", john.getName());
        assertEquals(5, john.getNumberOfAccounts());

        Customer david = new Customer("David");
        bank.addCustomer(david);
        david.openAccount(new CheckingAccount());
        david.openAccount(new SavingsAccount());
        david.openAccount(new MaxiSavingsAccount());

        assertEquals("David", david.getName());
        assertEquals(3, david.getNumberOfAccounts());

        Customer none = new Customer("None");
        bank.addCustomer(none);
        none.openAccount(new CheckingAccount());
        none.openAccount(new SavingsAccount());
        none.openAccount(new MaxiSavingsAccount());
        none.openAccount(new MaxiSavingsAccount());

        assertEquals("None", none.getName());
        assertEquals(4, none.getNumberOfAccounts());

        System.out.println(bank.customerSummary());

        assertEquals("Customer Summary\n - John (5 accounts)"
                + "\n - David (3 accounts)"
                + "\n - None (4 accounts)", bank.customerSummary());
    }

    @Test
    public void firstCustomerName() {
        
        Bank bank = new Bank();
        assertNull(bank.getFirstCustomerName());
  
        Customer john = new Customer("John");
        bank.addCustomer(john);
        
        assertEquals("John", bank.getFirstCustomerName());
    }
    
    @Test
    public void totalInterestPaid() {
        
        Bank bank = new Bank();
 
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        Customer ted = new Customer("Ted");
        Customer henry = new Customer("Henry");
        
        bank.addCustomer(ted);
        bank.addCustomer(henry);
        
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        henry.openAccount(maxiSavingsAccount);
        
        maxiSavingsAccount.deposit(new BigDecimal("4000.00"));
        checkingAccount.deposit(new BigDecimal("1536.00"));
        savingsAccount.deposit(new BigDecimal("1000.00"));
        
        checkingAccount = new CheckingAccount();
        savingsAccount = new SavingsAccount();
        maxiSavingsAccount = new MaxiSavingsAccount();
        
        ted.openAccount(checkingAccount);
        ted.openAccount(savingsAccount);
        ted.openAccount(maxiSavingsAccount);
        
        maxiSavingsAccount.deposit(new BigDecimal("4000.00"));
        checkingAccount.deposit(new BigDecimal("1536.00"));
        savingsAccount.deposit(new BigDecimal("1000.00"));
        
        assertEquals(new BigDecimal("405.08"), bank.totalInterestPaid());
    }

}
