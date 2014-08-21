package com.abc.account;

import com.abc.Account;
import com.abc.utilities.DateUtility;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bradharper
 * Date: 8/18/14
 * Time: 11:19 PM
 *
 * An account implementation with specific checking account rates and features.
 *
 */
public class CheckingAccount extends Account {

    public static final double RATE_FLAT = 0.001;

    public CheckingAccount() {
        super();
    }

    /**
     * Creates an accont with the provided initial deposit.
     * @param initialDeposit
     */
    public CheckingAccount(double initialDeposit) {
        super(initialDeposit);
    }

    /**
     * Creates an account with the provided initial deposit and opened on the specified open date.
     * @param initialDeposit
     * @param openDate
     */
    public CheckingAccount(double initialDeposit, Date openDate) {
        super(initialDeposit, openDate);
    }

    /**
     * Calculates daily accrual values with a flat rate of 0.01%.
     *
     * @param balance         - the amount to base accrual calculations on
     * @param calculationDate - the date from which calculations should be made
     * @return
     */
    @Override
    protected double calculateDailyAccrual(double balance, Date calculationDate) {
        double dailyAccrual = (RATE_FLAT/360);//~ Actual/360 accrual

        return balance*dailyAccrual;

    }

    @Override
    public AccountType accountType() {
        return AccountType.CHECKING;
    }
}
