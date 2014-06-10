package com.abc;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.SavingAccount;
import com.abc.accounts.interestRateCalculator.StubAmountInterestRateCalculator;
import com.abc.accounts.interestRateCalculator.StubInterestRateCalculator;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void shouldGenerateCustomerStatementIncorrectFormat(){

        Account checkingAccount = new CheckingAccount(new StubInterestRateCalculator());
        Account savingsAccount = new SavingAccount(new StubInterestRateCalculator());

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

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
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(new StubInterestRateCalculator()));
        oscar.openAccount(new CheckingAccount(new StubInterestRateCalculator()));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void shouldGetCorrectNameForCustomer(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(new StubAmountInterestRateCalculator()));
        assertEquals("Oscar", oscar.getName());
    }

    @Test
    public void shouldGetCorrectTotalInterestEarned(){
        SavingAccount savingAccount = new SavingAccount(new StubAmountInterestRateCalculator());
        savingAccount.deposit(100);
        Customer oscar = new Customer("Oscar")
                .openAccount(savingAccount);
        CheckingAccount checkingAccount = new CheckingAccount(new StubInterestRateCalculator());
        checkingAccount.deposit(100);
        oscar.openAccount(checkingAccount);


        assertThat(oscar.totalInterestEarned(), is(0.2));
    }


}
