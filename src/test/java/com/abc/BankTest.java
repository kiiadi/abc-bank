package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
  private static final double DOUBLE_DELTA = 1e-15;

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

    checkingAccount.deposit(1234567.89);

    assertEquals(getExpectedCheckingInterest(1234567.89), bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testSavingsAccountLowInterestPaid() {
    Bank bank = new Bank();
    Account savingsAccount = new SavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(savingsAccount));

    double lowSavingsDeposit = SavingsAccount.LOW_HIGH_BOUNDARY / 2;
    savingsAccount.deposit(lowSavingsDeposit);

    assertEquals(getExpectedSavingsInterest(lowSavingsDeposit), bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testSavingsAccountHighInterestPaid() {
    Bank bank = new Bank();
    Account savingsAccount = new SavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(savingsAccount));

    double highSavingsDeposit = SavingsAccount.LOW_HIGH_BOUNDARY * 2;
    savingsAccount.deposit(highSavingsDeposit);

    assertEquals(getExpectedSavingsInterest(highSavingsDeposit), bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testMaxiSavingsAccountLowInterestPaid() {
    Bank bank = new Bank();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(maxiSavingsAccount));

    double lowMaxiDeposit = MaxiSavingsAccount.LOW_MED_BOUNDARY / 2;
    maxiSavingsAccount.deposit(lowMaxiDeposit);

    assertEquals(getExpectedMaxiInterest(lowMaxiDeposit), bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testMaxiSavingsAccountMedInterestPaid() {
    Bank bank = new Bank();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(maxiSavingsAccount));

    double medMaxiDeposit = (MaxiSavingsAccount.LOW_MED_BOUNDARY + MaxiSavingsAccount.MED_HIGH_BOUNDARY) / 2;
    maxiSavingsAccount.deposit(medMaxiDeposit);

    assertEquals(getExpectedMaxiInterest(medMaxiDeposit), bank.totalInterestPaid(), DOUBLE_DELTA);
  }


  @Test
  public void testMaxiSavingsAccountHighInterestPaid() {
    Bank bank = new Bank();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(maxiSavingsAccount));

    double highMaxiDeposit = MaxiSavingsAccount.MED_HIGH_BOUNDARY * 2;
    maxiSavingsAccount.deposit(highMaxiDeposit);

    assertEquals(getExpectedMaxiInterest(highMaxiDeposit), bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  private double getExpectedCheckingInterest(double amount) {
    return amount * CheckingAccount.INTEREST_RATE;
  }

  private double getExpectedSavingsInterest(double amount) {
    if(amount <= SavingsAccount.LOW_HIGH_BOUNDARY) {
      return amount * SavingsAccount.LOW_RATE;
    } else {
      return SavingsAccount.TOTAL_LOW_INTEREST + (amount - SavingsAccount.LOW_HIGH_BOUNDARY) * SavingsAccount.HIGH_RATE;
    }
  }

  private double getExpectedMaxiInterest(double amount) {
    if(amount <= MaxiSavingsAccount.LOW_MED_BOUNDARY) {
      return amount * MaxiSavingsAccount.LOW_RATE;
    } else if(amount <= MaxiSavingsAccount.MED_HIGH_BOUNDARY) {
      return MaxiSavingsAccount.TOTAL_LOW_INTEREST
               + (amount - MaxiSavingsAccount.LOW_MED_BOUNDARY) * MaxiSavingsAccount.MED_RATE;
    } else {
      return MaxiSavingsAccount.TOTAL_LOW_INTEREST + MaxiSavingsAccount.TOTAL_MED_INTEREST
               + (amount - MaxiSavingsAccount.MED_HIGH_BOUNDARY) * MaxiSavingsAccount.HIGH_RATE;
    }
  }
}
