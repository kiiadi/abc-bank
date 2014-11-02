package com.abc.model.api;

import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.Transaction;
import com.abc.model.entity.Transfer;

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
    void deleteAllAccounts();

    void depositMoneyToAccount(Account account, BigDecimal amount);
    void withdrawMoneyFromAccount(Account account, BigDecimal amount);
    void transferMoney(Transfer transfer);
    void addInterest(Account account);

    public static class AttemptedAccountOverflow extends RuntimeException {

    }

    public static class AttemptedNegativeAmountTransfer extends RuntimeException {

    }
}
