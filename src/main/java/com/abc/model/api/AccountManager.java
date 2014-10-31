package com.abc.model.api;

import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface AccountManager {

    List<Account> getAllAccounts();

    Account openCheckingAccount(Customer customer, String accountName);
    Account openSavingsAccount(Customer customer, String accountName);
    Account openMaxiSavingsAccount(Customer customer, String accountName);

    Transaction depositMoneyToAccount(Account account, BigDecimal amount);
    Transaction withdrawMoneyFromAccount(Account account, BigDecimal amount);
    void transferMoney(Customer customer, String account1, String account2, BigDecimal amount);

    Transaction addInterest(Account account);

}
