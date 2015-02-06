package com.abc;

import static com.abc.domain.AccountType.CHECKING;
import static com.abc.domain.AccountType.SAVINGS;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.abc.domain.Account;
import com.abc.domain.Customer;
import com.abc.service.AccountService;
import com.abc.service.CustomerService;
import com.abc.service.TransactionService;


public class CustomerTest {

    private static CustomerService customerService;
    private static AccountService accountService;
    private static TransactionService transactionService;
    
    @BeforeClass
    public static void setUp() {
    	customerService = new CustomerService();
    	accountService = new AccountService();
    	transactionService = new TransactionService();
    	accountService.setTransactionService(transactionService);
    	customerService.setAccountService(accountService);
    }
    
    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(CHECKING);
        Account savingsAccount = new Account(SAVINGS);

        Customer henry = new Customer("Henry");
        customerService.addAccount(henry, checkingAccount);
        customerService.addAccount(henry, savingsAccount);
        
        accountService.deposit(checkingAccount, 100.0);
        accountService.deposit(savingsAccount, 4000.0);
        accountService.withdraw(savingsAccount, 200.0);

        assertEquals("Statement for Henry\n" +
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
                "Total In All Accounts $3,900.00", customerService.getStatement(henry));
    }
    
    @Test
    public void testOneAccount(){
    	Customer oscar = new Customer("Oscar");
    	customerService.addAccount(oscar, new Account(SAVINGS));
    	
        assertEquals(1, customerService.getNumberOfAccounts(oscar));
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar");
        customerService.addAccount(oscar, new Account(SAVINGS));
        customerService.addAccount(oscar, new Account(CHECKING));

        assertEquals(2, customerService.getNumberOfAccounts(oscar));
    }

    @Ignore
    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar");
        customerService.addAccount(oscar, new Account(SAVINGS));
        customerService.addAccount(oscar, new Account(CHECKING));

        assertEquals(3, customerService.getNumberOfAccounts(oscar));
    }
    
    @Test 
    public void testTransfer(){

        Account checkingAccount = new Account(CHECKING);
        Account savingsAccount = new Account(SAVINGS);

        Customer james = new Customer("James");
        customerService.addAccount(james, checkingAccount);
        customerService.addAccount(james, savingsAccount);
        
        accountService.deposit(checkingAccount, 100.0);
        accountService.deposit(savingsAccount, 200.0);

        customerService.transfer(james, CHECKING, SAVINGS, 50.0);
        customerService.transfer(james, SAVINGS, CHECKING, 75.0);

        assertEquals("Statement for James\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  withdrawal $50.00\n" +
                "  deposit $75.00\n" +
                "Total $125.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $200.00\n" +
                "  deposit $50.00\n" +
                "  withdrawal $75.00\n" +
                "Total $175.00\n" +
                "\n" +
                "Total In All Accounts $300.00", customerService.getStatement(james));
    }
}
