package com.abc;

import java.util.Date;
import java.util.Optional;

public class MaxiSavingsAccount extends Account {

    private Pair[] alternativeInterestTable;

    MaxiSavingsAccount(Pair... pairs) {
        super(AccountType.MAXI_SAVINGS, pairs);
    }

    public double interestEarned() {
        //find latest withdrawal
        Optional<Date> lastWithdrawal = transactions.stream().filter(t -> t.getAmount()<0).map(Transaction::getTransactionDate).max(Date::compareTo) ;
        if(!lastWithdrawal.isPresent()) return super.interestEarned() ;
        Date today = new Date() ;
        if((today.getTime() - lastWithdrawal.get().getTime())/1000/3600/24 > 10) {
            return super.interestEarned();
        } else {
            return interestEarned(alternativeInterestTable, sumTransactions()) ;
        }
    }

    void setAlternativeInterestTable(Pair... alternativeInterestTable) {
        this.alternativeInterestTable = alternativeInterestTable;
    }
}