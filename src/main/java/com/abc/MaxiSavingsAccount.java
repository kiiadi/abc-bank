package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class MaxiSavingsAccount extends Account {

    private Pair[] alternativeInterestTable;

    MaxiSavingsAccount(AccountType accountType, Pair... pairs) {
        super(accountType, pairs);
    }

    public double interestEarned() {
        //find latest withdrawal
        Optional<Date> lastWithdrawal = transactions.stream().filter(t -> t.amount<0).map(Transaction::getTransactionDate).max(Date::compareTo) ;
        if(!lastWithdrawal.isPresent()) return super.interestEarned() ;
        Date today = new Date() ;
        if((today.getTime() - lastWithdrawal.get().getTime())/1000/3600/24 > 10) {
            return super.interestEarned();
        } else {
            return interestEarned(alternativeInterestTable) ;
        }
    }

    void setAlternativeInterestTable(Pair... alternativeInterestTable) {
        this.alternativeInterestTable = alternativeInterestTable;
    }
}