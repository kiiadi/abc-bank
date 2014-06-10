package com.abc;

import com.abc.accounts.Account;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerTest {

    @Test
    public void shouldGenerateCustomerStatementIncorrectFormat(){
        Customer henry = new Customer("Henry");


        Account checkingAccount = mock(Account.class);
        Account savingsAccount = mock(Account.class);


        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        when(checkingAccount.getStatement()).thenReturn("Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00");
        when(checkingAccount.sumTransactions()).thenReturn(100.00);
        when(savingsAccount.getStatement()).thenReturn( "Savings Account\n" +
                                                        "  deposit $4,000.00\n" +
                                                        "  withdrawal $200.00\n" +
                                                        "Total $3,800.00");
        when(savingsAccount.sumTransactions()).thenReturn(3800.00);



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
                "Total In All Accounts $3,900.00", henry.getStatement());
    }




    @Test
    public void shouldOpen2Accounts(){
        Customer oscar = new Customer("Oscar");
        Account checkingAccount = mock(Account.class);
        Account savingsAccount = mock(Account.class);

        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void shouldGetCorrectNameForCustomer(){
        Customer oscar = new Customer("Oscar");
        assertEquals("Oscar", oscar.getName());
    }

    @Test
    public void shouldGetCorrectTotalInterestEarned(){
        Customer oscar = new Customer("Oscar");

        Account checkingAccount = mock(Account.class);
        when(checkingAccount.interestEarned()).thenReturn(0.1);

        Account savingsAccount = mock(Account.class);
        when(savingsAccount.interestEarned()).thenReturn(0.1);

        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);

        assertThat(oscar.totalInterestEarned(), is(0.2));
    }


}
