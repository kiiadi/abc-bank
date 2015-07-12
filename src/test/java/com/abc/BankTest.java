package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-5;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0, TransactionType.DEPOSIT);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0, TransactionType.DEPOSIT);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0, TransactionType.DEPOSIT);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void checkingAccountCompoundingTwoYears() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.CHECKING);
        bank.addCustomer(new Customer("Leo").openAccount(checkingAccount));

        checkingAccount.deposit(100000, TransactionType.DEPOSIT);
        double interest = checkingAccount.computeInterest();
        checkingAccount.deposit(interest, TransactionType.INTEREST);

        double secondYearInterest = checkingAccount.computeInterest();
        checkingAccount.deposit(secondYearInterest, TransactionType.INTEREST);

        assertEquals(200.1, bank.totalInterestPaid(), DOUBLE_DELTA);

    }

    @Test
    public void savingsAccountCompoundingTwoYears() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Neo").openAccount(savingsAccount));

        savingsAccount.deposit(1500, TransactionType.DEPOSIT);

        double interest = savingsAccount.computeInterest();
        savingsAccount.deposit(interest, TransactionType.INTEREST);

        double secondYearInterest = savingsAccount.computeInterest();
        savingsAccount.deposit(secondYearInterest, TransactionType.INTEREST);

        assertEquals(4.004, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    // Total interest paid is just twice because after first year, interest is withdrawn
    @Test
    public void maxiSavingsAccountCompounding() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Treo").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000, TransactionType.DEPOSIT);

        double interest = maxiSavingsAccount.computeInterest();
        maxiSavingsAccount.deposit(interest, TransactionType.INTEREST);

        maxiSavingsAccount.withdraw(interest);

        double secondYearInterest = maxiSavingsAccount.computeInterest();
        maxiSavingsAccount.deposit(secondYearInterest, TransactionType.INTEREST);

        assertEquals(interest *2.00, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    
}
