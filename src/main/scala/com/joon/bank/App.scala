package com.joon.bank

import org.joda.time.DateTime
import java.util.Date
import java.util.Calendar
import org.joda.time.Period
import org.joda.time.Days
/**
 *
 */
object App extends App {

  trait Account {
    def deposit(amount: Double): Account
    def sumTransactions: Double
    def interestEarned: Double
    def withdraw(amount: Double): Account
    def total: Double
    def statement: String
  }

  case class Transaction(amount: Double, transactionDate: Date)

  abstract class AccountImpl(transactions: List[Transaction]) extends Account {
    def total: Double = transactions.map(t => t.amount).sum

    override def deposit(amount: Double): Account = {
      val transaction = new Transaction(amount, new Date)
      val list = transaction :: transactions;
      this match {
        case Savings(_) => new Savings(list)
        case Checking(_) => new Checking(list)
        case MaxiSavings(_) => new MaxiSavings(list)
      }
    }

    override def withdraw(amount: Double): Account = {
      if (amount > total) throw new Exception("not enough money in account")
      else {
        val transaction = new Transaction(-amount, new Date)
        val list = transaction :: transactions;
        this match {
          case Savings(_) => new Savings(list)
          case Checking(_) => new Checking(list)
          case MaxiSavings(_) => new MaxiSavings(list)
        }
      }
    }

    override def sumTransactions(): Double = {

      def iterateSum(trans: List[Transaction]): Double = {
        if (trans.isEmpty) 0
        else {
          val curr = trans.head.amount
          curr + iterateSum(trans.tail)
        }
      }
      iterateSum(transactions)
    }

    override def statement(): String = {
      var transstr = transactions.map(t => if (t.amount >= 0) " deposit " + toDollars(t.amount) else " withdrawl " + toDollars(t.amount))
      transstr.mkString("\n")

    }
  }

  case class Savings(transactions: List[Transaction]) extends AccountImpl(transactions: List[Transaction]) {
    def interestEarned: Double = {
      dailyCompoundinterest(transactions, p=> if (p<=1000) 0.001 else 0.002 )
    }
  }

  case class MaxiSavings(transactions: List[Transaction]) extends AccountImpl(transactions: List[Transaction]) {
    def interestEarned: Double = {
      // if there are withdrawals in the last 10 days
      var cal = DateTime.now()
      var tenDaysAgo = cal.minusDays(10)
      if (transactions.filter(p => p.transactionDate.after(tenDaysAgo.toDate())).isEmpty) total * 0.05
      else total * 0.01
    }

  }

  case class Checking(transactions: List[Transaction]) extends AccountImpl(transactions: List[Transaction]) {
    def interestEarned: Double = {
      dailyCompoundinterest(transactions, p=>0.001)
    }
  }

  case class Customer(
    name: String,
    accounts: List[Account]) {
    def numAccounts(): Int = accounts.size

    def openAccount(account: Account): Customer = {
      new Customer(name, account :: accounts)
    }
    //helper method to keep accounts in sync with customer
    def change(account: Account, amount: Double): Customer = {
      //remove the old account object
      val list = accounts.filter(p => if (p eq account) false else true)
      //add or subtract:
      if (amount >= 0) {
        new Customer(name, account.deposit(amount.abs) :: list)
      } else new Customer(name, account.withdraw(amount.abs) :: list)
    }

    def transfer(from: Account, to: Account, amount: Double): Customer = {
      change(from, -amount).change(to, amount)
    }

    def statement(): String = {

      def str(account: Account): String = {

        val start = account match {
          case Savings(_) => "Savings Account\n"
          case Checking(_) => "Checking Account\n"
          case MaxiSavings(_) => "Maxi Savings Account\n"
        }
        start + account.statement
      }
      val endStr = accounts.map(acc => str(acc))
      "Statement for " + name + '\n' + endStr.mkString("\n")
    }

  }

  case class Bank(customers: List[Customer]) {

    def customerSummary(): String = {
      val intro = "Customer Summary";
      val list = for (c <- customers) yield '\n' + " - " + c.name + " (" + c.accounts.size + " " + pluralize("account", c.accounts.size) + ")"
      intro + list.mkString(",")
    }

    def getFirstCustomer(): String = {
      if (customers.isEmpty) new String
      else customers.head.name
    }

    def totalInterestPaid(): Double = {
      val allinterests = for {
        customer <- customers;
        account <- customer.accounts
      } yield account.interestEarned
      allinterests.sum
    }
  }

  def dailyCompoundinterest(transactions: List[Transaction], rateFormula:Double=>Double): Double = {
    val sorted = transactions.sortWith(_.transactionDate after _.transactionDate)
    val startDate = sorted.head.transactionDate

    def walkThroughTransactions(trans: List[Transaction],principal:Double) :Double = {
       if (trans.isEmpty) 0 
       else {
         val head = trans.head
         val start = new DateTime(head.transactionDate)
         val p=principal + head.amount; 
         val end = if (trans.size==1) DateTime.now else new DateTime(trans.tail.head.transactionDate)
         
         val numDays = Days.daysBetween(start.withTimeAtStartOfDay(),end.withTimeAtStartOfDay()).getDays().abs
         val rate = rateFormula(p)
         val currInterest = compoundDailyInterest(p, rate, numDays)
         val currPrincipal = currInterest + p
         currInterest + walkThroughTransactions(trans.tail, currPrincipal)
       }
    }
    walkThroughTransactions(sorted, 0.0)
  }
    def compoundDailyInterest(principal: Double, rate: Double, numDays: Int): Double = {
      principal * scala.math.pow((1 + (rate / 365)), numDays) - principal
    } 

  def pluralize(str: String, num: Int): String = {
    if (num > 1) str + "s"
    else str
  }

  def toDollars(d: Double): String = {
    "$%,.2f".format(d.abs.doubleValue());
  }


}
