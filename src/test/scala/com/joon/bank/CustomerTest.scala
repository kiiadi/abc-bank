package com.joon.bank
import org.junit._
import Assert._
import com.joon.bank.App.Customer
import com.joon.bank.App.Savings
import com.joon.bank.App.Checking
import java.util.Date
import com.joon.bank.App.Transaction
@Test
class CustomerTest {
  @Test
  def testStatement() {
    var cust = new Customer("Henry",Nil)
    val checking  = new Checking(List(new Transaction(100.0,new Date)))
    val savings =new Savings(List(new Transaction(4000.0,new Date)))
    cust =cust.openAccount(checking).openAccount(savings)
    cust =cust.change(savings, 400);
    assertEquals(cust.statement(), "Statement for Henry\nSavings Account\n deposit $400.00\n deposit $4,000.00\nChecking Account\n deposit $100.00")
  }


  @Test
  def testOneAccount() {
    var cust = new Customer("Oscar",Nil)
    cust =cust.openAccount(new Savings(Nil))
    assertEquals(1, cust.accounts.size)
  }

  @Test
  def testTwoAccounts() {
    var cust = new Customer("Oscar",Nil)
    cust = cust.openAccount(new Savings(Nil))
    cust = cust.openAccount(new Checking(Nil))
    assertEquals(2, cust.accounts.size)
  }
  //tests additional transfer
  @Test
  def testTransfer() = {
    var cust = new Customer("Henry",Nil)
    val checking  = new Checking(List(new Transaction(100.0,new Date)))
    val savings =new Savings(List(new Transaction(4000.0,new Date)))
    cust =cust.openAccount(checking).openAccount(savings)
    cust = cust.change(savings, -100)
    println(cust.accounts.head.total)
    assertEquals(cust.accounts.head.total.intValue(),3900)
  }


}