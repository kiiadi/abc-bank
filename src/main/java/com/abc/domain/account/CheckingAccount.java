package com.abc.domain.account;

import com.abc.domain.account.interest.CheckingInterestAccrued;

public class CheckingAccount extends AbstractAccount {
	
    public CheckingAccount() {
        super("Checking Account");
        super.interest = new CheckingInterestAccrued();
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof CheckingAccount)) {
            return false;
        }

        CheckingAccount other = (CheckingAccount) object;
        
        return name().equals(other.name());
    }
}