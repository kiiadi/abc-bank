package com.abc;

import com.abc.accounts.*;
import com.abc.interestRateCalculators.StubAmountInterestRateCalculator;
import com.abc.interestRateCalculators.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {


    @Test
    public void shouldDisplayCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount(new StubInterestRateCalculator()));

        Customer bill = new Customer("Bill");
        bill.openAccount(new CheckingAccount(new StubInterestRateCalculator()));
        bill.openAccount(new CheckingAccount(new StubInterestRateCalculator()));

        bank.addCustomer(john);
        bank.addCustomer(bill);

        assertEquals("Customer Summary\n - John (1 account)\n - Bill (2 accounts)", bank.customerSummary());
    }

    @Test
    public void shouldReturnCorrectTotalInterestForallCustomers() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount(new StubInterestRateCalculator());
        Customer bill = new Customer("Bill");
        bill.openAccount(checkingAccount);
        bank.addCustomer(bill);
        checkingAccount.deposit(100.0);


        Account savingAccount = new SavingAccount(new StubAmountInterestRateCalculator());
        Customer alex = new Customer("Alex");
        alex.openAccount(savingAccount);
        bank.addCustomer(alex);
        savingAccount.deposit(1000.0);

        Account maxiSavingAccount = new MaxiSavingAccount(new StubInterestRateCalculator());
        Customer bob = new Customer("Bob");
        bob.openAccount(maxiSavingAccount);
        bank.addCustomer(bob);

        maxiSavingAccount.deposit(2000.0);
        Assert.assertThat(bank.totalInterestPaidAllCustomers(), CoreMatchers.is(3.1));
    }



}
