package com.abc.account;

import com.abc.Account;
import com.abc.Transaction;

import java.util.Date;
import static com.abc.utilities.DateUtility.*;

/**
 * Created with IntelliJ IDEA.
 * User: bradharper
 * Date: 8/18/14
 * Time: 11:19 PM
 *
 * An account implementation with specific "maxi" savings account rates and features.
 *
 */
public class MaxiSavingsAccount extends Account {

    public MaxiSavingsAccount() {
        super();
    }

    public MaxiSavingsAccount(double initialDeposit, Date openDate) {
        super(initialDeposit, openDate);
    }

    public MaxiSavingsAccount(double initialDeposit) {
        super(initialDeposit);
    }

    /**
     * Calculates daily accrual values with varying rates - 5% If there are no withdrawals in the previous 10 days, .01%
     * otherwise.
     *
     * @param balance         - the amount to base accrual calculations on
     * @param calculationDate - the date from which calculations should be made
     * @return
     */
    @Override
    protected double calculateDailyAccrual(double balance, Date calculationDate) {
        double dailyAccrual_LOW = (0.001 / 360);//lower rate if withdrawals in past 10 days.
        double dailyAccrual_HI = (0.05 / 360);//higher rate when no withdrawals in past 10 days.

        Transaction latestWithdrawal = latestWithdrawal();
        if (latestWithdrawal!=null &&
                daysBetweenDates(
                        latestWithdrawal.getTransactionDate(),
                        calculationDate)<=10){
            return balance * dailyAccrual_LOW;
        }
        return balance * dailyAccrual_HI;
    }

    @Override
    public AccountType accountType() {
        return AccountType.MAXI_SAVINGS;
    }
}
