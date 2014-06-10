package com.abc.interestRateCalculators;

import com.abc.Transaction;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public class MaxiInterestRateCalculator implements InterestRateCalculator<List<Transaction>>{

    @Override
    public double calculateInterestRate(List<Transaction> transactions) {
        boolean hasWithdrawWithinLast10Days = hasWithdrawWithinLast10Days(transactions);

        if(!hasWithdrawWithinLast10Days){
            return 0.5;
        }

        return 0.001;
    }

    private boolean hasWithdrawWithinLast10Days(List<Transaction> transactions) {

        Date tenDaysAgo = date10DaysAgo();

        boolean hasWithDrawlWithin10Days = false;

        for (Transaction transaction : transactions) {
            boolean isWithdrawl = transaction.getAmount() < 0;

            if(isWithdrawl && transaction.getDate().after(tenDaysAgo)){
                hasWithDrawlWithin10Days = true;
                break;
            }
        }

        return hasWithDrawlWithin10Days;
    }

    private Date date10DaysAgo() {
        DateTime dt = new DateTime();
        DateTime tenDaysEarlier = dt.minusDays(10);
        return tenDaysEarlier.withTimeAtStartOfDay().toDate();
    }

}
