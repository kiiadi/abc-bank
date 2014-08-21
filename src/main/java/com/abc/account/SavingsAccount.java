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
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccount extends Account {

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(double initialDeposit, Date openDate) {
        super(initialDeposit, openDate);
    }

    public SavingsAccount(double initialDeposit) {
        super(initialDeposit);
    }

    /**
     * Calculates daily accrual values with varying rates - .01% if balance less than $1000, .02% if greater.
     *
     * @param balance         - the amount to base accrual calculations on
     * @param calculationDate - the date from which calculations should be made
     * @return
     */
    @Override
    protected double calculateDailyAccrual(double balance, Date calculationDate) {
        double accrual=0.0;
        double dailyAccrual_LO = (0.001 / 360);//~ Actual/360 accrual
        double dailyAccrual_HI = (0.002 / 360);//~ Actual/360 accrual

        if (balance > 1000) {
            accrual += (balance - 1000) * dailyAccrual_HI;
        }

        accrual += balance * dailyAccrual_LO;

        return accrual;
    }

    @Override
    public AccountType accountType() {
        return AccountType.SAVINGS;
    }
}
