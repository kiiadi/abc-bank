package com.abc.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.abc.Bank;
import com.abc.Customer;
import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal("100.00"));

        assertEquals(0.100, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(new BigDecimal("1500.0"));

        assertEquals(2.00, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(new BigDecimal("3000.0"));

        /**
         * Verified with online calculators.
         * $3000 compounded daily with interest rate 5% and term of 1 yr yields 153.80 as the interest.
         * http://www.thecalculatorsite.com/finance/calculators/compoundinterestcalculator.php
         */
        assertEquals(153.80, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }
    
    @Test
    public void maxi_savings_account_with_withdrawal_within10days() {
    	Bank bank = new Bank();
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(new BigDecimal("3100.0"));
        /**
         * Deliberate a recent withdrawal to test that on the same maxi saving account as before, with same net balance of
         * $3000 ( 3100 - 100 ) , we dont get the same interest of 5%. Rather we get interest of 0.1% i.e. $3.00 as specified in
         * the "Additional Features" aka. Requirements.
         */
        maxiSavingsAccount.withdraw(new BigDecimal("100.00"));
        

        /**
         * Verified with online calculators.
         * $3000 compounded daily with interest rate of 0.1% and term of 1 yr yields 3.00 as the interest.
         * http://www.thecalculatorsite.com/finance/calculators/compoundinterestcalculator.php
         */
        assertEquals(3.00, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

}
