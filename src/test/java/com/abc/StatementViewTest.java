package com.abc;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatementViewTest {
    @Test
    public void shouldRenderCurrentStatement() {
        final Customer henry = new Customer("Henry");

        final Account checkingAccount = henry.open(AccountType.Checking);
        final Account savingsAccount = henry.open(AccountType.Savings);
        final Account maxiSavingsAccount = henry.open(AccountType.MaxiSavings);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        maxiSavingsAccount.deposit(1.00);

        final StatementView view = new StatementView();
        assertThat(view.render(henry), is("Statement for Henry\n" +
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
                "Maxi Savings Account\n" +
                "  deposit $1.00\n" +
                "Total $1.00\n" +
                "\n" +
                "Total In All Accounts $3,901.00"));
    }
}
