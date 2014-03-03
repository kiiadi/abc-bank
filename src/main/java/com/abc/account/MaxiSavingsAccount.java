/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.account;

import com.abc.account.transaction.Transaction;
import com.abc.DateProvider;
import com.abc.account.transaction.TransactionType;
import com.abc.utils.DateUtils;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiecaxb
 */
public class MaxiSavingsAccount extends AbstractAccount {

    private final static List<InterestRange> INTEREST_RANGE_LADDER = new ArrayList<InterestRange>();

    static {

        InterestRange interestRange = null;
        BigDecimal interestRate = null;
        BigDecimal minimumAmount = null;
        BigDecimal maximumAmount = null;

        interestRate = new BigDecimal("2.00");
        minimumAmount = new BigDecimal("0.00");
        maximumAmount = new BigDecimal("1000.00");

        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        INTEREST_RANGE_LADDER.add(interestRange);

        interestRate = new BigDecimal("5.00");
        minimumAmount = new BigDecimal("1000.00");
        maximumAmount = new BigDecimal("2000.00");

        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        INTEREST_RANGE_LADDER.add(interestRange);

        interestRate = new BigDecimal("10.00");
        minimumAmount = new BigDecimal("2000.00");
        maximumAmount = new BigDecimal(Long.MAX_VALUE).setScale(2);

        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        INTEREST_RANGE_LADDER.add(interestRange);

    }

    public MaxiSavingsAccount() {
        super(AccountType.MAXI_SAVINGS, MaxiSavingsAccount.INTEREST_RANGE_LADDER);
    }

    /*
    New functionality starts here
    */
    
    @Override
    public BigDecimal interestEarned() {

        List<InterestRange> interestRangeLadder = null;

        if (isWithdrawalInTenDays(getTransactions())) {
            interestRangeLadder = buildPenaltyInterestLadder();
        } else {
            interestRangeLadder = buildStandardInterestLadder();
        }
        
        return super.interestEarned(interestRangeLadder);
    }

    private boolean isWithdrawalInTenDays(List<Transaction> transactions) {

        boolean withdrawalInTenDays = false;

        long currentTime;
        long transactionDate;
        long daysApart;

        for (Transaction transaction : transactions) {

            if (transaction.getType() == TransactionType.DEBIT) {
                
                transactionDate = transaction.getTransactionDate();
                currentTime = DateProvider.getInstance().now();

                daysApart = DateUtils.getDayDifference(transactionDate, currentTime);

                if (daysApart < 11L) {
                    withdrawalInTenDays = true;
                    break;
                }
            }

        }

        return withdrawalInTenDays;

    }

    private List<InterestRange> buildPenaltyInterestLadder() {

        List<InterestRange> interestRangeLadder = new ArrayList<InterestRange>();

        InterestRange interestRange = null;

        BigDecimal interestRate = null;
        BigDecimal minimumAmount = null;
        BigDecimal maximumAmount = null;

        interestRate = new BigDecimal("0.10");
        minimumAmount = new BigDecimal("0.00");
        maximumAmount = new BigDecimal(Long.MAX_VALUE).setScale(2);

        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        interestRangeLadder.add(interestRange);

        return interestRangeLadder;

    }

    private List<InterestRange> buildStandardInterestLadder() {

        InterestRange interestRange = null;

        BigDecimal interestRate = null;
        BigDecimal minimumAmount = null;
        BigDecimal maximumAmount = null;

        List<InterestRange> interestRangeLadder = new ArrayList<InterestRange>();

        interestRate = new BigDecimal("5.00");
        minimumAmount = new BigDecimal("0.00");
        maximumAmount = new BigDecimal(Long.MAX_VALUE).setScale(2);

        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        interestRangeLadder.add(interestRange);

        return interestRangeLadder;

    }

}
