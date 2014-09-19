package com.abc;

import com.abc.account.AccountTest;
import com.abc.account.MultiThreadedAccountTest;
import com.abc.bank.BankTest;
import com.abc.customer.CustomerTest;
import com.abc.transaction.TransactionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Archana on 9/19/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TransactionTest.class,
    AccountTest.class,
    MultiThreadedAccountTest.class,
    CustomerTest.class,
    BankTest.class
})

public class BankAllTestsSuite {
}
