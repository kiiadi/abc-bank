package com.abc.domain.account;

import com.abc.domain.account.interest.MaxiSavingsInterestAccrued;

public class MaxiSavingsAccount extends AbstractAccount {
	
    public MaxiSavingsAccount() {
        super("Maxi Savings Account");
        super.interest = new MaxiSavingsInterestAccrued();
    }
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof MaxiSavingsAccount)) {
            return false;
        }

        MaxiSavingsAccount other = (MaxiSavingsAccount) object;
        
        return name().equals(other.name());
    }
}