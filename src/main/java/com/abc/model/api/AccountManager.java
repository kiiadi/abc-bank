package com.abc.model.api;

import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.Transaction;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface AccountManager {

    Account openCheckingAccount(Customer customer);
    Account openSavingsAccount(Customer customer);
    Account openMaxiSavingsAccount(Customer customer);

    Transaction depositMoneyToAccount(Account account, BigDecimal amount);
    Transaction withdrawMoneyFromAccount(Account account, BigDecimal amount);

}
