package com.abc;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testCustomerStatement(){

        Customer henry = new Customer("Henry").openAccount(Account.CHECKING).openAccount(Account.SAVINGS);
        Customer james = new Customer("James").openAccount(Account.CHECKING);

        henry.deposit(100.0, Account.CHECKING);
        henry.deposit(4000.0, Account.SAVINGS);
        henry.withdraw(200.0, Account.SAVINGS);

        james.deposit(300.0, Account.CHECKING);
        james.withdraw(100.0, Account.CHECKING);

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
                "Total In All Accounts $3,900.00", ReportGenerator.generateCustomerStatement(henry));

        assertEquals("Statement for James\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $300.00\n" +
                "  withdrawal $100.00\n" +
                "Total $200.00\n" +
                "\n" +
                "Total In All Accounts $200.00", ReportGenerator.generateCustomerStatement(james));
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.SAVINGS);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccounts(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.SAVINGS);
        oscar.openAccount(Account.CHECKING);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAccounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.SAVINGS);
        oscar.openAccount(Account.CHECKING);
        oscar.openAccount(Account.MAXI_SAVINGS);
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransfer() {
        Customer gandalf = new Customer("Gandalf");
        gandalf.openAccount(Account.CHECKING);
        gandalf.deposit(350.0, Account.CHECKING);
        gandalf.openAccount(Account.SAVINGS);
        gandalf.deposit(800, Account.SAVINGS);
        gandalf.transfer(250, Account.CHECKING, Account.SAVINGS);
        assertEquals("Statement for Gandalf\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $350.00\n" +
                "  withdrawal $250.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $800.00\n" +
                "  deposit $250.00\n" +
                "Total $1,050.00\n" +
                "\n" +
                "Total In All Accounts $1,150.00", ReportGenerator.generateCustomerStatement(gandalf));
    }

    @Test
    public void testInterestEarned() {
        Customer pippin = new Customer("Pippin");
        pippin.openAccount(Account.CHECKING);
        pippin.openAccount(Account.SAVINGS);
        pippin.deposit(1000.0, Account.CHECKING);
        pippin.deposit(2000.0, Account.SAVINGS);
        assertEquals(4.0, pippin.calculateTotalInterestEarned(), DOUBLE_DELTA);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testFailedWithdraw() {
        Customer rohan = new Customer("Rohan");
        rohan.openAccount(Account.CHECKING);
        rohan.deposit(100.0, Account.CHECKING);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Withdrawal failed. Cannot withdraw more than the account balance");
        rohan.withdraw(200.0, Account.CHECKING);
    }

    @Test
    public void testFailedTransfer() {
        Customer strider = new Customer("Strider");
        strider.openAccount(Account.CHECKING);
        strider.deposit(1000.0, Account.CHECKING);
        strider.openAccount(Account.SAVINGS);
        strider.deposit(2000.0, Account.SAVINGS);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot transfer between accounts. Transfer amount cannot be greater than balance");
        strider.transfer(4000.0, Account.CHECKING, Account.SAVINGS);
    }
}
