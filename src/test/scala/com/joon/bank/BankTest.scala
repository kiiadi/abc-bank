package com.joon.bank
import org.junit._
import Assert._
import com.joon.bank.App.Customer
import com.joon.bank.App.Checking
import com.joon.bank.App.Bank
import com.joon.bank.App.Savings
import com.joon.bank.App.MaxiSavings
import org.joda.time.DateTime
import com.joon.bank.App.Transaction
class BankTest {

 val DOUBLE_DELTA = 1e-15;

  @Test
  def customerSummary() {
   var customer = new Customer("John",Nil)    
   customer  = customer.openAccount(new Checking(Nil))
   val bank = new Bank(customer::Nil)
   println(bank.customerSummary())
   assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
  }
  @Test
  def checkingAccount() {
    var bill = new Customer("Bill",Nil)
    val acct = new Checking(Nil)
    bill = bill.openAccount(acct.deposit(100.0))
    val bank = new Bank(List(bill))
    println(bank.totalInterestPaid())
    assertEquals(0.0, bank.totalInterestPaid(), DOUBLE_DELTA);

  }
  @Test
  def savingsAccount() {
    val savings = new Savings(List(new Transaction(1500,DateTime.now().minusYears(1).toDate )) )
    val cust = new Customer("Bill",List(savings))
    val bank = new Bank(List(cust)) 
    assertEquals(3.002, bank.totalInterestPaid(), 0.001)
  }
  @Test
  def maxiSavingsAccont() {
    val maxi = new MaxiSavings(Nil) 
    val cust = new Customer("Bill",List(maxi.deposit(3000.0)))
    val bank = new Bank(List(cust)) 
    println(bank.totalInterestPaid())
    assertEquals(30.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }
}