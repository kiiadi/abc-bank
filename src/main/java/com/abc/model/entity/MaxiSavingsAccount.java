package com.abc.model.entity;

import com.abc.impl.DefaultSystemSettings;
import com.abc.impl.util.DateTimeUtil;
import com.abc.impl.util.MathUtil;
import com.abc.model.api.SystemSettings;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class MaxiSavingsAccount extends Account {

    private static final int NUMBER_OF_DAYS_NO_WITHDRAWALS_PREFERRED = 10;
    private static final double LOWER_INTEREST_RATE = 0.1;
    private static final double HIGHER_INTEREST_RATE = 5.0;
    private SystemSettings systemSettings = new DefaultSystemSettings();

    public MaxiSavingsAccount(String name) {
        super(name);
    }

    public void setSystemSettings(SystemSettings systemSettings) {
        this.systemSettings = systemSettings;
    }

    @Override
    public String getAccountType() {
        return "Maxi-Savings Account";
    }

    @Override
    public BigDecimal calculateInterest() {
        if(haveThereBeenWithdrawals(NUMBER_OF_DAYS_NO_WITHDRAWALS_PREFERRED)) {
            return MathUtil.calculateInterestForOneDay(getBalance(), LOWER_INTEREST_RATE);
        } else {
            return MathUtil.calculateInterestForOneDay(getBalance(), HIGHER_INTEREST_RATE);
        }
    }

    private boolean haveThereBeenWithdrawals(int numberOfDaysToLookBack) {
        Date dateOfLastWithdrawal = dateOfLastWithdrawal();
        if(dateOfLastWithdrawal == null) return false;

        Date dateForComparison = DateTimeUtil.pushDateForwardByNumberOfDays(dateOfLastWithdrawal, numberOfDaysToLookBack);
        return !dateForComparison.before(systemSettings.getSystemDate());
    }

    private Date dateOfLastWithdrawal() {

        Date lastWithdrawalDate = null;

        for(Transaction transaction : getTransactions()) {
            if(transaction.getType().equals(Transaction.Type.DEBIT)) {
                if(lastWithdrawalDate == null || transaction.getCreatedOn().after(lastWithdrawalDate)) {
                    lastWithdrawalDate = transaction.getCreatedOn();
                }
            }
        }

        return lastWithdrawalDate;
    }
}
