/**
 * 
 */
/**
 * A dummy application for a bank; should provide various functions of a retail bank.
 * <p>
 * Current Features
 * <p>
 * <li>A customer can open an account
 * <li>A customer can deposit / withdraw funds from an account
 * <li>A customer can request a statement that shows transactions and totals for each of their accounts
 * <li>Different accounts have interest calculated in different ways
 * <li>Checking accounts have a flat rate of 0.1%
 * <li>Savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
 * <li>Maxi-Savings accounts have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
 * <li>A bank manager can get a report showing the list of customers and how many accounts they have
 * <li>A bank manager can get a report showing the total interest paid by the bank on all account
 * 
 * Additional Features
 * 
 * <li>A customer can transfer between their account
 * <li>Change Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals 
 * in the past 10 days otherwise 0.1.
 * <li>Interest rates should accrue daily (incl. weekends), rates above are per-annum
 * 
 * @author Bank of America
 *
 */
package com.abc;