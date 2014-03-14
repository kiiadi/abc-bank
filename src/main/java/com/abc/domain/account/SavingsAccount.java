package com.abc.domain.account;

import com.abc.domain.account.interest.SavingsInterestAccrued;

public class SavingsAccount extends AbstractAccount {

    public SavingsAccount() {
        super("Savings Account");
        super.interest = new SavingsInterestAccrued();
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof SavingsAccount)) {
            return false;
        }

        SavingsAccount other = (SavingsAccount) object;
        
        return name().equals(other.name());
    }
}
