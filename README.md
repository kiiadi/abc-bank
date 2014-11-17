Summary of the changes
======================
* ALL THE ADDITIONAL FEATURES ARE IMPLEMENTED.
* TEST CASES DETAILS HAVE COMMENTS DESCRIBING TEST SCENARIOS.
  INTEREST CACULATION TEST CASES WOULD FAIL AS CALCULATION IS DONE BASED ON THE NUMBER OF DAYS ON THE DAY OF RUN.
  CURRENT EXPECTED VALUES WERE THE VALUES APPLICABLE WHEN THE TEST CASES WERE WRITTEN.
*  NEW CLASSES CREATED ARE AccountTest AND DateProviderTest.

Class specific changes
====================== 
Account class :
   1) Account types have been implemented as enum.
      Account description is set as part of enum constructor which would be used in Customer class for statement generation.
   2) Access modifier for transactions is made private.
   3) A new date variable lastWithdrawalDate to track last withdrawal date to be used in interest rate calculation for Maxi Savings Account.
   4) withdraw and deposit methods are overloaded to accept a date argument. This is done to create a scenario where withdrawal was more than 10 days back.
   5) interestEarned method now handle additional feature of Maxi Savings interest calculation and daily interest calculation.
   6) checkIfTransactionsExist method definition is merged to sumTransactions.
   7) numOfDaysForInterestCalculation method consider the first deposit date as the first date to calculate the number of days till today.
   8) isNoWithdrawalInLast10Days method checks if any withdrawal in last 10 days.

Bank class :
   1) Removed null initialization from getFirstCustomer method 

Customer class :
   1) statementForAccount method now uses the Account description that was set as part of enum constructor in Account class.
   2) getStatement and statementForAccount methods have been modified as per test case testApp() from test class CustomerTest.
   3) transferFunds method implements the additional feature of fund transfer. A basic check is done so that same account is not used. This is done as there is no account number currently.

Transaction class:
   1) Instance variables are made private and getter methods are provided.

DateProvider class:
   1) No longer referred in other classes.


