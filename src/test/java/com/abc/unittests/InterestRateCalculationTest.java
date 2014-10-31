package com.abc.unittests;


import com.abc.dummy.DummySystemSettings;
import com.abc.impl.DefaultAccountManager;
import com.abc.impl.util.MathUtil;
import com.abc.model.api.AccountManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.MaxiSavingsAccount;
import com.abc.model.entity.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class InterestRateCalculationTest {

    private final double DELTA = 0.000001;

    private Customer customer = new Customer("Customer 1");
    private AccountManager accountManager = new DefaultAccountManager();

    @Test
    public void testMathUtil_interestRateForOneDay_1() {
        double oneDayInterestRate = MathUtil.calculateInterestRateForOneDay(5.0);

        assertEquals(0.013368,oneDayInterestRate, DELTA);
    }

    @Test
    public void testMathUtil_interestRateForOneDay_2() {
        double oneDayInterestRate = MathUtil.calculateInterestRateForOneDay(6.0);

        assertEquals(0.015965,oneDayInterestRate, DELTA);
    }

    @Test
    public void testMathUtil_interestForOneDay() {
        BigDecimal interest = MathUtil.calculateInterestForOneDay(new BigDecimal("200000.00"), 6.95);

        //one day the interest is expected to be:
        assertEquals(36.820511, interest.doubleValue(), DELTA);
    }

    @Test
    public void testMathUtil_interestForOneYear() {
        //over a whole year the interest is one dollar
        BigDecimal currentBalance = new BigDecimal("100");
        for(int i = 0;i < 365;i++) {
            BigDecimal interest = MathUtil.calculateInterestForOneDay(currentBalance, 1);
            currentBalance = currentBalance.add(interest);
        }

        assertEquals(101, currentBalance.doubleValue(), DELTA);
    }

    @Test
    public void testCheckingAccount() {

        Account checkingAccount = accountManager.openCheckingAccount(customer,"Checking Account");
        accountManager.depositMoneyToAccount(checkingAccount,new BigDecimal("100000.00"));
        Transaction interestTransaction = accountManager.addInterest(checkingAccount);

        assertEquals(Transaction.Type.INTEREST,interestTransaction.getType());
        assertEquals(0.273836,interestTransaction.getAmount().doubleValue(), DELTA);

    }

    @Test
    public void testSavingsAccount() {

        Account savingsAccount = accountManager.openSavingsAccount(customer,"Savings Account");
        accountManager.depositMoneyToAccount(savingsAccount,new BigDecimal("100000.00"));
        Transaction interestTransaction = accountManager.addInterest(savingsAccount);

        assertEquals(Transaction.Type.INTEREST,interestTransaction.getType());
        assertEquals(0.002738 + 0.541925,interestTransaction.getAmount().doubleValue(), DELTA);

    }

    @Test
    public void testMaxiSavingsAccount_noWithdrawals() {

        Account maxiSavingsAccount = accountManager.openMaxiSavingsAccount(customer,"Maxi Savings Account");
        accountManager.depositMoneyToAccount(maxiSavingsAccount, new BigDecimal("100000.00"));
        Transaction interestTransaction = accountManager.addInterest(maxiSavingsAccount);

        assertEquals(Transaction.Type.INTEREST,interestTransaction.getType());
        assertEquals(13.368061,interestTransaction.getAmount().doubleValue(), DELTA);

    }

    @Test
    public void testMaxiSavingsAccount_withdrawalWithinTheNoWithdrawalsHorizon() {

        Account maxiSavingsAccount = accountManager.openMaxiSavingsAccount(customer,"Maxi Savings Account");
        accountManager.depositMoneyToAccount(maxiSavingsAccount, new BigDecimal("100000.00"));
        accountManager.withdrawMoneyFromAccount(maxiSavingsAccount, new BigDecimal("500.00"));
        Transaction interestTransaction = accountManager.addInterest(maxiSavingsAccount);

        assertEquals(Transaction.Type.INTEREST,interestTransaction.getType());
        assertEquals(0.272466,interestTransaction.getAmount().doubleValue(), DELTA);
    }

    @Test
    public void testMaxiSavingsAccount_withdrawalOutOfTheNoWithdrawalsHorizon() {

        Account maxiSavingsAccount = accountManager.openMaxiSavingsAccount(customer,"Maxi Savings Account");

        accountManager.depositMoneyToAccount(maxiSavingsAccount, new BigDecimal("100500.00"));
        accountManager.withdrawMoneyFromAccount(maxiSavingsAccount, new BigDecimal("500.00"));

        //this line will make the account think the withdrawal is old
        ((MaxiSavingsAccount)maxiSavingsAccount).setSystemSettings(new DummySystemSettings());
        Transaction interestTransaction = accountManager.addInterest(maxiSavingsAccount);

        assertEquals(Transaction.Type.INTEREST,interestTransaction.getType());
        assertEquals(13.368061,interestTransaction.getAmount().doubleValue(), DELTA);

    }

}
