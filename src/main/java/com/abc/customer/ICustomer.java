package com.abc.customer;

import com.abc.account.IAccount;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface ICustomer {

    public void openAccount(final IAccount account);

    public int getNumberOfAccounts();
    
    public double getTotalInterestEarned();

    public String getStatement();
    
    public String getId();
    
    public String getDisplayName();

}
