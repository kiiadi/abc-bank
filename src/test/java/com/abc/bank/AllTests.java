package com.abc.bank;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.abc.bank.account.AccountTest;
import com.abc.bank.account.AccountTypeTest;
import com.abc.bank.account.CheckingAccountTest;
import com.abc.bank.account.MaxiSavingsAccountTest;
import com.abc.bank.account.SavingsAccountTest;
import com.abc.bank.account.TransactionTest;
import com.abc.bank.customer.CustomerTest;
import com.abc.bank.customer.CustomersImplTest;


@RunWith(Suite.class)
@SuiteClasses({ BankTest.class, AccountTypeTest.class, AccountTest.class,
    CheckingAccountTest.class, MaxiSavingsAccountTest.class, 
    SavingsAccountTest.class, TransactionTest.class,
    CustomersImplTest.class, CustomerTest.class})
public class AllTests {

}
