package com.abc.model.entity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */

//this class is not adding much value right now, hopefully will be appreciated in the future
public class InterestAmountPaidReport extends Report {

    private BigDecimal totalInterestPaid = new BigDecimal("0");

    public InterestAmountPaidReport(String name) {
        super(name);
    }

    public void addAccount(Account account) {
        totalInterestPaid = totalInterestPaid.add(account.getTotalInterestReceived());
    }

    public BigDecimal getTotalInterestPaid() {
        return totalInterestPaid;
    }
}
