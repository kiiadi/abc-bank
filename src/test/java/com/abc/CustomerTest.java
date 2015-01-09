package com.abc;

import static com.abc.Account.Types.CHECKING;
import static com.abc.Account.Types.MAXI_SAVINGS;
import static com.abc.Account.Types.SAVINGS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

    public void testCustomerStatementGeneration(){

        Account checkingAccount = CHECKING.newInstance();
        Account savingsAccount = SAVINGS.newInstance();

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
    public void openAccounts_thenNumberOfAccountsIncrements() {
        Customer oscar = new Customer("Oscar").openAccount(SAVINGS.newInstance());
        oscar.openAccount(CHECKING.newInstance());
        oscar.openAccount(MAXI_SAVINGS.newInstance());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
