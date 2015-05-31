package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatementTest {

    @Test
    public void generateStatement() {
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Customer customer = new Customer("Henry");
        customer.openAccount(checkingAccount);
        customer.openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        Statement statement = new Statement(customer);

        assertEquals("Statement for Henry\n" +
                '\n' +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                '\n' +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                '\n' +
                "Total In All Accounts $3,900.00", statement.generateStatement());
    }
}