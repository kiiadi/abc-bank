package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
  private static final double DOUBLE_DELTA = 1e-15;

  double checkingDepositAmount = 100.0;
  double savingsDepositAmount = 1500.0;
  double maxiDepositAmount = 3000.0;
  double expectedCheckingInterest;
  double expectedSavingsInterest;
  double expectedMaxiInterest;
  double expectedTotalInterest;

  @Before
  public void setUp() {
    expectedCheckingInterest = checkingDepositAmount * CheckingAccount.INTEREST_RATE;

    if(savingsDepositAmount <= SavingsAccount.LOW_HIGH_BOUNDARY) {
      expectedSavingsInterest = savingsDepositAmount * SavingsAccount.LOW_RATE;
    } else {
      expectedSavingsInterest = SavingsAccount.TOTAL_LOW_INTEREST
                                  + (savingsDepositAmount - SavingsAccount.LOW_HIGH_BOUNDARY) * SavingsAccount.HIGH_RATE;
    }

    if(maxiDepositAmount <= MaxiSavingsAccount.LOW_MED_BOUNDARY) {
      expectedMaxiInterest = maxiDepositAmount * MaxiSavingsAccount.LOW_RATE;
    } else if(maxiDepositAmount <= MaxiSavingsAccount.MED_HIGH_BOUNDARY) {
      expectedMaxiInterest = MaxiSavingsAccount.TOTAL_LOW_INTEREST
                               + (maxiDepositAmount - MaxiSavingsAccount.LOW_MED_BOUNDARY) * MaxiSavingsAccount.MED_RATE;
    } else {
      expectedMaxiInterest = MaxiSavingsAccount.TOTAL_LOW_INTEREST + MaxiSavingsAccount.TOTAL_MED_INTEREST
                               + (maxiDepositAmount - MaxiSavingsAccount.MED_HIGH_BOUNDARY) * MaxiSavingsAccount.HIGH_RATE;
    }

    expectedTotalInterest = expectedCheckingInterest + expectedSavingsInterest + expectedMaxiInterest;
  }

  @Test
  public void testCustomerSummary() {
    Bank bank = new Bank();

    Customer customer1 = new Customer("Customer1 Name");
    customer1.openAccount(new CheckingAccount());
    bank.addCustomer(customer1);

    Customer customer2 = new Customer("Customer2 Name");
    customer2.openAccount(new SavingsAccount());
    customer2.openAccount(new MaxiSavingsAccount());
    bank.addCustomer(customer2);

    assertEquals("Customer Summary\n" +
                   " - Customer1 Name (1 account)\n" +
                   " - Customer2 Name (2 accounts)", bank.customerSummary());
  }

  @Test
  public void testCheckingAccountInterestPaid() {
    Bank bank = new Bank();
    Account checkingAccount = new CheckingAccount();
    Customer customer = new Customer("Customer Name").openAccount(checkingAccount);
    bank.addCustomer(customer);

    checkingAccount.deposit(checkingDepositAmount);

    assertEquals(expectedCheckingInterest, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testSavingsAccountInterestPaid() {
    Bank bank = new Bank();
    Account savingsAccount = new SavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(savingsAccount));

    savingsAccount.deposit(savingsDepositAmount);

    assertEquals(expectedSavingsInterest, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testMaxiSavingsAccountInterestPaid() {
    Bank bank = new Bank();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(maxiSavingsAccount));

    maxiSavingsAccount.deposit(maxiDepositAmount);

    assertEquals(expectedMaxiInterest, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testCombinedInterestPaid() {
    Bank bank = new Bank();
    Account savingsAccount = new SavingsAccount();
    Account checkingAccount = new CheckingAccount();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(savingsAccount)
                       .openAccount(checkingAccount)
                       .openAccount(maxiSavingsAccount));

    checkingAccount.deposit(checkingDepositAmount);
    savingsAccount.deposit(savingsDepositAmount);
    maxiSavingsAccount.deposit(maxiDepositAmount);

    assertEquals(expectedTotalInterest, bank.totalInterestPaid(), DOUBLE_DELTA);
  }
}
