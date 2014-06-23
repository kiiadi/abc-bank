Programming Test
========

This is a dummy application to be used as part of a software development interview.

instructions
--------

* Treat this code as if you owned this application, do whatever you feel is necessary to make this your own.
* There are several deliberate design, code quality and test issues that should be identified and resolved.
* Below is a list of the current features supported by the application; as well as some additional features that have been requested by the business owner.
* In order to work on this take a fork into your own GitHub area; make whatever changes you feel are necessary and when you are satisfied submit back via a pull request. See details on GitHub's [Fork & Pull](https://help.github.com/articles/using-pull-requests) model
* The project uses maven to resolve dependencies however if you want to avoid maven configuration the only external JAR that's required is junit-4.11.
* Refactor and add features (from the below list) as you see fit; there is no need to add all the features in order to "complete" the exercise. Keep in mind that code quality is the critical measure and there should be an obvious focus on testing.
* You'll notice there is no database or UI; these are not needed - the exercise deliberately avoids these requirements.
* REMEMBER: this is YOUR code, made any changes you feel are necessary.
* You're welcome to spend as much time as you like; however it's anticipated that this should take about 2 hours.

abc-bank
--------

A dummy application for a bank; should provide various functions of a retail bank.

### Current Features

* A customer can open an account
* A customer can deposit / withdraw funds from an account
* A customer can request a statement that shows transactions and totals for each of their accounts
* Different accounts have interest calculated in different ways
  * **Checking accounts** have a flat rate of 0.1%
  * **Savings accounts** have a rate of 0.1% for the first $1,000 then 0.2%
  * **Maxi-Savings accounts** have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
* A bank manager can get a report showing the list of customers and how many accounts they have
* A bank manager can get a report showing the total interest paid by the bank on all accounts

### Additional Features

* A customer can transfer between their accounts
* Change **Maxi-Savings accounts** to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
* Interest rates should accrue daily (incl. weekends), rates above are per-annum


--------------------------
Major change list

1) AccountTest.java: Added test case for deposite/withdraw negative amount;
   Added test case with Overwithdraw and found a bug and fixed the issue (Can't withdraw more than balance)

2) CustomerTest.java: Added check for customers opening 3 accounts. Fixed the balancesheet to include interest paid.

3) Changed summary(statement/customer) to use StringBuilder

4) Added more checks for interest paid scenarios

5) Added test case for getFirstCustomer, found NullPointer exception, fixed the issue

6) Changed caluation of Maxi-Savings accounts, changed test cases 

7) Changed interest rate calculation, added date to transaction and changed test cases

8) Transfer should be wrapped in transaction(like DB) with atomic operation around withdraw/deposite

9) Found issue with displaying customer summary (having no account should show 0 account)

10) Added test case for DateProvider

11) ran coverage test, to make sure coverage is 99%